package com.illtamer.sillage.rear.controller;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.BlogService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/{index}")
    public Response getBlogPage(@PathVariable Integer index) {
        return Response.success(blogService.listRecommendHomeBlog(index));
    }

    @GetMapping("/detail/{blogId}")
    public Response getBlogDetail(@PathVariable Integer blogId) {
        return Response.success(blogService.getDetailBlog(blogId));
    }

    @GetMapping("/latest-list")
    public Response getLatestList() {
        return Response.success(blogService.listLatestMinBlog());
    }

    @GetMapping("/year-month-map")
    public Response getYearWithMonthMap() {
        return Response.success(blogService.mapMinBlogOrderByYearWithMonth());
    }

    @GetMapping("/search/{index}")
    public Response getSearchPage(
            @PathVariable Integer index,
            @RequestParam("content") String content
    ) {
        return Response.success(blogService.listSearchHomeBlog(content, index));
    }

    @GetMapping("/by-type/{typeId}/{index}")
    public Response getTypePage(
            @PathVariable Integer typeId,
            @PathVariable Integer index
    ) {
        return Response.success(blogService.listHomeBlogByTypeId(typeId, index));
    }

    @GetMapping("/by-tag/{tagId}/{index}")
    public Response getTagPage(
            @PathVariable Integer tagId,
            @PathVariable Integer index
    ) {
        return Response.success(blogService.listHomeBlogByTagId(tagId, index));
    }

}
