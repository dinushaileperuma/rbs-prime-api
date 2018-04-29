package com.rbs.prime.api.generator;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public abstract class AbstractFunction implements Function<Integer, List<Integer>> {


    @Cacheable(value = "primes")
    public boolean isPrime(Integer number) {
        return IntStream.range(2, number).noneMatch(i -> number % i == 0);
    }
}
