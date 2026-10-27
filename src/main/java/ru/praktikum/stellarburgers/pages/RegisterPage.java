package ru.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница регистрации /register.
 */
public class RegisterPage extends BasePage {

    public static final String URL = BASE_URL + "register";

    // Поле «Имя»
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    // Поле «Email»
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    // Поле «Пароль»
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    // Кнопка «Зарегистрироваться»
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    // Ссылка «Войти» под формой
    private final By loginLink = By.xpath("//a[@href='/login' and text()='Войти']");
    // Сообщение об ошибке под полем пароля
    private final By passwordError = By.xpath("//p[contains(@class, 'input__error')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу регистрации")
    public RegisterPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Зарегистрироваться: имя {name}, email {email}")
    public void register(String name, String email, String password) {
        type(nameInput, name);
        type(emailInput, email);
        type(passwordInput, password);
        click(registerButton);
    }

    @Step("Нажать «Войти» в форме регистрации")
    public LoginPage clickLoginLink() {
        click(loginLink);
        return new LoginPage(driver);
    }

    public boolean isPasswordErrorVisible() {
        return isVisible(passwordError);
    }

    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }
}
