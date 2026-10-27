package ru.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница восстановления пароля /forgot-password.
 */
public class ForgotPasswordPage extends BasePage {

    public static final String URL = BASE_URL + "forgot-password";

    // Ссылка «Войти» под формой
    private final By loginLink = By.xpath("//a[@href='/login' and text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу восстановления пароля")
    public ForgotPasswordPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Нажать «Войти» в форме восстановления пароля")
    public LoginPage clickLoginLink() {
        click(loginLink);
        return new LoginPage(driver);
    }
}
