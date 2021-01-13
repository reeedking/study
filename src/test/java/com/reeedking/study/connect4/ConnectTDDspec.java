package com.reeedking.study.connect4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ConnectTDDspec {

    private Connect4TDD tested;

    @BeforeEach
    public void before(){
        tested = new Connect4TDD();
    }

    @Test
    public void whenGameStartedTheBoardIsEmpty(){
        assertThat(tested.getNumberOfDiscs(), is(0));
    }
}
