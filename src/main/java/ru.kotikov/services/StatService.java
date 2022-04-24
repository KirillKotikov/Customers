package ru.kotikov.services;

import ru.kotikov.dao.CustomerDao;
import ru.kotikov.dao.ProductDao;
import ru.kotikov.models.Customer;
import ru.kotikov.output.Stat;
import ru.kotikov.output.statAttributes.NameAndExpenses;
import ru.kotikov.output.statAttributes.NameAndPurchases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatService {

    public static Stat getStats(String startDate, String endDate) {
        Stat stat = new Stat();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            stat.setTotalDays((int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) + 1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<NameAndPurchases> nameAndPurchasesList = new ArrayList<>();


        List<Customer> customerList = CustomerDao.getNamesByDates(startDate, endDate);


        for (Customer customer : customerList) {
            List<String> productNames = ProductDao.getProductNamesByDateAndCustomer(startDate, endDate, customer);
            List<NameAndExpenses> nameAndExpensesList = new ArrayList<>();
            for (String productName : productNames) {
                NameAndExpenses nameAndExpenses = new NameAndExpenses(productName,
                        ProductDao.getProductExpensesByProductNameAndCustomerAndDates(startDate, endDate, customer, productName));
                nameAndExpensesList.add(nameAndExpenses);
            }
            NameAndPurchases nameAndPurchases = new NameAndPurchases(
                    customer.getLastName() + " " + customer.getFirstName(),
                    nameAndExpensesList,
                    nameAndExpensesList.stream()
                            .mapToDouble(NameAndExpenses::getExpenses).sum()
            );
            nameAndPurchasesList.add(nameAndPurchases);
            stat.setTotalExpenses(
                    nameAndPurchasesList.stream().mapToDouble(NameAndPurchases::getTotalExpensesForSum).sum()
            );
            stat.setAvgExpenses(
                    (nameAndPurchasesList.stream().mapToDouble(NameAndPurchases::getTotalExpensesForSum).sum() /
                     (long) nameAndPurchasesList.size())
            );
        }
        stat.setCustomers(nameAndPurchasesList);

        return stat;

    }
}
