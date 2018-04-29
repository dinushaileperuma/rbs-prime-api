package com.rbs.prime.api.controller;

import com.rbs.prime.api.generator.PrimeNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A RESTful service that calculates and returns all the prime numbers up to an including a number provided.
 */
@RestController()
public final class PrimeNumberController {

    @Autowired
    private PrimeNumberGenerator primeNumberGenerator;


    /**
     * Calculates and returns all the prime numbers up to an including a number provided.
     *
     * URL:
     * /primes/{initial}
     *
     * initial - the number to general the prime numbers. It should be great than 1.]
     * e.g. /primes/10 will generate the prime numbers 2, 3, 5, 7
     *
     *
     * METHOD TYPE: GET
     * HEADER:
     * ACCEPT aplication/xml | plication/json
     *
     * URL Params: No params
     *
     * SUCCESS RESPONSE: 200
     * Media-Type: JSON or XML
     *
     * JSON Content:
     *  <pre>
     *      {
     *           “Initial”:  “10
     *           “Primes”: [2,3,5,7]
     *       }
     *  </pre>
     *
     * ERROR RESPONSE: 400
     * <pre>
     *  {
     *      "date":"2018-04-29T12:55:32.139",
     *      "message":"Specified number should be between 1 and 1,000,000 (inclusive). Supplied value is [-1]",
     *      "details":"uri=/primes/-1"
     *  }
     * </pre>
     *
     *
     *
     * @param initial Initial number provided to calculate the prime numbers. The number needs to be
     *                greater than 1 and less than 1,000,000
     *
     * @return <code>PrimeNumberResult</code>. Contains the <code>initial</code> number and
     *         all prime numbers up to and including the number provided.
     */
    @GetMapping(path = "/primes/{initial}")
    public final PrimeNumberResult getPrimeNumbers(@PathVariable Integer initial) {
        List<Integer> primes = primeNumberGenerator.generate(initial);
        return new PrimeNumberResult(initial, primes);
    }
}
