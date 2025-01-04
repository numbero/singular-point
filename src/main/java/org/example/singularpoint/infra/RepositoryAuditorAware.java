package org.example.singularpoint.infra;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

// NOTE 当没有通过spring-security框架获取到用户信息时，使用此方法填充默认值
@Component
public class RepositoryAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("admin" + Math.random());
    }
}
