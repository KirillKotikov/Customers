package ru.kotikov;

import ru.kotikov.output.Search;
import ru.kotikov.parser.JsonSearchParser;
import ru.kotikov.parser.SearchLists;
import ru.kotikov.services.MainService;

public class Main {
    public static void main(String[] args) {

        System.out.println("Запустилось)\n");


        if (args[0].equalsIgnoreCase("search")) {
            if (args[1] != null) {
                SearchLists searchLists = new JsonSearchParser().read(args[1]);
                Search resultSearch = new Search();

                MainService.getByLastName(searchLists, resultSearch);

                MainService.getByProductNameAndMinTimes(searchLists, resultSearch);

                MainService.getByMinAndMaxExpenses(searchLists, resultSearch);

                MainService.getByBadCustomers(searchLists, resultSearch);

            } else if (args[1] == null) {

            }
        } else if (args[0] == null) {

        }

        System.out.println(new JsonSearchParser().read("InputTest.json"));

//        if (args[0].equalsIgnoreCase("search")) {
//         List<Customer> customerList = CustomerDao.getByLastName(args[1]);
//         for (Customer customer : customerList) {
//             System.out.println(customer);
//         }
//        }

//        for (Customer customer : CustomerDao.getByProductNameAndMinTimes("Пончик", 4)) {
//            System.out.println(customer);
//        }

//        for (Customer customer : CustomerDao.getByMinAndMaxExpenses(5000, 7000)) {
//            System.out.println(customer);
//        }

//        for (Customer customer : CustomerDao.getByBadCustomers(6)) {
//            System.out.println(customer);
//        }

        System.out.println("\n------OFF--------");

    }
}