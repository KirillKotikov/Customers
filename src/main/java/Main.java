import ru.kotikov.output.Search;
import ru.kotikov.output.Stat;
import ru.kotikov.parser.JsonSearchParser;
import ru.kotikov.parser.JsonStatParser;
import ru.kotikov.parser.SearchLists;
import ru.kotikov.parser.StatDates;
import ru.kotikov.services.SearchService;
import ru.kotikov.services.StatService;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(("--------------- !ОШИБКА! ---------------\n" +
                                "Вы не указали тип, путь к файлу для чтения " +
                                "и путь к файлу для внесения результатов запроса!"));
            System.exit(0);
        }
        if (args[0].equalsIgnoreCase("search")) {
            if (args.length > 1) {
                if (args.length > 2) {
                    SearchLists searchLists = JsonSearchParser.read(args[1], args[2]);
                    Search resultSearch = new Search();

                    SearchService.getByLastName(searchLists, resultSearch);

                    SearchService.getByProductNameAndMinTimes(searchLists, resultSearch);

                    SearchService.getByMinAndMaxExpenses(searchLists, resultSearch);

                    SearchService.getByBadCustomers(searchLists, resultSearch);

                    JsonSearchParser.write(args[2], resultSearch);
                    System.out.println("Операция прошла успешно!\nРезультат в файле с именем - " + args[2]);
                } else {
                    System.out.println("--------------- !ОШИБКА! ---------------\n" +
                                        "Вы не указали путь к файлу для внесения результатов запроса!");
                }
            } else {
                System.out.println("--------------- !ОШИБКА! ---------------\n" +
                                    "Вы не указали путь к файлу для чтения и записи результатов запроса!");
            }
        } else if (args[0].equalsIgnoreCase("stat")) {

            StatDates statDates = JsonStatParser.read(args[1], args[2]);
            Stat stat = StatService.getStats(statDates.getStartDate(), statDates.getEndDate());
            JsonStatParser.write(args[2], stat);
            System.out.println("Операция прошла успешно!\nРезультат в файле с именем - " + args[2]);
        } else {
            System.out.println("Вы ввели неверную комманду в args[0]! Возможны команды \"search\" и \"stat\".");
        }
    }
}