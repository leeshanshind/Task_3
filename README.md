# Task_3

UI-тесты веб-приложения [Stellar Burgers](https://qa-stellarburgers.education-services.ru/): Java 11, JUnit 5, Selenium, WebDriverManager, Allure.

## Запуск

Google Chrome:

```
mvn clean test
```

Яндекс.Браузер (`yandex.version` — major-версия Chromium, на которой собран Яндекс.Браузер):

```
mvn test -Dbrowser=yandex -Dyandex.version=<major>
```

Путь к браузеру по умолчанию — `/Applications/Yandex.app/Contents/MacOS/Yandex`, другой можно задать через `-Dyandex.binary=<path>`.

Отчёт Allure:

```
mvn allure:serve
```
