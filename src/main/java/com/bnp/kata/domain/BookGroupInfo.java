package com.bnp.kata.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BookGroupInfo {

    private final int bookPrice;
    private final Map<Integer, Integer> discountRates;
    private final int numberOfBooks;

    Map<Integer, Integer> groupings = new HashMap<>();


    public BookGroupInfo(int bookPrice, Map<Integer, Integer> discountRates, int nrBooks) {
        this.bookPrice = bookPrice;
        this.discountRates = discountRates;
        this.numberOfBooks = nrBooks;
    }

    /**
     * Aggregate the books by group sizes (not titles!)
     * If the bookGroup already exists, increment it, otherwise start a new group
     * @param bookGroup the group size where the key is (1 - 5)
     */
    public void add(int bookGroup) {
        groupings.compute(bookGroup, (currentBookGroup, currentCount) -> currentCount == null ? 1 : currentCount + 1);
    }

    public Map<Integer, Integer> groupings() {
        return groupings;
    }

    public void displayTotalDiscountsByGroup() {
        groupings.forEach((bookGroup, groupCount) -> {
            Integer discountPc = discountRates.get(bookGroup);
            double factor = 1 - (double) discountPc/100;
            log.info(groupCount + " x " + bookGroup + " x " + bookPrice + " (-" + discountPc + "%) = " + groupCount*bookGroup * bookPrice * factor);
        });
    }

    public Double getTotalDiscountedPrice() {
        double totalDiscountPrice = 0.0;
        for (Integer bookGroup : groupings.keySet()) {
            Integer groupCount = groupings.get(bookGroup);
            Integer discountPc = discountRates.get(bookGroup);
            double factor = 1 - (double) discountPc / 100;  // % as a decimal (0..1)
            totalDiscountPrice += (groupCount * bookGroup * bookPrice * factor);
        }
        return totalDiscountPrice;
    }

    public Double getTotalPrice() {
        return (double) (bookPrice * numberOfBooks);
    }

    public Double getPercentageDiscount() {
        Double totalPrice = getTotalPrice();
        return (totalPrice - getTotalDiscountedPrice()) * 100 / totalPrice;
    }

}
