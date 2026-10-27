# Task_3

UI-тесты веб-приложения [Stellar Burgers](https://qa-stellarburgers.education-services.ru/): Java 11, JUnit 5, Selenium, WebDriverManager, Allure.

## Запуск

Google Chrome:

```
mvn clean test
```

Яндекс.Браузер:

```
mvn test -Dbrowser=yandex
```

По умолчанию берётся chromedriver под Chromium 152 (Яндекс.Браузер 26.10) и браузер из `/Applications/Yandex.app/Contents/MacOS/Yandex`.
Для другой версии или пути: `-Dyandex.version=<major-версия Chromium> -Dyandex.binary=<путь>`.

Отчёт Allure:

```
mvn allure:serve
```
