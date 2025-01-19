package org.example.singularpoint.basic.ui.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(3)
@Aspect
@Component
public class AspectThree {

    @Pointcut("execution(public * org.example.singularpoint.ui.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before() {
        log.info("before three");
    }

    @After(value = "pointCut()")
    public void after() {
        log.info("after three");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturning() {
        log.info("afterReturning three");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("around three start");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("around error",e);
        }
        log.info("around three end");
        return result;
    }
}