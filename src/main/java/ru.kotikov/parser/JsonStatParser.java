package ru.kotikov.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NonNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonStatParser {
    public static StatDates read(String fileName) {
        StatDates statDates = null;
        try {
            File file = new File(fileName);
            // Создаем объект JSONParser(
            Object obj = new JSONParser().parse(new FileReader(file));
            // Кастим obj в JSONObject
            JSONObject jsonObject = (JSONObject) obj;
            // получаем данные из объекта
            String startDate = String.valueOf(jsonObject.get("startDate"));
            String endDate = String.valueOf(jsonObject.get("endDate"));
            // сохраняем данные в класс
            statDates = new StatDates(startDate, endDate);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return statDates;
    }

    public static void write(@NonNull String fileName, Object object) {
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
