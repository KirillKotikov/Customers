package ru.kotikov.output.searchAttributes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CriteriaAndResult {
    @JsonProperty("criteria")
    private Criteria criteria;
    @JsonProperty("results")
    private List<Results> results = new ArrayList<>();
}
