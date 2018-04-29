package com.rbs.prime.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PrimeNumberControllerIntTest {

    @Autowired
    private MockMvc mvcMock;

    @Test
    public void testGivenANumberThenPrimeNumbersUptoAndIncludingReturnedJson() throws Exception {

        int initialValue = 10;

        mvcMock.perform(get(String.format("/primes/%s", initialValue))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.initial", is(initialValue)))
                .andExpect(jsonPath("$.primes", contains(2, 3, 5, 7)));
    }

    @Test
    public void testGivenANumberThenPrimeNumbersUptoAndIncludingReturnedXml() throws Exception {

        int initialValue = 10;

        mvcMock.perform(get(String.format("/primes/%s", initialValue))
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("/PrimeNumberResult/initial").string("10"))
                .andExpect(xpath("/PrimeNumberResult/primes").string("2357"));    }
}
