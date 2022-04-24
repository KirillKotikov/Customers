package ru.kotikov.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.kotikov.output.searchAttributes.CriteriaAndResult;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Search {
    @Getter
    @JsonProperty("type")
    private final String TYPE = "search";
    @Getter
    @Setter
    @JsonProperty("results")
    private List<CriteriaAndResult> results = new ArrayList<>();
}
