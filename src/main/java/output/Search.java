package output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import output.attributes.criteria;
import output.attributes.result;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class Search {
    @JsonProperty("type")
    private String type = "search";
    @JsonProperty("results")
    private List<LinkedHashMap<criteria, List<result>>> results = new ArrayList<>();
}
