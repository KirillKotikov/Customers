package ru.kotikov.dao;

import ru.kotikov.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/customers";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getProductNamesByDateAndCustomer(String startDate, String endDate, Customer customer) {
        List<String> productsNames = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT product_name, SUM(price) AS sum FROM product p " +
                    "JOIN purchase p2 on p.id = p2.product_id " +
                    "JOIN customer c on c.id = p2.customer_id " +
                    "WHERE p2.purchases_date > (to_date('" + startDate + "','YYYY-MM-DD') - 1) " +
                    "AND p2.purchases_date < (to_date('" + endDate + "','YYYY-MM-DD') + 1) " +
                    "AND c.last_name = '" + customer.getLastName() + "' " +
                    "AND c.first_name = '" + customer.getFirstName() + "' " +
                    "GROUP BY product_name " +
                    "ORDER BY sum DESC;"
            );
            while (resultSet.next()) {
                productsNames.add(resultSet.getString("product_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsNames;
    }

    public static Double getProductExpensesByProductNameAndCustomerAndDates(
            String startDate, String endDate, Customer customer, String productName) {
        Double productsExpenses = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT sum(p.price) FROM product p " +
                    "JOIN purchase p2 on p.id = p2.product_id " +
                    "JOIN customer c on c.id = p2.customer_id " +
                    "WHERE p2.purchases_date > (to_date('" + startDate + "','YYYY-MM-DD') - 1) " +
                    "AND p2.purchases_date < (to_date('" + endDate + "','YYYY-MM-DD') + 1) " +
                    "AND c.last_name = '" + customer.getLastName() + "' " +
                    "AND c.first_name = '" + customer.getFirstName() + "' " +
                    "AND p.product_name = '" + productName + "';"
            );
            while (resultSet.next()) {
                productsExpenses = resultSet.getDouble("sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsExpenses;
    }
}
