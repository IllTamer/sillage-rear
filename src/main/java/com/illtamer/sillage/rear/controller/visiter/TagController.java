package com.illtamer.sillage.rear.controller.visiter;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.TagService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{index}")
    public Response getTagPage(@PathVariable Integer index) {
        return Response.success(tagService.listTag(index));
    }

    @GetMapping("/list")
    public Response getTagList() {
        return Response.success(tagService.list());
    }

}
