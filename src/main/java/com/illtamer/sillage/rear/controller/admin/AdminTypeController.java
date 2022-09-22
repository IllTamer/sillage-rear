package com.illtamer.sillage.rear.controller.admin;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.pojo.Type;
import com.illtamer.sillage.rear.service.TypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin/type")
public class AdminTypeController {

    private final TypeService typeService;

    public AdminTypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Response removeType(
            @PathVariable Integer id
    ) {
        return Response.success(typeService.removeById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Response updateType(@RequestBody Type type) {
        return Response.success(typeService.updateById(type));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Response addType(@RequestBody Type type) {
        return Response.success(typeService.save(type));
    }

}
