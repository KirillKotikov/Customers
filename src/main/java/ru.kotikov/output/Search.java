package ru.kotikov.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.kotikov.output.attributes.criteria;
import ru.kotikov.output.attributes.result;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Search {
    @Getter
    @JsonProperty("type")
    private final String type = "search";
    @Getter
    @Setter
    @JsonProperty("results")
    private List<LinkedHashMap<criteria, List<result>>> results = new ArrayList<>();
}
