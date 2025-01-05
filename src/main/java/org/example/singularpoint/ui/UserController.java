package org.example.singularpoint.ui;

import jakarta.annotation.Resource;
import org.example.singularpoint.app.UserService;
import org.example.singularpoint.domain.User;
import org.example.singularpoint.ui.model.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public Result<Boolean> create(@RequestBody User user){
        userService.create(user);
        return Result.success(true);
    }

    @GetMapping()
    public Result<User> get(@RequestParam String name){
        return Result.success(userService.getByName(name));
    }
}
