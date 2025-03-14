package org.example.singularpoint.basic.ui;

import jakarta.annotation.Resource;
import org.example.singularpoint.basic.infra.model.UserDO;
import org.example.singularpoint.basic.infra.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/mybatis")
public class UserMybatisController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDO get(@PathVariable Long id){
        return userMapper.selectById(id);
    }

    @GetMapping
    public UserDO get(@RequestParam String userName){
        return userMapper.selectByName(userName);
    }
}
