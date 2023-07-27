package dev.aleixmorgadas.eventsourcedmongo;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@FunctionalInterface
interface HA<T, R> {
    R apply(T v);
}

interface H<T, R extends HA<T, ?>> extends HA<T, R> {
    R apply(T v);
}

interface TH<T, R> extends HA<T, R> {
    R apply(T v);
}

public class CurriedTest {

    static H<Integer, TH<Integer, Integer>> handle(int a, int b) {
        return xa -> xb -> a + xa + b + xb;
    }

    @Test
    void curriedTest() {
        var curriedHandler = handle(1, 2);
        System.out.println(curriedHandler.apply(1).apply(2));

        assertThat(true).isTrue();
    }
}
