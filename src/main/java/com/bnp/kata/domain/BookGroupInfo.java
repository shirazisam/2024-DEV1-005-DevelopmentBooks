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

}
