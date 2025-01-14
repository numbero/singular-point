package org.example.detault.ui;

import jakarta.annotation.Resource;
import org.example.detault.app.UserService;
import org.example.detault.domain.User;
import org.example.detault.ui.model.Result;
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
