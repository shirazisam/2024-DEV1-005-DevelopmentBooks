package com.bnp.kata.domain;

import java.util.HashMap;
import java.util.Map;

public class BookGroupInfo {

    private final int bookPrice;
    private final Map<Integer, Integer> discountRates;

    Map<Integer, Integer> groupings = new HashMap<>();


    public BookGroupInfo(int bookPrice, Map<Integer, Integer> discountRates) {
        this.bookPrice = bookPrice;
        this.discountRates = discountRates;
    }

    /**
     * Aggregate the books by group sizes (not titles!)
     * @param bookGroup the group size (1..5)
     */
    public void add(int bookGroup) {
        groupings.compute(bookGroup, (currentBookGroup, currentCount) -> currentCount == null ? 1 : currentCount + 1);
    }

    public Map<Integer, Integer> groupings() {
        return groupings;
    }
}
