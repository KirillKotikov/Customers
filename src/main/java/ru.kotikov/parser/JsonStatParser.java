package ru.kotikov.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NonNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.kotikov.output.Error;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JsonStatParser {
    public static StatDates read(String inputFileName, String outputFileName) {
        StatDates statDates = new StatDates();
        try {
            File file = new File(inputFileName);
            // Создаем объект JSONParser(
            Object obj = new JSONParser().parse(new FileReader(file));
            // Кастим obj в JSONObject
            JSONObject jsonObject = (JSONObject) obj;
            // получаем данные из объекта
            try {
                if (isDateValid(String.valueOf(jsonObject.get("startDate")))) {
                    String startDate = String.valueOf(jsonObject.get("startDate"));
                    statDates.setStartDate(startDate);
                }
            } catch (java.text.ParseException e) {
                Error error = new Error("В поле startDate указан неверный формат даты. Пример верной даты - 2022-03-15");
                write(outputFileName, error);
                System.out.println("Ошибка! Информация в файле с именем - " + outputFileName);
                System.exit(0);
            }

            try {
                if (isDateValid(String.valueOf(jsonObject.get("endDate")))) {
                    String endDate = String.valueOf(jsonObject.get("endDate"));
                    statDates.setEndDate(endDate);
                }
            } catch (java.text.ParseException e) {
                Error error = new Error("В поле endDate указан неверный формат даты. Пример верной даты - 2022-03-15");
                write(outputFileName, error);
                System.out.println("Ошибка! Информация в файле с именем - " + outputFileName);
                System.exit(0);
            }
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


    public static boolean isDateValid(String date) throws java.text.ParseException {
        final String DATE_FORMAT = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
    }
}
