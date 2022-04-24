package ru.kotikov.services;

import ru.kotikov.dao.CustomerDao;
import ru.kotikov.output.Search;
import ru.kotikov.output.searchAttributes.Criteria;
import ru.kotikov.output.searchAttributes.CriteriaAndResult;
import ru.kotikov.parser.SearchLists;

public class SearchService {

    public static void getByLastName(SearchLists searchLists, Search resultSearch) {
        for (String s : searchLists.getLAST_NAMES()) {
            Criteria cr = new Criteria();
            cr.setLastname(s);
            CriteriaAndResult criteriaAndResult = new CriteriaAndResult();
            criteriaAndResult.setCriteria(cr);
            criteriaAndResult.setResults(CustomerDao.getByLastName(s));
            resultSearch.getResults().add(criteriaAndResult);

//            LinkedHashMap<Criteria, List<Results>> Results = new LinkedHashMap<>();
//            Results.put(cr, CustomerDao.getByLastName(s));
//            resultSearch.getResults().put(cr, CustomerDao.getByLastName(s));
//            resultSearch.getResults().add(cr);
//            resultSearch.getResults().add(CustomerDao.getByLastName(s));
        }
    }

    public static void getByProductNameAndMinTimes(SearchLists searchLists, Search resultSearch) {
        for (int i = 0; i < searchLists.getPRODUCT_NAMES().size(); i++) {
            Criteria cr = new Criteria();
            String productName = searchLists.getPRODUCT_NAMES().get(i);
            Double minTimes = searchLists.getMIN_TIMES().get(i);
            cr.setProductName(productName);
            cr.setMinTimes(minTimes);
            CriteriaAndResult criteriaAndResult = new CriteriaAndResult();
            criteriaAndResult.setCriteria(cr);
            criteriaAndResult.setResults(CustomerDao.getByProductNameAndMinTimes(productName, minTimes));
            resultSearch.getResults().add(criteriaAndResult);

//            LinkedHashMap<Criteria, List<Results>> Results = new LinkedHashMap<>();
//            Results.put(cr, CustomerDao.getByProductNameAndMinTimes(productName, minTimes));
//            resultSearch.getResults().put(cr, CustomerDao.getByProductNameAndMinTimes(productName, minTimes));
//            resultSearch.getResults().add(cr);
//            resultSearch.getResults().add(CustomerDao.getByProductNameAndMinTimes(productName, minTimes));
        }
    }

    public static void getByMinAndMaxExpenses(SearchLists searchLists, Search resultSearch) {
        for (int i = 0; i < searchLists.getMIN_EXPENSES().size(); i++) {
            Criteria cr = new Criteria();
            Double minExpenses = searchLists.getMIN_EXPENSES().get(i);
            Double maxExpenses = searchLists.getMAX_EXPENSES().get(i);
            cr.setMinExpenses(minExpenses);
            cr.setMaxExpenses(maxExpenses);
            CriteriaAndResult criteriaAndResult = new CriteriaAndResult();
            criteriaAndResult.setCriteria(cr);
            criteriaAndResult.setResults(CustomerDao.getByMinAndMaxExpenses(minExpenses, maxExpenses));
            resultSearch.getResults().add(criteriaAndResult);

//            LinkedHashMap<Criteria, List<Results>> results = new LinkedHashMap<>();
//            results.put(cr, CustomerDao.getByMinAndMaxExpenses(minExpenses, maxExpenses));
//            resultSearch.getResults().add(results);
//            resultSearch.getResults().put(cr, CustomerDao.getByMinAndMaxExpenses(minExpenses, maxExpenses));
//            resultSearch.getResults().add(cr);
//            resultSearch.getResults().add(CustomerDao.getByMinAndMaxExpenses(minExpenses, maxExpenses));
        }
    }

    public static void getByBadCustomers(SearchLists searchLists, Search resultSearch) {
        for (Double l : searchLists.getBAD_CUSTOMERS()) {
            Criteria cr = new Criteria();
            cr.setBadCustomers(l);
            CriteriaAndResult criteriaAndResult = new CriteriaAndResult();
            criteriaAndResult.setCriteria(cr);
            criteriaAndResult.setResults(CustomerDao.getByBadCustomers(l));
            resultSearch.getResults().add(criteriaAndResult);

//            LinkedHashMap<Criteria, List<Results>> Results = new LinkedHashMap<>();
//            Results.put(cr, CustomerDao.getByBadCustomers(l));
//            resultSearch.getResults().put(cr, CustomerDao.getByBadCustomers(l));
//            resultSearch.getResults().add(cr);
//            resultSearch.getResults().add(CustomerDao.getByBadCustomers(l));
        }
    }
}
