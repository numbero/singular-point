package org.example.singularpoint.infra;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisLock {

    @Resource
    private StringRedisTemplate redisTemplate;

    public boolean tryLock(String lockKey, String requestId, long expireSeconds) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, Duration.ofSeconds(expireSeconds)));
    }

    // NOTE 本操作非原子 可能出现并发问题
    public void unlock(String lockKey, String requestId) {
        String currentRequestId = redisTemplate.opsForValue().get(lockKey);
        if (requestId.equals(currentRequestId)) {
            redisTemplate.delete(lockKey);
        }
    }
}
