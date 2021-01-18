package com.reeedking.study.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisOperationService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;


    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setTimeoutMin(String key, String value, int min) {
        stringRedisTemplate.opsForValue().set(key, value, 3, TimeUnit.MINUTES);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

}
