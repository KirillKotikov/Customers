package ru.kotikov.output.statAttributes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NameAndExpenses {
    @JsonProperty("name")
    private String name;
    @JsonProperty("expenses")
    private Double expenses;

}
