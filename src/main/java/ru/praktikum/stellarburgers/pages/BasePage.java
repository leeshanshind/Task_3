package ru.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Общие элементы и действия для всех страниц Stellar Burgers: шапка сайта.
 */
public abstract class BasePage {

    public static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";
    protected static final Duration TIMEOUT = Duration.ofSeconds(10);

    // Ссылка «Конструктор» в шапке
    private final By constructorLink = By.xpath("//header//a[p[text()='Конструктор']]");
    // Ссылка «Личный Кабинет» в шапке
    private final By accountLink = By.xpath("//header//a[p[text()='Личный Кабинет']]");
    // Логотип Stellar Burgers в шапке
    private final By logo = By.xpath("//div[starts-with(@class, 'AppHeader_header__logo')]/a");

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    @Step("Нажать «Конструктор» в шапке")
    public MainPage clickConstructorLink() {
        click(constructorLink);
        return new MainPage(driver);
    }

    @Step("Нажать на логотип Stellar Burgers")
    public MainPage clickLogo() {
        click(logo);
        return new MainPage(driver);
    }

    @Step("Нажать «Личный Кабинет» в шапке")
    public void clickAccountLink() {
        click(accountLink);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    protected boolean isUrl(String url) {
        try {
            return wait.until(ExpectedConditions.urlToBe(url));
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
