import dao.CustomerDao;
import models.Customer;

public class Main {
    public static void main(String[] args) {

        System.out.println("Запустилось)\n");

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

        for (Customer customer : CustomerDao.getByBadCustomers(6)) {
            System.out.println(customer);
        }
        System.out.println("------OFF--------");

    }
}