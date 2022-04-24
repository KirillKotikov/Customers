package ru.kotikov.output.searchAttributes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Criteria {
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value = "lastname")
    private String lastname;
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value = "productName")
    private String productName;
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value = "minTimes")
    private Double minTimes;
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value = "minExpenses")
    private Double minExpenses;
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value = "maxExpenses")
    private Double maxExpenses;
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value = "badCustomers")
    private Double badCustomers;
}
