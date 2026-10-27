package ru.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница входа /login.
 */
public class LoginPage extends BasePage {

    public static final String URL = BASE_URL + "login";

    // Заголовок формы «Вход»
    private final By header = By.xpath("//h2[text()='Вход']");
    // Поле «Email»
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    // Поле «Пароль»
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    // Кнопка «Войти»
    private final By loginButton = By.xpath("//form//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу входа")
    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    public boolean isOpened() {
        return isUrl(URL) && isVisible(header);
    }

    @Step("Войти с email {email}")
    public MainPage login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginButton);
        return new MainPage(driver);
    }
}
