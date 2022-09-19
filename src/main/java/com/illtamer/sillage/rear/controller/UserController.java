package com.illtamer.sillage.rear.controller;

import com.illtamer.sillage.rear.entity.Response;
import com.illtamer.sillage.rear.service.UserService;
import com.illtamer.sillage.rear.vo.AuthData;
import com.illtamer.sillage.rear.vo.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Response login(@RequestBody AuthData authData) {
        return Response.success(userService.login(authData));
    }

    @GetMapping("/login-out")
    public Response loginOut() {
        return Response.success(userService.loginOut());
    }

}
