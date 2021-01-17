package com.reeedking.study.controller;

import com.reeedking.study.game.TicTacToeCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TicTacToeCollection ticTacToeCollection;

    @GetMapping("/test")
    public String test(){
        ticTacToeCollection.set("test-key", "test-value");
        return "hallo";
    }
}
