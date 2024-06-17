package com.bnp.kata.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultsDto {
    private Integer bookPrice;
    private List<String> books;
    private Map<String, Long> bookCountByTitle;
    private Map<Integer, Integer> groupings;
    private Double totalPrice;
    private Double totalDiscountPrice;
    private Double percentageDiscount;
}
