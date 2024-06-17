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
    private String message;
    List<String> books;
    Map<String, Long> bookCountByTitle;
    Map<Integer, Integer> groupings;
}
