package ru.kotikov.output.attributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.kotikov.models.Customer;

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
