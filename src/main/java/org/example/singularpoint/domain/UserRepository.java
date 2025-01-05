package org.example.singularpoint.domain;

public interface UserRepository {
    int insert(User user);
    User selectByName(String username);
}
