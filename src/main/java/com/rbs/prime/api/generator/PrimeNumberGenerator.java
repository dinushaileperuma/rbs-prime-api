package com.rbs.prime.api.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Component
public class PrimeNumberGenerator {

    @Autowired
    private Function<Integer, List<Integer>> generateFunction;

    public PrimeNumberGenerator(final Function<Integer, List<Integer>> generateFunction) {
        this.generateFunction = generateFunction;
    }

    public List<Integer> generate(final int n) {
        return generate(n, generateFunction);
    }

    public List<Integer> generate(final int number, final Function<Integer, List<Integer>> function) {

        if(number < 1 || number > 1_000_000) {
            throw new IllegalArgumentException(String.format("Specified number should be between 1 and 1,000,000 (inclusive). Supplied value is [%s]", number));
        }

        if(number == 1) {
            return Arrays.asList(number);
        }

        return function.apply(number);
    }
}
