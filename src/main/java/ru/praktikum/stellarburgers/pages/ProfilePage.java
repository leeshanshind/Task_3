package ru.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Личный кабинет /account/profile.
 */
public class ProfilePage extends BasePage {

    public static final String URL = BASE_URL + "account/profile";

    // Ссылка «Профиль» в меню личного кабинета
    private final By profileLink = By.xpath("//a[@href='/account/profile' and text()='Профиль']");
    // Кнопка «Выход» в меню личного кабинета
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isUrl(URL) && isVisible(profileLink);
    }

    @Step("Нажать «Выход» в личном кабинете")
    public LoginPage clickLogoutButton() {
        click(logoutButton);
        return new LoginPage(driver);
    }
}
