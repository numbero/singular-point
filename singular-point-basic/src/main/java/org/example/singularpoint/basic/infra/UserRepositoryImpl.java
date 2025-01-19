package org.example.singularpoint.basic.infra;

import jakarta.annotation.Resource;
import org.example.singularpoint.basic.domain.User;
import org.example.singularpoint.basic.domain.UserRepository;
import org.example.singularpoint.basic.infra.mapper.UserMapper;
import org.example.singularpoint.basic.infra.model.UserDO;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        if (Objects.isNull(user)) {
            return 0;
        }
        return userMapper.insert(new UserDO(user));
    }

    @Override
    public User selectByName(String username) {
        UserDO userDO = userMapper.selectByName(username);
        return Objects.isNull(userDO) ? null : userDO.toUser();
    }
}
