package com.illtamer.sillage.rear.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illtamer.sillage.rear.mapper.CommentMapper;
import com.illtamer.sillage.rear.pojo.Comment;
import com.illtamer.sillage.rear.vo.RateComment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

    /**
     * 获取博文对应顶级评论
     * */
    public Collection<RateComment> listTopCommentByBlogId(Integer blogId) {
        return secondOrderFlatten(list(Wrappers.lambdaQuery(Comment.class)
                .eq(Comment::getBlogId, blogId)));
    }

    protected Collection<RateComment> secondOrderFlatten(List<Comment> comments) {
        final List<RateComment> parents = new LinkedList<>();
        // parentId -> son
        final Map<Integer, RateComment> sonMap = new TreeMap<>();
        for (Comment comment : comments) {
            RateComment rateComment = new RateComment();
            BeanUtils.copyProperties(comment, rateComment);
            if (comment.getParentCommentId() == null) {
                parents.add(rateComment);
            } else {
                sonMap.put(comment.getParentCommentId(), rateComment);
            }
        }
        for (RateComment parent : parents) {
            int parentId = parent.getId();
            RateComment son;
            while ((son = sonMap.get(parentId)) != null) {
                parent.getSonComments().add(son);
                parentId = son.getId();
            }
        }
        return parents;
    }

}
