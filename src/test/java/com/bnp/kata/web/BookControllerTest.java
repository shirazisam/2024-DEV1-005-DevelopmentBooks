package com.bnp.kata.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void calculateDiscount() throws Exception {
        mvc.perform(get("/books?nrbooks=10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookPrice").value("50"));

    }

    @Test
    void calculateDiscountNrBooksNaN() throws Exception {
        mvc.perform(get("/books?nrbooks=hello"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertInstanceOf(ResponseStatusException.class, result.getResolvedException()))
                .andExpect(result -> Assertions.assertEquals("400 BAD_REQUEST \"'nrbooks' must be a number\"",
                        result.getResolvedException().getMessage()));

    }
}