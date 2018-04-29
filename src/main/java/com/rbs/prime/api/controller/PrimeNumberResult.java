package com.rbs.prime.api.controller;

import java.util.List;
import java.util.Collections;
import java.util.Objects;

public final class PrimeNumberResult {

    private final Integer initial;

    private final List<Integer> primes;

    public PrimeNumberResult() {
        this.initial = 0;
        this.primes = Collections.emptyList();
    }

    public PrimeNumberResult(final Integer initial, final List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public final List<Integer> getPrimes() {
        return primes;
    }

    public final Integer getInitial() {
        return initial;
    }

    @Override
    public final String toString() {
        return "PrimeNumberResult{" +
                "initial=" + initial +
                ", primes=" + primes +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimeNumberResult that = (PrimeNumberResult) o;
        return Objects.equals(initial, that.initial) &&
                Objects.equals(primes, that.primes);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(initial, primes);
    }


}
