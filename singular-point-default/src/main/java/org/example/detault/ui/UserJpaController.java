package org.example.detault.ui;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.detault.infra.UserJpaRepository;
import org.example.detault.infra.model.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/jpa")
@Tag(name = "用户")
public class UserJpaController {

    @Resource
    private UserJpaRepository userJpaRepository;

    // 获取用户
    @GetMapping("/{id}")
    public UserDO get(@PathVariable Long id){
        return userJpaRepository.findById(id).orElse(null);
    }

    // 获取用户列表
    @GetMapping
    public Page<UserDO> list(
            @RequestParam(defaultValue = "id") String property,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        return userJpaRepository.findAll(PageRequest.of(page, size, Sort.by(direction, property)));
    }

    // 创建用户
    @PostMapping
    public UserDO create(@RequestBody UserDO user){
        return userJpaRepository.save(user);
    }

    // 更新用户
    @PutMapping("/{id}")
    public UserDO update(@PathVariable Long id, @RequestBody UserDO user){
        UserDO userDO = userJpaRepository.findById(id).orElse(null);
        if (userDO == null){
            return null;
        }
        userDO.setName(user.getName());
        userDO.setEmail(user.getEmail());
        return userJpaRepository.save(userDO);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userJpaRepository.deleteById(id);
    }

    @DeleteMapping()
    public void deleteByName(@RequestParam String name){
        userJpaRepository.deleteByName_JPQL(name);
    }

}
