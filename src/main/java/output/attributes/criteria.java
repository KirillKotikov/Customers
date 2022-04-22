package output.attributes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class criteria {
    @JsonProperty(value = "lastname")
    private String lastname;
    @JsonProperty(value = "productName")
    private String productName;
    @JsonProperty(value = "minTimes")
    private Integer minTimes;
    @JsonProperty(value = "minExpenses")
    private Integer minExpenses;
    @JsonProperty(value = "maxExpenses")
    private Integer maxExpenses;
    @JsonProperty(value = "badCustomers")
    private Integer badCustomers;
}
