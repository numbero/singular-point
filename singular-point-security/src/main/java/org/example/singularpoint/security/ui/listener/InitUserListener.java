package org.example.singularpoint.security.ui.listener;

import jakarta.annotation.Resource;
import org.example.singularpoint.security.ui.service.CustomerUserDetailsManager;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitUserListener implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private CustomerUserDetailsManager customerUserDetailsManager;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        customerUserDetailsManager.clear();
        customerUserDetailsManager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build());
        customerUserDetailsManager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build());
        customerUserDetailsManager.createUser(User.withUsername("config")
                .password(passwordEncoder.encode("123456"))
                .roles("CONFIG")
                .build());
        customerUserDetailsManager.createUser(User.withUsername("user-config")
                .password(passwordEncoder.encode("123456"))
                .roles("USER","CONFIG")
                .build());
    }

}
