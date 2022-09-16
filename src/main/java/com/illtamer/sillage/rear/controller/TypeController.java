package com.illtamer.sillage.rear.controller;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.TypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/list")
    public Response getTypeList() {
        return Response.success(typeService.list());
    }

    @GetMapping("/count-list")
    public Response getCountList() {
        return Response.success(typeService.listCountType());
    }

}
