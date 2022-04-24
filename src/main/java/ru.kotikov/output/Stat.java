package ru.kotikov.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.kotikov.output.statAttributes.NameAndPurchases;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Stat {
    @Getter
    @JsonProperty("type")
    private final String TYPE = "stat";
    @Getter
    @Setter
    @JsonProperty("totalDays")
    private int totalDays;
    @Getter
    @Setter
    @JsonProperty("customers")
    List<NameAndPurchases> customers = new ArrayList<>();
    @Getter
    @JsonProperty("totalExpenses")
    private String totalExpenses;
    @Getter
    @JsonProperty("avgExpenses")
    private String avgExpenses;

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = new DecimalFormat("#0.00").format(totalExpenses);
    }

    public void setAvgExpenses(Double avgExpenses) {
        this.avgExpenses = new DecimalFormat("#0.00").format(avgExpenses);
    }
}
