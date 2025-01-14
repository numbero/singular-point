package org.example.detault.domain;

public interface UserRepository {
    int insert(User user);
    User selectByName(String username);
}
