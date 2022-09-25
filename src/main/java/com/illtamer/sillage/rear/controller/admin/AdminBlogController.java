package com.illtamer.sillage.rear.controller.admin;

import com.illtamer.sillage.rear.annotation.SecurityUser;
import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.pojo.User;
import com.illtamer.sillage.rear.service.BlogService;
import com.illtamer.sillage.rear.vo.PublishBlog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin/blog")
public class AdminBlogController {

    private final BlogService blogService;

    public AdminBlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Response getPublishBlog(@PathVariable Integer id) {
        return Response.success(blogService.getPublishBlog(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Response removeBlog(
            @PathVariable Integer id
    ) {
        return Response.success(blogService.removeById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Response addPublishBlog(
            @RequestBody PublishBlog publishBlog,
            @SecurityUser User user
    ) {
        return Response.success(blogService.savePublishBlog(publishBlog, user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/manage/{index}")
    public Response getManageBlogPage(
            @PathVariable Integer index,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer typeId,
            @RequestParam(required = false) Boolean recommend
    ) {
        return Response.success(blogService.listManageBlogs(index, title, typeId, recommend));
    }

}
