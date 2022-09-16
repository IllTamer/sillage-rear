package com.illtamer.sillage.rear.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illtamer.sillage.rear.mapper.BlogMapper;
import com.illtamer.sillage.rear.mapper.UserMapper;
import com.illtamer.sillage.rear.pojo.Blog;
import com.illtamer.sillage.rear.pojo.Tag;
import com.illtamer.sillage.rear.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@Service
public class BlogService extends ServiceImpl<BlogMapper, Blog> {

    public static final int MAX_PAGE_SIZE = 10;

    private final TagService tagService;
    private final CommentService commentService;
    private final UserService userService;

    public BlogService(
            TagService tagService,
            CommentService commentService,
            UserService userService
    ) {
        this.tagService = tagService;
        this.commentService = commentService;
        this.userService = userService;
    }

    /**
     * 获取文章详情 VO
     * */
    public DetailBlog getDetailBlog(Integer blogId) {
        final DetailBlog detailBlog = new DetailBlog();
        final Blog blog = getById(blogId);
        Assert.notNull(blog, "未查询到指定 id 的博文");
        BeanUtils.copyProperties(blog, detailBlog);
        final MinUser minUser = userService.getMinUserById(blog.getUserId());
        detailBlog.setMinUser(minUser);
        final List<Tag> tags = tagService.listByBlogId(blogId);
        detailBlog.setTags(tags);
        final Collection<RateComment> rateComments = commentService.listTopCommentByBlogId(blogId);
        detailBlog.setRateComments(rateComments);
        return detailBlog;
    }

    /**
     * 查询首页最新推荐文章 VO
     * */
    public Page<HomeBlog> listRecommendHomeBlog(Integer index) {
        return generateHomeBlogPage(index, Wrappers.lambdaQuery(Blog.class)
                .select(Blog::getId, Blog::getTitle, Blog::getSummary, Blog::getCover, Blog::getViews, Blog::getCreateTime, Blog::getUserId)
                .eq(Blog::getPublished, true)
                .eq(Blog::getRecommend, true)
                .orderBy(true, false, Blog::getCreateTime));
    }

    /**
     * 查询指定 Type 下首页展示文章 VO
     * */
    public Page<HomeBlog> listHomeBlogByTypeId(Integer typeId, Integer index) {
        return generateHomeBlogPage(index, Wrappers.lambdaQuery(Blog.class)
                .select(Blog::getId, Blog::getTitle, Blog::getSummary, Blog::getCover, Blog::getViews, Blog::getCreateTime, Blog::getUserId)
                .eq(Blog::getPublished, true)
                .eq(Blog::getTypeId, typeId)
                .orderBy(true, false, Blog::getCreateTime));
    }

    /**
     * 查询指定 Tag 下首页展示文章 VO
     * */
    public Page<HomeBlog> listHomeBlogByTagId(Integer tagId, Integer index) {
        final Set<Integer> targetBlogIds = tagService.listBlogIdByTagId(tagId);
        if (targetBlogIds.size() == 0) return new Page<>();
        return generateHomeBlogPage(index, Wrappers.lambdaQuery(Blog.class)
                .select(Blog::getId, Blog::getTitle, Blog::getSummary, Blog::getCover, Blog::getViews, Blog::getCreateTime, Blog::getUserId)
                .eq(Blog::getPublished, true)
                .in(Blog::getId, targetBlogIds)
                .orderBy(true, false, Blog::getCreateTime));
    }

    /**
     * 搜索展示文章 VO
     * */
    public Page<HomeBlog> listSearchHomeBlog(String content, Integer index) {
        return generateHomeBlogPage(index, Wrappers.lambdaQuery(Blog.class)
                .select(Blog::getId, Blog::getTitle, Blog::getSummary, Blog::getCover, Blog::getViews, Blog::getCreateTime, Blog::getUserId)
                .eq(Blog::getPublished, true)
                .and(wrapper1 -> wrapper1
                        .like(Blog::getTitle, content)
                        .or()
                        .like(Blog::getSummary, content))
                .orderBy(true, false, Blog::getCreateTime));
    }

    /**
     * 查询首页最新最简文章 VO
     * @apiNote Limit 10
     * */
    public List<MinBlog> listLatestMinBlog() {
        final List<Blog> blogs = list(Wrappers.lambdaQuery(Blog.class)
                .select(Blog::getId, Blog::getTitle, Blog::getCreateTime, Blog::getFlag)
                .eq(Blog::getPublished, true)
                .orderBy(true, false, Blog::getCreateTime)
                .last("LIMIT " + MAX_PAGE_SIZE));
        return generateMinBlogList(blogs);
    }

    /**
     * 查询归档最简文章 VO
     * @return 倒序 Map: year, month, obj
     */
    public Map<Integer, Map<Integer, Set<MinBlog>>> mapMinBlogOrderByYearWithMonth() {
        Map<Integer, Map<Integer, Set<MinBlog>>> yearMap = new TreeMap<>(Comparator.reverseOrder());
        final List<MinBlog> minBlogList = generateMinBlogList(list(Wrappers.lambdaQuery(Blog.class)
                .select(Blog::getId, Blog::getTitle, Blog::getCreateTime, Blog::getFlag)
                .eq(Blog::getPublished, true)));
        Calendar calendar = Calendar.getInstance();
        for (MinBlog minBlog : minBlogList) {
            calendar.setTime(minBlog.getCreateTime());
            final Map<Integer, Set<MinBlog>> monthMap = yearMap.computeIfAbsent(calendar.get(Calendar.YEAR), key ->
                    new TreeMap<>(Comparator.reverseOrder()));
            final Set<MinBlog> minBlogs = monthMap.computeIfAbsent(calendar.get(Calendar.MONTH) + 1, key ->
                    new TreeSet<>(((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))));
            minBlogs.add(minBlog);
        }
        return yearMap;
    }

    /**
     * 统计博文数量
     * */
    public int countByTypeId(int typeId) {
        return count(Wrappers.lambdaQuery(Blog.class)
                .select(Blog::getId)
                .eq(Blog::getTypeId, typeId));
    }

    protected Page<HomeBlog> generateHomeBlogPage(int index, LambdaQueryWrapper<Blog> wrapper) {
        final Page<Blog> blogPage = page(new Page<>(index, MAX_PAGE_SIZE), wrapper);
        final List<HomeBlog> homeBlogs = generateHomeBlogList(blogPage.getRecords());
        Page<HomeBlog> homeBlogPage = new Page<HomeBlog>().setRecords(homeBlogs);
        BeanUtils.copyProperties(blogPage, homeBlogPage, "records");
        return homeBlogPage;
    }

    protected List<HomeBlog> generateHomeBlogList(Collection<Blog> collection) {
        return collection.stream().map(blog -> {
            HomeBlog homeBlog = new HomeBlog();
            BeanUtils.copyProperties(blog, homeBlog);
            final MinUser minUser = userService.getMinUserById(blog.getUserId());
            List<Tag> tags = tagService.listByBlogId(blog.getId());
            homeBlog.setMinUser(minUser);
            homeBlog.setTags(tags);
            return homeBlog;
        }).collect(Collectors.toList());
    }

    protected List<MinBlog> generateMinBlogList(Collection<Blog> collection) {
        return collection.stream().map(blog -> {
            MinBlog minBlog = new MinBlog();
            BeanUtils.copyProperties(blog, minBlog);
            return minBlog;
        }).collect(Collectors.toList());
    }

}
