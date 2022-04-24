package ru.kotikov.output.searchAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.kotikov.models.Customer;

@Data
@AllArgsConstructor
public class Results {
    private String lastName;
    private String firstName;

    public static Results toResult(Customer customer) {
        return new Results(customer.getFirstName(),
                customer.getLastName());
    }

}
