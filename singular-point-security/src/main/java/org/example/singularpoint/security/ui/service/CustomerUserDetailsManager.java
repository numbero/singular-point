package org.example.singularpoint.security.ui.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.singularpoint.security.infra.mapper.UserMapper;
import org.example.singularpoint.security.infra.model.UserDO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class CustomerUserDetailsManager implements UserDetailsManager {

    @Resource
    private UserMapper userMapper;

    @Override
    public void createUser(UserDetails user) {
        UserDO userDO = new UserDO();
        userDO.setUsername(user.getUsername());
        userDO.setPassword(user.getPassword());
        userDO.setRoles(StringUtils.join(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(), ","));
        userMapper.insert(userDO);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return Objects.nonNull(userMapper.selectByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userMapper.selectByUsername(username);
        if(Objects.isNull(userDO)){
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList(userDO.getRoles()))
                .build();
    }

    public void clear() {
        userMapper.deleteAll();
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 非匿名用户访问才能获得用户信息
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String userName = authentication.getName();
            log.info("userName by SecurityContextHolder: {}", userName);
            return userName;
        }
        throw new RuntimeException("用户不存在！");
    }
}
