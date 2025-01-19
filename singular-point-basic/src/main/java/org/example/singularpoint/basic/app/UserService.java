package org.example.singularpoint.basic.app;

import jakarta.annotation.Resource;
import org.example.singularpoint.basic.domain.User;
import org.example.singularpoint.basic.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Transactional
    public void create(User user){
        userRepository.insert(user);
    }

    public User getByName(String name){
        return userRepository.selectByName(name);
    }
}
