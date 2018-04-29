package com.rbs.prime.api.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Calculate the prime number
 */
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


    /**
     * Generates the prime numbers upto and including the number provided.
     *
     * @param n initial number. Primes are generated up to and including.
     * @param function the function to generate prime numbers.
     * @return List of prime numbers
     */
    public List<Integer> generate(final int number, final Function<Integer, List<Integer>> function) {

        //for performance reason and to ensure the system doesn't break a limit is put into
        //to ensure we don't process a large number.
        if(number < 1 || number > 1_000_000) {
            throw new IllegalArgumentException(String.format("Specified number should be between 1 and 1,000,000 (inclusive). Supplied value is [%s]", number));
        }

        if(number == 1) {
            return Arrays.asList(number);
        }

        return function.apply(number);
    }
}
