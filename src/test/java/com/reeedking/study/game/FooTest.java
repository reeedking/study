package com.reeedking.study.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FooTest {

    @Test
    public void whenDoFooThenThrowRuntimeException() {
        Foo foo = new Foo();
        RuntimeException runtimeException = assertThrows(RuntimeException.class, foo::doFoo,
                "foo.doFoo() excepted throw RuntimeException");
       // assertTrue(runtimeException.getMessage().contains("reeedking"));
    }

}
