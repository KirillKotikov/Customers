### Customer's stat
---
<h1> Описание </h1>
<h3>  Данное приложение предоставляет сервис работы с данными в БД. 
Данный сервис, на основании входных параметров (аргументы командной строки), 
типа операции и входного файла – извлекает необходимые данные из БД и формирует результат обработки в выходной файл. 
Все возможные ошибки обработаны и зафиксированы в выходном файле. </h3>

---
<h1> Использованные технологии:</h1>
<h3>Java 8, PostgreSQL, Maven.
</h3>

---
<h1> Инструкция установки и запуска</h1>
<h3>
1) Для запуска проекта необходимо скачать в одну папку следующие файлы
из корня проекта: Customers-1.0-SNAPSHOT.jar, jackson-annotations-2.9.0.jar, 
jackson-core-2.9.5.jar, jackson-databind-2.9.5.jar, json-simple-1.1.jar, postgresql-42.3.4.jar. <br/>
2) Для работы PostgreSQL вам необходимо создать базу данных со следующим адресом - jdbc:postgresql://localhost:5432/customers.<br/>
3) В корне проекта есть DDL (DDL_script.sql) и DML (DML_script.sql) скрипты для создания таблиц и внесения в них данных, которые нужно скопировать 
в консоль запросов к базе данных и запустить.<br/>
4) Запуск приложения производится из консоли. Входные параметры: 
тип операции, путь к входному файлу, путь к файлу результата. 
Формат файлов - JSON. <br/>
Пример запуска: -cp postgresql-42.3.4.jar:json-simple-1.1.jar:jackson-databind-2.9.5.jar:jackson-core-2.9.5.jar:jackson-annotations-2.9.0.jar:Customers-1.0-SNAPSHOT.jar Main stat InputTestBad.json OutputTestBad.json
<br/> Где "Main" - указатель главного класса для запуска (с методом main),
"stat" - тип операции (он может быть только "stat" или "search", 
"InputTestBad.json" - файл с параметрами запроса (пример будет приведён ниже),
"OutputTestBad.json" - файл с результатом обработки запроса. <br/>
P.S. Я понимаю, что запрос при помощи -cp и ручного добавления библиотек некрасиво, 
но на данный момент я не смог решить проблему с ошибкой "ClassNotFoundException" (хотя задействован Maven)
и решил опубликовать проект хоть и в таком, но рабочем виде. 
Если у Вас есть решение данной проблемы в моём случае, буду премного благодарен. :) 
</h3>

<h1> Типы и Описание операций (операции определяются по входному параметру): </h1>

---
<h2> Тип "search" </h2>
<h3> При поиске покупателей по критериям (search) во входном файле передаётся 
список критериев для поиска покупателей. Результат операции - списки покупателей
для каждого критерия из запроса. Порядок списков такой же как в запросе, порядок
покупателей в списке — произвольный.
<br/><br/>
Критерии:<br/>
- Фамилия — поиск покупателей с этой фамилией. <br/>
- Название товара и число раз — поиск покупателей, купивших этот товар не менее, чем указанное число раз.<br/>
- Минимальная и максимальная стоимость всех покупок — поиск покупателей, у которых общая стоимость всех покупок за всё время попадает в интервал.<br/>
- Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров. Возвращается не более, чем указанное число покупателей.<br/><br/>
Критерии могут повторяться, например, два раза критерий с lastName и т.п.
Примеры входных файлов вы можете найти в корне проекта: "InputTestSearchGood.json" - с валидными параметрами
и "InputTestSearchBad.json" - с неправильными параметрами.<br/><br/>
Пример входного файла:<br/>
{<br/>
"criterias": [<br/>
{"lastName": "Иванов"}, //Фамилия<br/>
{"productName": "Минеральная вода", "minTimes": 5}, // Название товара и число раз<br/>
{"minExpenses": 112, "maxExpenses": 4000}, //Минимальная и максимальная стоимость всех покупок<br/>
{"badCustomers": 3} //Число пассивных покупателей<br/>
]<br/>
}<br/><br/>
Пример выходного файла:<br/>
{<br/>
"type": "search", // Тип результата<br/>
"results": [ // Списки покупателей<br/>
{<br/>
"criteria": {"lastName": "Иванов"}, // Критерий из запроса<br/>
"results": [ // Список покупателей<br/>
{"lastName: "Иванов", "firstName": "Антон"}, // Фамилия и имя покупателя<br/>
{"lastName: "Иванов", "firstName": "Николай"}<br/>
...<br/>
]<br/>
},<br/>
{<br/>
"criteria": {"productName": "Минеральная вода", "minTimes": 5}, // Критерий из запроса<br/>
"results": [<br/>
{"lastName": "Петров", "firstName": "Валентин"}, // Фамилия и имя покупателя<br/>
...<br/>
]<br/>
},<br/>
...<br/>
]<br/>
}<br/> </h3>

<h2> Тип "stat"</h2>
<h3> Во входном файле передаётся интервал дат сбора статистики. 
Результат операции - статистика по покупателям за период из двух дат,
включительнo. <br/>
Пример входного файла:<br/>
{<br/>
"startDate": "2020-01-14", // Начальная дата<br/>
"endDate": "2020-01-26" // Конечная дата<br/>
}</h3>
<h3>
Пример выходного файла:<br/>
{<br/>
"type": "stat" // Тип результата<br/>
"totalDays": 9, // Общее число дней за период из двух дат, включительно, без выходных<br/>
"customers": [ // Данные по покупателям за этот период, упорядоченные по общей стоимости п<br/>окупок по убыванию<br/>
{ // Данные первого покупателя<br/>
"name": "Иванов Антон", // Фамилия и имя покупателя<br/>
"purchases": [ // Список всех уникальных товаров, купленных покупателем за этот период, упорядоченных по суммарной стоимости по убыванию<br/>
{<br/>
"name": "Хлеб", // Название товара<br/>
"expenses": 540 // Суммарная стоимость всех покупок этого товара за период<br/>
},<br/>
{<br/>
"name": "Сметана",<br/>
"expenses": 517<br/>
},<br/>
{<br/>
"name": "Колбаса",<br/>
"expenses": 332<br/>
},<br/>
...<br/>
],<br/>
"totalExpenses": 4100 // Общая стоимость покупок этого покупателя за период (то есть сумма всех стоимостей покупок всех товаров)<br/>
},<br/>
{ // Данные второго покупателя<br/>
"name": "Петров Валентин",<br/>
"purchases": [<br/>
{<br/>
"name": "Сыр",<br/>
<br/>
},<br/>
{<br/>
"name": "Хлеб",<br/>
"expenses": 300<br/>
},<br/>
{<br/>
"name": "Минеральная вода",<br/>
"expenses": 120<br/>
},<br/>
...<br/>
],<br/>
"totalExpenses": 3700<br/>
},<br/>
...<br/>
],<br/>
"totalExpenses": 19920, // Сумма покупок всех покупателей за период<br/>
"avgExpenses": 3455.72 // Средние затраты всех покупателей за период<br/>
}</h3>

<h2> В случае возникновения ошибки, при выполнении любой операции - фиксируется результат:</h2>
<h3>
Пример выходного файла с ошибкой:<br/>
{<br/>
"type": "error", // Тип результата<br/>
"message": "Неправильный формат даты" // Описание ошибки<br/>
} </h3>
