package parser;

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

public class JsonSearchParser implements Parser {
    @Override
    public SearchLists read(File file) {
        SearchLists searchLists = null;
        try {
            // Создаем объект JSONParser(
            Object obj = new JSONParser().parse(new FileReader(file));
            // Кастим obj в JSONObject
            JSONObject jsonObject = (JSONObject) obj;
            // Берем из файла данные step, которые внутри game
            JSONArray criterias = (JSONArray) jsonObject.get("criterias");
            // цикл для чтения критериев
            List<String> lastNames = new ArrayList<>();
            List<String> productNames = new ArrayList<>();
            List<Integer> minTimes = new ArrayList<>();
            List<Integer> minExpenses = new ArrayList<>();
            List<Integer> maxExpenses = new ArrayList<>();
            List<Integer> badCustomers = new ArrayList<>();
            for (Object o : criterias) {
                // кастим объект в json объект
                JSONObject criteria = (JSONObject) o;
                // получаем фамилию и сохраняем в список для поиска
                lastNames.add(String.valueOf(criteria.get("lastName")));
                // получение имени продукта и минимального кол-ва покупов и добавляем для поиска
                productNames.add(String.valueOf(criteria.get("productName")));
                minTimes.add((Integer) criteria.get("minTimes"));
                // получение минимальной и максимальной суммы всех покупок для поиска
                minExpenses.add((Integer) criteria.get("minExpenses"));
                maxExpenses.add((Integer) criteria.get("maxExpenses"));
                // получение и поиск плохих покупателей
                badCustomers.add((Integer) criteria.get("badCustomers"));
                searchLists = new SearchLists(lastNames, productNames, minTimes, minExpenses, maxExpenses, badCustomers);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return searchLists;
    }

    @Override
    public void write(@NonNull File file, Object object) {
        // создаем объект с опциями красивой записи json файла
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            // записываем объект в файл
            mapper.writeValue(file, object);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
