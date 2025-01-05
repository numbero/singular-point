package org.example.singularpoint.app;

import jakarta.annotation.Resource;
import org.example.singularpoint.domain.User;
import org.example.singularpoint.domain.UserRepository;
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
