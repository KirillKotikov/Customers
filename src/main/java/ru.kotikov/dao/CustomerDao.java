package ru.kotikov.dao;

import ru.kotikov.output.attributes.result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

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

    public static List<result> getByLastName(String lastName) {
        List<result> customers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM customer AS c WHERE c.last_name = '" + lastName + "'");
            while (resultSet.next()) {
                result resultCustomer = new result(resultSet.getString("first_name"),
                        resultSet.getString("last_name"));
                customers.add(resultCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static List<result> getByProductNameAndMinTimes(String productName, Long minTimes) {
        List<result> customers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT c.first_name AS first_name, c.last_name AS last_name, count(pr.product_name) FROM customer c " +
                    "JOIN purchase pu on c.id = pu.customer_id " +
                    "JOIN product pr on pu.product_id = pr.id " +
                    "WHERE pr.product_name = '" + productName + "' " +
                    "GROUP BY c.last_name, c.first_name;"
            );
            while (resultSet.next()) {
                if (resultSet.getLong("count") >= minTimes) {
                    result resultCustomer = new result(resultSet.getString("first_name"),
                            resultSet.getString("last_name"));
                    customers.add(resultCustomer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static List<result> getByMinAndMaxExpenses(Long minExpenses, Long maxExpenses) {
        List<result> customers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT c.first_name AS first_name, c.last_name AS last_name, SUM(pr.price) AS sum FROM customer c " +
                    "JOIN purchase pu on c.id = pu.customer_id " +
                    "JOIN product pr on pu.product_id = pr.id " +
                    "GROUP BY c.last_name, c.first_name;"
            );
            while (resultSet.next()) {
                if ((resultSet.getLong("sum") >= minExpenses) && (resultSet.getLong("sum") <= maxExpenses)) {
                    result resultCustomer = new result(resultSet.getString("first_name"),
                            resultSet.getString("last_name"));
                    customers.add(resultCustomer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static List<result> getByBadCustomers(Long badCustomers) {
        List<result> customers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT c.first_name AS first_name, c.last_name AS last_name, COUNT(pr.product_name) AS count FROM customer c " +
                    "JOIN purchase pu on c.id = pu.customer_id " +
                    "JOIN product pr on pu.product_id = pr.id " +
                    "GROUP BY c.last_name, c.first_name " +
                    "ORDER BY count;"
            );
            for (int i = 0; i < badCustomers; i++) {
                resultSet.next();
                result resultCustomer = new result(resultSet.getString("first_name"),
                        resultSet.getString("last_name"));
                customers.add(resultCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
