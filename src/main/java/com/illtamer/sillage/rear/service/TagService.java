package com.illtamer.sillage.rear.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illtamer.sillage.rear.mapper.BlogTagMapper;
import com.illtamer.sillage.rear.mapper.TagMapper;
import com.illtamer.sillage.rear.pojo.BlogTag;
import com.illtamer.sillage.rear.pojo.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService extends ServiceImpl<TagMapper, Tag> {

    public static final int MAX_PAGE_SIZE = 15;

    private final BlogTagMapper blogTagMapper;

    public TagService(BlogTagMapper blogTagMapper) {
        this.blogTagMapper = blogTagMapper;
    }

    public Page<Tag> listTag(Integer index) {
        return page(new Page<>(index, MAX_PAGE_SIZE));
    }

    /**
     * 查询博文对应标签列表
     * */
    public List<Tag> listByBlogId(Integer blogId) {
        final Set<Integer> tagIds = listTagIdByBlogId(blogId);
        return tagIds.stream().map(this::getById).collect(Collectors.toList());
    }

    public Set<Integer> listBlogIdByTagId(Integer tagId) {
        final List<BlogTag> blogTags = blogTagMapper.selectList(Wrappers.lambdaQuery(BlogTag.class)
                .select(BlogTag::getBlogId)
                .eq(BlogTag::getTagId, tagId));
        return blogTags.stream().map(BlogTag::getBlogId).collect(Collectors.toSet());
    }

    public Set<Integer> listTagIdByBlogId(Integer blogId) {
        final List<BlogTag> blogTags = blogTagMapper.selectList(Wrappers.lambdaQuery(BlogTag.class)
                .select(BlogTag::getTagId)
                .eq(BlogTag::getBlogId, blogId));
        return blogTags.stream().map(BlogTag::getTagId).collect(Collectors.toSet());
    }

}
