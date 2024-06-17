package com.bnp.kata.web;

import com.bnp.kata.domain.ResultsDto;
import com.bnp.kata.infrastructure.adapter.BookDiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookDiscountService bookDiscountService;

    public BookController(BookDiscountService bookDiscountService) {
        this.bookDiscountService = bookDiscountService;
    }

    @GetMapping
    public ResultsDto calculateDiscount(@RequestParam("nrbooks") String nrBooks) {
        int n;
        try {
            n = Integer.parseInt(nrBooks);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'nrbooks' must be a number", e);
        }
        bookDiscountService.calculateBookDiscounts(n);
        return new ResultsDto("number of random books " + nrBooks);
    }

}
