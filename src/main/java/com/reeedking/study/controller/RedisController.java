package com.reeedking.study.controller;

import com.reeedking.study.service.RedisOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {

    @Autowired
    private RedisOperationService redisOperationService;

    @GetMapping("/redis/{key}")
    public String get(@PathVariable("key") String key) {
        return redisOperationService.get(key);
    }

    @PostMapping("/redis")
    public void add(@RequestParam("key") String key, @RequestParam("value") String value,
                    @RequestParam(name = "min", required = false) Integer min) {
        if (min == null)
            redisOperationService.set(key, value);
        else
            redisOperationService.setTimeoutMin(key, value, min);
    }

    @DeleteMapping("/redis/{key}")
    public void delete(@PathVariable("key") String key) {
        redisOperationService.delete(key);
    }

}
