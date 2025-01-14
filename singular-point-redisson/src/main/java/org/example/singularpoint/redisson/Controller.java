package org.example.singularpoint.redisson;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Resource
    private RedissonService redissonService;
    @Resource
    private RedisService redisService;

    @PostMapping("/test-lock")
    public void testLock() {
        redissonService.testLock();
    }

    @PostMapping("/test-redis")
    public void testRedis() {
        redisService.setString("test", "test");
        redisService.getString("test");
    }
}
