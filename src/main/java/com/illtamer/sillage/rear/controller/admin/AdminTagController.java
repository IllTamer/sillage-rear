package com.illtamer.sillage.rear.controller.admin;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.pojo.Tag;
import com.illtamer.sillage.rear.service.TagService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin/tag")
public class AdminTagController {

    private final TagService tagService;

    public AdminTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Response removeTag(
            @PathVariable Integer id
    ) {
        return Response.success(tagService.removeById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Response updateTag(@RequestBody Tag tag) {
        return Response.success(tagService.updateById(tag));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Response addTag(@RequestBody Tag tag) {
        return Response.success(tagService.save(tag));
    }

}
