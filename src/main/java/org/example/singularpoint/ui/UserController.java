package org.example.singularpoint.ui;

import jakarta.annotation.Resource;
import org.example.singularpoint.app.UserService;
import org.example.singularpoint.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public Boolean create(@RequestBody User user){
        userService.create(user);
        return true;
    }
}
