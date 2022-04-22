package parser;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchLists {

    private final List<String> LAST_NAMES;
    private final List<String> PRODUCT_NAMES;
    private final List<Integer> MIN_TIMES;
    private final List<Integer> MIN_EXPENSES;
    private final List<Integer> MAX_EXPENSES;
    private final List<Integer> BAD_CUSTOMERS;

    public SearchLists(List<String> lastNames, List<String> productNames, List<Integer> minTimes,
                       List<Integer> minExpenses, List<Integer> maxExpenses, List<Integer> badCustomers) {
        this.LAST_NAMES = lastNames;
        this.PRODUCT_NAMES = productNames;
        this.MIN_TIMES = minTimes;
        this.MIN_EXPENSES = minExpenses;
        this.MAX_EXPENSES = maxExpenses;
        this.BAD_CUSTOMERS = badCustomers;
    }
}
