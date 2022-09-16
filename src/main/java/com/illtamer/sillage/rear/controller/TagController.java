package com.illtamer.sillage.rear.controller;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.TagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public Response getTagList() {
        return Response.success(tagService.list());
    }

}
