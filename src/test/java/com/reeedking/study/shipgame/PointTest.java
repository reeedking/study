package com.reeedking.study.shipgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    private Point point;
    private final int x = 12;
    private final int y = 21;

    @BeforeEach
    public void beforeTest() {
        point = new Point(x, y);
    }

    @Test
    public void whenInstantiatedThenXIsSet() {
        assertEquals(point.getX(), x);
    }

    @Test
    public void whenInstantiatedThenYIsSet() {
        assertEquals(point.getY(), y);
    }

}
