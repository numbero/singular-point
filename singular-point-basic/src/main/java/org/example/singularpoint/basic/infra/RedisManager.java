package org.example.singularpoint.basic.infra;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisManager {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value); // 设置值
    }

    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key); // 获取值
    }

    public void deleteValue(String key) {
        stringRedisTemplate.delete(key); // 删除值
    }

    public BoundValueOperations<String, String> getValueOperations(String key) {
        return stringRedisTemplate.boundValueOps(key); // 获取值操作对象
    }
}
