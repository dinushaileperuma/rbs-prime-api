package com.rbs.prime.api.generator;


import java.util.List;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.contains;

public class PrimeNumberGeneratorTest {

    @Rule
    public ExpectedException exceptionThrown = ExpectedException.none();

    private SequentialFunction sequentialFunction = new SequentialFunction();
    private PrimeNumberGenerator generator = new PrimeNumberGenerator(sequentialFunction);

    @Test
    public void testWhenNumberIs10ThenAllPrimeNumberBelowAreGenerated() {
        List<Integer> results = generator.generate(10);
        assertThat(results, contains(2, 3, 5, 7));
    }

    @Test
    public void testWhenNumberIs5ThenAllPrimeNumberBelowAndIncludingThePrimeIsGenerated() {
        List<Integer> results = generator.generate(5);
        assertThat(results, contains(2, 3, 5));
    }

    @Test
    public void testWhenNumberIs1Then1IsGenerated() {
        List<Integer> results = generator.generate(1);
        assertThat(results, contains(1));
    }

    @Test
    public void testWhenInitialNumberIsLessThan1ThenExceptionIsThrown() {
        exceptionThrown.expect(IllegalArgumentException.class);
        exceptionThrown.expectMessage("Specified number should be between 1 and 1,000,000 (inclusive). Supplied value is [0]");
        generator.generate(0);
    }

    @Test
    public void testWhenNumberIs10ThenAllPrimeNumberBelowAreGenerated_Parallel() {
        List<Integer> results = generator.generate(10, new ParallelFunction());
        assertThat(results, contains(2, 3, 5, 7));
    }

    @Test
    public void testWhenNumberIs10ThenAllPrimeNumberBelowAreGenerated_Concurrent() {
        List<Integer> results = generator.generate(10, new FuturesFunction());
        assertThat(results, contains(2, 3, 5, 7));
    }

    @Test
    public void testWhenInitialNumberIsMoreThan1000000ThenExceptionIsThrown() {
        exceptionThrown.expect(IllegalArgumentException.class);
        exceptionThrown.expectMessage("Specified number should be between 1 and 1,000,000 (inclusive). Supplied value is [1000001]");
        generator.generate(1_000_001);
    }


}
