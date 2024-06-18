package com.bnp.kata.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BookGroupInfoTest {

    private final Map<Integer, Integer> discountRates = initialiseDiscountRates();
    private BookGroupInfo bookGroupInfo;

    @BeforeEach
    void setUp() {
        bookGroupInfo = new BookGroupInfo(50, discountRates, 10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        bookGroupInfo.add(3);
        Assertions.assertEquals(1, bookGroupInfo.groupings().size());
        Assertions.assertEquals(1, bookGroupInfo.groupings().get(3));
        bookGroupInfo.add(5);
        Assertions.assertEquals(2, bookGroupInfo.groupings().size());
        Assertions.assertEquals(1, bookGroupInfo.groupings().get(3));
        Assertions.assertEquals(1, bookGroupInfo.groupings().get(5));
        bookGroupInfo.add(3);
        Assertions.assertEquals(2, bookGroupInfo.groupings().size());
        Assertions.assertEquals(2, bookGroupInfo.groupings().get(3));
        bookGroupInfo.add(3);
        Assertions.assertEquals(3, bookGroupInfo.groupings().get(3));
    }


    @Test
    void getTotalDiscountedPriceForExactlyFiveGroupings() {
        bookGroupInfo = new BookGroupInfo(50, discountRates, 15);
        bookGroupInfo.add(1);  // 0% discount
        bookGroupInfo.add(2);  // 5%
        bookGroupInfo.add(3);  // 10%
        bookGroupInfo.add(4);  // 20%
        bookGroupInfo.add(5);  // 25%
        Assertions.assertEquals(627.50, bookGroupInfo.getTotalDiscountedPrice());
        Assertions.assertEquals(15*50, bookGroupInfo.getTotalPrice());
    }

    @Test
    void getTotalDiscountedPriceFor3groupsOf4and2groupsOf5() {
        bookGroupInfo.add(4);
        bookGroupInfo.add(4);
        bookGroupInfo.add(4);
        bookGroupInfo.add(5);
        bookGroupInfo.add(5);
        Assertions.assertEquals(855.0, bookGroupInfo.getTotalDiscountedPrice());
    }

    @Test
    void getSingleBooksHAveNoDiscount() {
        bookGroupInfo = new BookGroupInfo(50, discountRates, 4);
        bookGroupInfo.add(1);  // 0% discount
        bookGroupInfo.add(1);  // 0% discount
        bookGroupInfo.add(1);  // 0% discount
        bookGroupInfo.add(1);  // 0% discount
        Assertions.assertEquals(1, bookGroupInfo.groupings().size());
        Assertions.assertEquals(4, bookGroupInfo.groupings().get(1));
        Assertions.assertEquals(200, bookGroupInfo.getTotalDiscountedPrice()); // no discount
        /* the total price should be the same as the total discount price since there is no discount */
        Assertions.assertEquals(bookGroupInfo.getTotalPrice(), bookGroupInfo.getTotalDiscountedPrice()); // no discount
    }

    @Test
    void getTotalPrice() {
        Assertions.assertEquals(500.0, bookGroupInfo.getTotalPrice()); // 10 books
    }

    private Map<Integer, Integer> initialiseDiscountRates() {
        Map<Integer, Integer> discountRates = new HashMap<>();
        discountRates.put(1, 0);  // no discount for individual books
        discountRates.put(2, 5);  // 5% discount for 2 books
        discountRates.put(3, 10); // 10% for 3 books
        discountRates.put(4, 20); // ..
        discountRates.put(5, 25); // ..
        return discountRates;
    }

}