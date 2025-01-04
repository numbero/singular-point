package org.example.singularpoint.app;

import jakarta.annotation.Resource;
import org.example.singularpoint.domain.User;
import org.example.singularpoint.infra.UserDO;
import org.example.singularpoint.infra.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Transactional
    public void create(User user){
        UserDO userDO = new UserDO(user);
        userMapper.insert(userDO);
    }
}
