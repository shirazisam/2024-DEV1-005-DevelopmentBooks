package com.bnp.kata.infrastructure.adapter;

import com.bnp.kata.domain.ResultsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class BookDiscountServiceTest {

    BookDiscountService bookDiscountService = new BookDiscountService();

    @Test
    void calculateBookDiscounts() {
        ResultsDto resultsDto = bookDiscountService.calculateBookDiscounts(11);
        Assertions.assertNotNull(resultsDto);
        Assertions.assertEquals(11, resultsDto.getBooks().size());
        var bookCountByTitle = resultsDto.getBookCountByTitle();
        AtomicLong totalBooks = new AtomicLong(0L);
        bookCountByTitle.forEach((k, v) -> totalBooks.addAndGet(v));
        /* sum of aggregates should add up to total number of books */
        Assertions.assertEquals(11, totalBooks.get());
    }

    @Test
    void getPercentageDiscountOn8books() {
        /* from the example in the kata */
        String book1 = "Book 1";
        String book2 = "Book 1";
        String book3 = "Book 2";
        String book4 = "Book 2";
        String book5 = "Book 3";
        String book6 = "Book 3";
        String book7 = "Book 4";
        String book8 = "Book 5";
        List<String> bookList = List.of(book1, book2, book3, book4, book5, book6, book7, book8);
        ResultsDto resultsDto = bookDiscountService.calculateBookDiscounts(bookList);
        Map<String, Long> bookCountByTitle = resultsDto.getBookCountByTitle();
        /* test the aggregation */
        Assertions.assertEquals(2, bookCountByTitle.get("Book 1"));
        Assertions.assertEquals(2, bookCountByTitle.get("Book 2"));
        Assertions.assertEquals(2, bookCountByTitle.get("Book 3"));
        Assertions.assertEquals(1, bookCountByTitle.get("Book 4"));
        Assertions.assertEquals(1, bookCountByTitle.get("Book 5"));
        /* groupings.. */
        Map<Integer, Integer> groupings = resultsDto.getGroupings();
        Assertions.assertEquals(1, groupings.get(5));  // 1 group of 5
        Assertions.assertEquals(1, groupings.get(3));  // 1 group of 3
        Assertions.assertNull(groupings.get(1));  // no single books
        Assertions.assertNull(groupings.get(2));  // no grouping of 2 books
        Assertions.assertNull(groupings.get(4));  // no grouping of 4 books
        Assertions.assertEquals(322.50, resultsDto.getTotalDiscountPrice());
        Assertions.assertEquals(400.0, resultsDto.getTotalPrice());
    }

    @Test
    void reduceBookCount() {
        String book1 = "Book 1";
        String book2 = "Book 1";
        String book3 = "Book 2";
        String book4 = "Book 2";
        String book5 = "Book 3";
        String book6 = "Book 3";
        String book7 = "Book 4";
        String book8 = "Book 5";
        List<String> bookList = List.of(book1, book2, book3, book4, book5, book6, book7, book8);
        Map<String, Long> bookCountByTitle = bookList.stream().collect(groupingBy(Function.identity(), counting()));
        Assertions.assertEquals(2, bookCountByTitle.get("Book 1"));
        Assertions.assertEquals(2, bookCountByTitle.get("Book 2"));
        Assertions.assertEquals(2, bookCountByTitle.get("Book 3"));
        Assertions.assertEquals(1, bookCountByTitle.get("Book 4"));
        Assertions.assertEquals(1, bookCountByTitle.get("Book 5"));
        bookCountByTitle = bookDiscountService.reduceBookCount(bookCountByTitle);
        Assertions.assertEquals(1, bookCountByTitle.get("Book 1"));
        Assertions.assertEquals(1, bookCountByTitle.get("Book 2"));
        Assertions.assertEquals(1, bookCountByTitle.get("Book 3"));
        Assertions.assertNull(bookCountByTitle.get("Book 4"));  // group is exhausted
        Assertions.assertNull(bookCountByTitle.get("Book 5"));  // group exhausted
        bookCountByTitle = bookDiscountService.reduceBookCount(bookCountByTitle);
        /* all groups have been reduced; no more book groups to count */
        Assertions.assertNull(bookCountByTitle.get("Book 1"));
        Assertions.assertNull(bookCountByTitle.get("Book 2"));
        Assertions.assertNull(bookCountByTitle.get("Book 3"));
        Assertions.assertNull(bookCountByTitle.get("Book 4"));
        Assertions.assertNull(bookCountByTitle.get("Book 5"));

    }
}