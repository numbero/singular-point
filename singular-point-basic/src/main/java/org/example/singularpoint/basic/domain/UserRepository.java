package org.example.singularpoint.basic.domain;

public interface UserRepository {
    int insert(User user);
    User selectByName(String username);
}
