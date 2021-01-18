package com.reeedking.study.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TicTacToeCollection {

    @Autowired
    private RedisTemplate redisTemplate;

}
