package ru.kotikov.output.statAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.List;

@Data
@JsonPropertyOrder({ "name", "purchases", "totalExpenses" })
public class NameAndPurchases {
    @JsonIgnore
    private Double totalExpensesForSum;
    @JsonProperty("name")
    private String name;
    @JsonProperty("purchases")
    private List<NameAndExpenses> purchases;
    @JsonProperty("totalExpenses")
    private String totalExpensesForFile;

    public NameAndPurchases(String name, List<NameAndExpenses> purchases, Double totalExpenses) {
        this.name = name;
        this.purchases = purchases;
        this.totalExpensesForFile = new DecimalFormat("#0.00").format(totalExpenses);
        this.totalExpensesForSum = totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpensesForFile = new DecimalFormat("#0.00").format(totalExpenses);
        this.totalExpensesForSum = totalExpenses;
    }
}
