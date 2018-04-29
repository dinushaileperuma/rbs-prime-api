package com.rbs.prime.api.controller;


import com.rbs.prime.api.generator.PrimeNumberGenerator;
import com.rbs.prime.api.util.ClockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PrimeNumberController.class)
public class PrimeNumberControllerTest {

    @Autowired
    private MockMvc mvcMock;

    @MockBean
    private PrimeNumberGenerator primeGeneratorMock;

    @MockBean
    private ClockService clockServiceMock;

    @Test
    public void testWhenGetPrimeNumberRequestThenJsonResultReturned() throws Exception {

        int initialValue = 10;

        given(primeGeneratorMock.generate(initialValue)).willReturn(Arrays.asList(2, 3, 5, 7));

        mvcMock.perform(get(String.format("/primes/%s", initialValue))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.initial", is(initialValue)))
                .andExpect(jsonPath("$.primes", contains(2, 3, 5, 7)));
    }

    @Test
    public void testGivenAnInvalidNumberThenBadRequestErrorStatusReturned() throws Exception {

        int initialValue = -1;
        String message = "Illegal argument";
        String path = String.format("/primes/%s", initialValue);
        LocalDateTime localDateTime = LocalDateTime.parse("2018-04-29T12:30:10");

        given(primeGeneratorMock.generate(initialValue)).willThrow(new IllegalArgumentException(message));
        given(clockServiceMock.currentTime()).willReturn(localDateTime);

        mvcMock.perform(get(path)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(message)))
                .andExpect(jsonPath("$.details", is(String.format("uri=%s", path))))
                .andExpect(jsonPath("$.date", is(localDateTime.toString())));
    }


    @Test
    public void testWhenAcceptMediaTypeIsXmlThenXmlResponseReturned() throws Exception {

        int initialValue = 10;

        given(primeGeneratorMock.generate(initialValue)).willReturn(Arrays.asList(2, 3, 5, 7));

        mvcMock.perform(get(String.format("/primes/%s", initialValue))
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("/PrimeNumberResult/initial").string("10"))
                .andExpect(xpath("/PrimeNumberResult/primes").string("2357"));
    }

}
