package com.rbs.prime.api.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Simple sequential processing of numbers
 */
public class SequentialFunction extends AbstractFunction {

    @Override
    public List<Integer> apply(Integer number) {
        return IntStream.rangeClosed(2, number)
                .filter(this::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }
}
