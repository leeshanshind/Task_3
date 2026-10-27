package ru.praktikum.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikum.stellarburgers.api.User;
import ru.praktikum.stellarburgers.api.UserClient;

import static org.apache.http.HttpStatus.SC_OK;

/**
 * Запуск браузера и подготовка тестового пользователя.
 * Браузер выбирается параметром -Dbrowser=chrome|yandex.
 */
public abstract class BaseTest {
    private static final String YANDEX_BINARY = "/Applications/Yandex.app/Contents/MacOS/Yandex";

    protected final UserClient userClient = new UserClient();
    protected WebDriver driver;
    protected User user;

    @BeforeEach
    public void startBrowser() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        ChromeOptions options = new ChromeOptions();

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "yandex":
                // Яндекс.Браузер работает на Chromium, поэтому нужен chromedriver под его версию Chromium
                String yandexVersion = System.getProperty("yandex.version");
                if (yandexVersion == null) {
                    throw new IllegalStateException("Укажите версию Chromium Яндекс.Браузера: -Dyandex.version=<major>");
                }
                WebDriverManager.chromedriver().browserVersion(yandexVersion).setup();
                options.setBinary(System.getProperty("yandex.binary", YANDEX_BINARY));
                break;
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
        if (headless) {
            options.addArguments("--headless=new");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        deleteUser();
    }

    protected void createUser() {
        user = User.random();
        userClient.register(user).statusCode(SC_OK);
    }

    // пользователь мог быть создан и через API, и через форму регистрации, поэтому токен получаем логином
    private void deleteUser() {
        if (user == null) {
            return;
        }
        String accessToken = userClient.login(user.getEmail(), user.getPassword())
                .extract().path("accessToken");
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}
