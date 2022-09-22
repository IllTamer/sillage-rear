package com.illtamer.sillage.rear.controller.visiter;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.TypeService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/{index}")
    public Response getTypePage(@PathVariable Integer index) {
        return Response.success(typeService.listType(index));
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
