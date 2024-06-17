package com.bnp.kata.infrastructure.adapter;

import com.bnp.kata.domain.ResultsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
public class BookDiscountService {

    private static final int BOOK_PRICE = 50;

    private final Map<Integer, Integer> discountRates = initialiseDiscountRates();

    private Map<Integer, Integer> initialiseDiscountRates() {
        Map<Integer, Integer> discountRates = new HashMap<>();
        discountRates.put(1, 0);  // no discount for individual books
        discountRates.put(2, 5);  // 5% discount for 2 books
        discountRates.put(3, 10); // 10% for 3 books
        discountRates.put(4, 20); // ..
        discountRates.put(5, 25); // ..
        return discountRates;
    }

    public ResultsDto calculateBookDiscounts(int nrBooks) {
        log.info("Calculating discounts for {} books",nrBooks);
        List<String> bookList = generateRandomBookList(nrBooks);
        Map<String, Long> bookCountByTitle = bookList.stream().collect(groupingBy(Function.identity(), counting()));

        return new ResultsDto("Calculating discounts for.. " + nrBooks, bookList, bookCountByTitle);
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
