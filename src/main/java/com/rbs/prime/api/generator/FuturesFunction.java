package com.rbs.prime.api.generator;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class FuturesFunction extends AbstractFunction {

    @Override
    public List<Integer> apply(Integer number) {
        List<CompletableFuture<Number>> futures = IntStream
                .rangeClosed(2, number)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> processNumber(i)))
                .collect(Collectors.toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .filter(Number::isPrime)
                .map(Number::getValue)
                .sorted()
                .collect(Collectors.toList());
    }

    private Number processNumber(int number) {
        return new Number(number, isPrime(number));
    }

    private final class Number {
        private final int value;
        private boolean isPrime;

        public Number(int value, boolean isPrime) {
            this.value = value;
            this.isPrime = isPrime;
        }

        private final boolean isPrime() {
            return isPrime;
        }

        private final int getValue() {
            return value;
        }
    }
}
