package ru.kotikov.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NonNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonSearchParser {
    public SearchLists read(String fileName) {
        SearchLists searchLists = null;
        try {
            File file = new File(fileName);
            // Создаем объект JSONParser(
            Object obj = new JSONParser().parse(new FileReader(file));
            // Кастим obj в JSONObject
            JSONObject jsonObject = (JSONObject) obj;
            // Берем из файла данные step, которые внутри game
            JSONArray criterias = (JSONArray) jsonObject.get("criterias");
            // цикл для чтения критериев
            List<String> lastNames = new ArrayList<>();
            List<String> productNames = new ArrayList<>();
            List<Long> minTimes = new ArrayList<>();
            List<Long> minExpenses = new ArrayList<>();
            List<Long> maxExpenses = new ArrayList<>();
            List<Long> badCustomers = new ArrayList<>();
            for (Object o : criterias) {
                // кастим объект в json объект
                JSONObject criteria = (JSONObject) o;
                // получаем фамилию и сохраняем в список для поиска
                if (criteria.get("lastName") != null) lastNames.add(String.valueOf(criteria.get("lastName")));
                // получение имени продукта и минимального кол-ва покупов и добавляем для поиска
                if (criteria.get("productName") != null) productNames.add(String.valueOf(criteria.get("productName")));
                if (criteria.get("minTimes") != null)minTimes.add((Long) criteria.get("minTimes"));
                // получение минимальной и максимальной суммы всех покупок для поиска
                if (criteria.get("minExpenses") != null)minExpenses.add((Long) criteria.get("minExpenses"));
                if (criteria.get("maxExpenses") != null)maxExpenses.add((Long) criteria.get("maxExpenses"));
                // получение и поиск плохих покупателей
                if (criteria.get("badCustomers") != null)badCustomers.add((Long) criteria.get("badCustomers"));
                searchLists = new SearchLists(lastNames, productNames, minTimes, minExpenses, maxExpenses, badCustomers);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return searchLists;
    }

    public void write(@NonNull String fileName, Object object) {
        // создаем объект с опциями красивой записи json файла
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File file = new File(fileName);
            // записываем объект в файл
            mapper.writeValue(file, object);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
