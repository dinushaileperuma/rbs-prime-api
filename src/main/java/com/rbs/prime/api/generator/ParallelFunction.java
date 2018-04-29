package com.rbs.prime.api.generator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
