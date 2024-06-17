package com.bnp.kata.web;

import com.bnp.kata.infrastructure.adapter.BookDiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookDiscountService bookDiscountService;

    public BookController(BookDiscountService bookDiscountService) {
        this.bookDiscountService = bookDiscountService;
    }

    @GetMapping
    public String calculateDiscount(@RequestParam("nrbooks") String nrBooks) {
        bookDiscountService.calculateBookDiscounts(nrBooks);
        return "number of random books " + nrBooks;
    }

}
