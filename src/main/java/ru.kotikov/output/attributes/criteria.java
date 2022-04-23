package ru.kotikov.output.attributes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@JsonInclude(Include.NON_NULL)
public class criteria {
    @JsonProperty(value = "lastname")
    private String lastname;
    @JsonProperty(value = "productName")
    private String productName;
    @JsonProperty(value = "minTimes")
    private Long minTimes;
    @JsonProperty(value = "minExpenses")
    private Long minExpenses;
    @JsonProperty(value = "maxExpenses")
    private Long maxExpenses;
    @JsonProperty(value = "badCustomers")
    private Long badCustomers;
}
