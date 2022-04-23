package ru.kotikov.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
public class Purchase {
    private Customer customer;
    private Product product;
    private Date purchasesDate;

}
