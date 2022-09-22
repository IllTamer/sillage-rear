package com.illtamer.sillage.rear.controller.admin;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.BlogService;
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
    @GetMapping("/{index}")
    public Response getManageBlogPage(
            @PathVariable Integer index,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer typeId,
            @RequestParam(required = false) Boolean recommend
    ) {
        return Response.success(blogService.listManageBlogs(index, title, typeId, recommend));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Response removeBlog(
            @PathVariable Integer id
    ) {
        return Response.success(blogService.removeById(id));
    }

}
