package output.attributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.Customer;

@Data
@AllArgsConstructor
public class result {
    private String lastName;
    private String firstName;

    public static result toResult(Customer customer) {
        return new result(customer.getFirstName(),
                customer.getLastName());
    }

}
