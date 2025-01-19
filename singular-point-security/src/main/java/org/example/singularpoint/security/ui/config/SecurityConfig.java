package org.example.singularpoint.security.ui.config;

import jakarta.annotation.Resource;
import org.example.singularpoint.security.ui.service.CustomerAccessDeniedHandler;
import org.example.singularpoint.security.ui.service.CustomerAuthenticationEntryPoint;
import org.example.singularpoint.security.ui.service.CustomerAuthenticationFailureHandler;
import org.example.singularpoint.security.ui.service.CustomerAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@EnableMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true
)
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Resource
    private CustomerAuthenticationSuccessHandler successHandler;
    @Resource
    private CustomerAuthenticationFailureHandler failureHandler;
    @Resource
    private CustomerAccessDeniedHandler accessDeniedHandler;
    @Resource
    private CustomerAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 基于内存的用户管理
     * NOTE 需要与数据库的用户管理任选其一
     */
    //@Bean
    //public InMemoryUserDetailsManager userDetailsService() {
    //    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //    UserDetails user = User.withUsername("admin")
    //            .password(encoder.encode("123456"))
    //            .roles("ADMIN")
    //            .build();
    //    return new InMemoryUserDetailsManager(user);
    //}

    /**
     * 基于数据库的用户管理
     * NOTE 需要与基于内存的用户管理任选其一
     */

    // NOTE 内嵌数据库
    //  当无外置数据库时启用
    //@Bean
    //public DataSource dataSource() {
    //    return new EmbeddedDatabaseBuilder()
    //            .setType(EmbeddedDatabaseType.H2)
    //            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
    //            .build();
    //}
    //
    //@Bean
    //public UserDetailsManager userDetailsManager(DataSource dataSource) {
    //    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //    UserDetails user = User.withUsername("admin")
    //            .password(encoder.encode("123456"))
    //            .roles("ADMIN")
    //            .build();
    //    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    //    users.createUser(user);
    //    return users;
    //}

    // NOTE 外置数据库
    //  只需要自行实现 UserDetailsManager 接口即可
    //  范例见 org.example.singularpoint.security.MyUserDetailsManager

    /**
     * security鉴权配置类
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests(authorize -> authorize
        //                .anyRequest().authenticated()
        //        ).formLogin(form -> form.loginPage("/login").permitAll())
        //        .logout(logout -> logout.permitAll());

        // 接口权限
        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/ping").permitAll()
                        .requestMatchers("/anonymous").anonymous()
                        .requestMatchers("/permit-all").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/config").hasAnyRole("ADMIN", "CONFIG")
                        // NOTE 其他所有api都不需要登录
                        //.anyRequest().permitAll()
                        // NOTE 其他所有api都需要登录
                        .anyRequest().authenticated()
        );

        // 登录成功/失败处理器
        http.formLogin(form ->
                        form.successHandler(successHandler)
                                .failureHandler(failureHandler));

        // 鉴权异常处理
        // FIXME 当前还未完全理解 需要后续研究学习
        //http.exceptionHandling(exception ->
        //        // 登录用户鉴权失败处理
        //        exception.accessDeniedHandler(accessDeniedHandler)
        //                // 匿名用户鉴权失败处理
        //                .authenticationEntryPoint(authenticationEntryPoint));

        // 记住我
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setValiditySeconds(3600);
        http.rememberMe(rememberMe ->
                rememberMe.rememberMeServices(rememberMeServices));

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
