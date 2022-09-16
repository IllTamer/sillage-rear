package com.illtamer.sillage.rear.controller;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.CommentService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{blogId}")
    public Response getComment(@PathVariable Integer blogId) {
        return Response.success(commentService.listTopCommentByBlogId(blogId));
    }

}
