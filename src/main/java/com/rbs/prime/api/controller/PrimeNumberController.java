package com.rbs.prime.api.controller;

import com.rbs.prime.api.generator.PrimeNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public final class PrimeNumberController {

    @Autowired
    private PrimeNumberGenerator primeNumberGenerator;

    @GetMapping(path = "/primes/{initial}")
    public final PrimeNumberResult getPrimeNumbers(@PathVariable int initial) {
        List<Integer> primes = primeNumberGenerator.generate(initial);
        return new PrimeNumberResult(initial, primes);
    }
}
