package com.rbs.prime.api.generator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Simple function that takes advantage of
 * stream parallel functionality.
 */
@Component
public class ParallelFunction extends AbstractFunction {

    @Override
    public List<Integer> apply(Integer number) {
        return IntStream.rangeClosed(2, number)
                .parallel()
                .filter(this::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }
}
