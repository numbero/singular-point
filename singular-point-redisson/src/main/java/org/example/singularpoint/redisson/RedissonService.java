package org.example.singularpoint.redisson;

import jakarta.annotation.Resource;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
public class RedissonService {

    @Resource
    private RedissonClient redissonClient;

    public void testLock() {
        RLock lock = redissonClient.getLock("testLock");
        try {
            // 加锁
            lock.lock();
            System.out.println("Lock acquired!");
            // 模拟业务操作
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 解锁
            lock.unlock();
            System.out.println("Lock released!");
        }
    }
}
