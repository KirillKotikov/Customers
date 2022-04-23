package ru.kotikov.services;

import ru.kotikov.dao.CustomerDao;
import ru.kotikov.output.Search;
import ru.kotikov.output.attributes.criteria;
import ru.kotikov.output.attributes.result;
import ru.kotikov.parser.SearchLists;

import java.util.LinkedHashMap;
import java.util.List;

public class MainService {

    public static void getByLastName(SearchLists searchLists, Search resultSearch) {
        for (String s : searchLists.getLAST_NAMES()) {
            criteria cr = new criteria();
            cr.setLastname(s);
            LinkedHashMap<criteria, List<result>> results = new LinkedHashMap<>();
            results.put(cr, CustomerDao.getByLastName(s));
            resultSearch.getResults().add(results);
        }
    }

    public static void getByProductNameAndMinTimes(SearchLists searchLists, Search resultSearch) {
        for (int i = 0; i < searchLists.getPRODUCT_NAMES().size(); i++) {
            criteria cr = new criteria();
            String productName = searchLists.getPRODUCT_NAMES().get(i);
            Long minTimes = searchLists.getMIN_TIMES().get(i);
            cr.setProductName(productName);
            cr.setMinTimes(minTimes);
            LinkedHashMap<criteria, List<result>> results = new LinkedHashMap<>();
            results.put(cr, CustomerDao.getByProductNameAndMinTimes(productName, minTimes));
            resultSearch.getResults().add(results);
        }
    }

    public static void getByMinAndMaxExpenses(SearchLists searchLists, Search resultSearch) {
        for (int i = 0; i < searchLists.getMIN_EXPENSES().size(); i++) {
            criteria cr = new criteria();
            Long minExpenses = searchLists.getMIN_EXPENSES().get(i);
            Long maxExpenses = searchLists.getMAX_EXPENSES().get(i);
            cr.setMinExpenses(minExpenses);
            cr.setMaxExpenses(maxExpenses);
            LinkedHashMap<criteria, List<result>> results = new LinkedHashMap<>();
            results.put(cr, CustomerDao.getByMinAndMaxExpenses(minExpenses, maxExpenses));
            resultSearch.getResults().add(results);
        }
    }

    public static void getByBadCustomers(SearchLists searchLists, Search resultSearch) {
        for (Long l : searchLists.getBAD_CUSTOMERS()) {
            criteria cr = new criteria();
            cr.setBadCustomers(l);
            LinkedHashMap<criteria, List<result>> results = new LinkedHashMap<>();
            results.put(cr, CustomerDao.getByBadCustomers(l));
            resultSearch.getResults().add(results);
        }
    }
}
