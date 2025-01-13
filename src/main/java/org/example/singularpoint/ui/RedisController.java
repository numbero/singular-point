package org.example.singularpoint.ui;

import jakarta.annotation.Resource;
import org.example.singularpoint.infra.RedisManager;
import org.example.singularpoint.ui.model.Result;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisManager redisManager;

    // set
    @PostMapping("/set")
    public Result<Boolean> set(@RequestParam String key, @RequestParam String value) {
        redisManager.setValue(key, value);
        return Result.success(true);
    }

    // get
    @GetMapping("/get")
    public Result<String> get(@RequestParam String key) {
        return Result.success(redisManager.getValue(key));
    }

    // delete
    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam String key) {
        redisManager.deleteValue(key);
        return Result.success(true);
    }

    // bound
    @PostMapping("/bound")
    public Result<String> bound(@RequestParam String key) {
        BoundValueOperations<String, String> boundValueOps = redisManager.getValueOperations(key);
        boundValueOps.set("hello");
        boundValueOps.set("world");
        boundValueOps.append("!");
        return Result.success(boundValueOps.get());
    }
}
