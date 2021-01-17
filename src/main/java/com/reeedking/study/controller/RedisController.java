package com.reeedking.study.controller;

import com.reeedking.study.game.TicTacToeCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {

    @Autowired
    private TicTacToeCollection ticTacToeCollection;

    @GetMapping("/redis/{key}")
    public String get(@PathVariable("key") String key) {
        return ticTacToeCollection.get(key);
    }

    @PostMapping("/redis")
    public void add(@RequestParam("key") String key, @RequestParam("value") String value,
                    @RequestParam(name = "min", required = false) Integer min) {
        if (min == null)
            ticTacToeCollection.set(key, value);
        else
            ticTacToeCollection.setTimeoutMin(key, value, min);
    }

    @DeleteMapping("/redis/{key}")
    public void delete(@PathVariable("key") String key) {
        ticTacToeCollection.delete(key);
    }

}
