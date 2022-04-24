package ru.kotikov.parser;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class SearchLists {

    private final List<String> LAST_NAMES;
    private final List<String> PRODUCT_NAMES;
    private final List<Double> MIN_TIMES;
    private final List<Double> MIN_EXPENSES;
    private final List<Double> MAX_EXPENSES;
    private final List<Double> BAD_CUSTOMERS;

    public SearchLists(List<String> lastNames, List<String> productNames, List<Double> minTimes,
                       List<Double> minExpenses, List<Double> maxExpenses, List<Double> badCustomers) {
        this.LAST_NAMES = lastNames;
        this.PRODUCT_NAMES = productNames;
        this.MIN_TIMES = minTimes;
        this.MIN_EXPENSES = minExpenses;
        this.MAX_EXPENSES = maxExpenses;
        this.BAD_CUSTOMERS = badCustomers;
    }
}
