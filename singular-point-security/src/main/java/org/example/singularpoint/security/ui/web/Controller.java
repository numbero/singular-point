package org.example.singularpoint.security.ui.web;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.singularpoint.common.ui.model.Result;
import org.example.singularpoint.security.ui.config.SecurityConfig;
import org.example.singularpoint.security.ui.service.CustomerUserDetailsManager;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class Controller {

    @Resource
    private CustomerUserDetailsManager customerUserDetailsManager;

    @GetMapping("/ping")
    public Result<?> ping() {
        return Result.success("pong");
    }

    /**
     * 权限测试
     */

    // 任何人都可以访问
    @GetMapping(value = "/permit-all")
    public Result<String> permitAll() {
        return Result.success("permitAll");
    }

    // 未登录时 可以访问
    @GetMapping(value = "/anonymous")
    public Result<String> anonymous() {
        return Result.success("anonymous");
    }

    @GetMapping("/admin")
    public Result<String> admin() {
        return Result.success("admin");
    }

    @GetMapping(value = "/config")
    public Result<String> config() {
        return Result.success("config");
    }

    /**
     * 注解权限配置
     * NOTE 这些注解需要与 @EnableMethodSecurity 搭配使用
     * @see SecurityConfig
     */

    // 指定角色可以访问
    @Secured({"ROLE_ADMIN"})
    @GetMapping(value = "/secured")
    public Result<String> secured() {
        return Result.success("secured");
    }

    // 具有指定权限点的用户可以访问
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/preAuthorize")
    public Result<String> preAuthorize() {
        return Result.success("PreAuthorize");
    }

    // 具有指定权限点的用户可以获得返回值
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/postAuthorize")
    public Result<String> postAuthorize() {
        return Result.success("PostAuthorize");
    }

    /**
     * 获取用户
     */
    @GetMapping(value = "/current-user-principal")
    public Result<String> getCurrentUserPrincipal(Principal principal) {
        String userName = principal.getName();
        log.info("userName by Principal: {}", userName);
        return Result.success(userName);
    }

    @GetMapping(value = "/current-user-authentication")
    public Result<String> getCurrentUserAuthentication(Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName by Authentication: {}", userName);
        return Result.success(userName);
    }

    @GetMapping(value = "/current-user-httpServletRequest")
    public Result<String> getCurrentUserHttpServletRequest(HttpServletRequest httpServletRequest) {
        String userName = httpServletRequest.getUserPrincipal().getName();
        log.info("userName by HttpServletRequest: {}", userName);
        return Result.success(userName);
    }

    @GetMapping(value = "/current-user-authenticationPrincipal")
    public Result<String> getCurrentUserAuthenticationPrincipal(@AuthenticationPrincipal UserDetails user) {
        String userName = user.getUsername();
        log.info("userName by @AuthenticationPrincipal: {}", userName);
        return Result.success(userName);
    }

    @GetMapping(value = "/current-user-securityContextHolder")
    public Result<String> getCurrentUserSecurityContextHolder() {
        String userName = customerUserDetailsManager.getCurrentUser();
        log.info("userName by SecurityContextHolder: {}", userName);
        return Result.success(userName);
    }

}
