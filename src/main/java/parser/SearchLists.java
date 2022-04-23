package parser;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class SearchLists {

    private final List<String> LAST_NAMES;
    private final List<String> PRODUCT_NAMES;
    private final List<Long> MIN_TIMES;
    private final List<Long> MIN_EXPENSES;
    private final List<Long> MAX_EXPENSES;
    private final List<Long> BAD_CUSTOMERS;

    public SearchLists(List<String> lastNames, List<String> productNames, List<Long> minTimes,
                       List<Long> minExpenses, List<Long> maxExpenses, List<Long> badCustomers) {
        this.LAST_NAMES = lastNames;
        this.PRODUCT_NAMES = productNames;
        this.MIN_TIMES = minTimes;
        this.MIN_EXPENSES = minExpenses;
        this.MAX_EXPENSES = maxExpenses;
        this.BAD_CUSTOMERS = badCustomers;
    }
}
