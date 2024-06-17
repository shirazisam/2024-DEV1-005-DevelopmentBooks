package com.bnp.kata.infrastructure.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
public class BookDiscountService {

    public void calculateBookDiscounts(String nrBooks) {
        log.info("Calculating discounts for {} books",nrBooks);
        int n = Integer.parseInt(nrBooks);
        List<String> books = generateRandomBookList(n);
        books.forEach(System.out::println);
    }

    /**
     * Generate a random, sorted list of Book1..Book5
     * @param numberOfBooks the number of books in the shopping cart
     * @return the sorted list of random books
     */
    List<String> generateRandomBookList(int numberOfBooks) {
        return IntStream.range(0,numberOfBooks)
                .boxed()
                .map(c -> "Book " + getRandomNumberClosed(1,5))
                .sorted()
                .toList();
    }

    /**
     * Generate a random number
     * @param min lower boundary
     * @param max upper inclusive boundary
     * @return a random number in the range
     */
    public int getRandomNumberClosed(int min, int max) {
        return (int) ((Math.random() * (max - min +1)) + min);
    }

}
