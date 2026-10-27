package ru.praktikum.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Главная страница с конструктором бургеров. Элементы шапки описаны в {@link BasePage}.
 */
public class MainPage extends BasePage {

    // Заголовок конструктора «Соберите бургер»
    private final By constructorHeader = By.xpath("//h1[text()='Соберите бургер']");
    // Кнопка «Войти в аккаунт» (видна неавторизованному пользователю)
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    // Кнопка «Оформить заказ» (видна авторизованному пользователю)
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    // Вкладка раздела конструктора по названию: «Булки», «Соусы» или «Начинки»
    private static final String TAB = "//div[starts-with(@class, 'tab_tab')][span[text()='%s']]";
    // Признак выбранной вкладки в классе элемента
    private static final String CURRENT_TAB_CLASS = "tab_tab_type_current";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public MainPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public boolean isOpened() {
        return isUrl(BASE_URL) && isVisible(constructorHeader);
    }

    @Step("Нажать «Войти в аккаунт» на главной")
    public LoginPage clickLoginButton() {
        click(loginButton);
        return new LoginPage(driver);
    }

    public boolean isOrderButtonVisible() {
        return isVisible(orderButton);
    }

    @Step("Перейти на вкладку «{tabName}»")
    public MainPage clickTab(String tabName) {
        click(By.xpath(String.format(TAB, tabName)));
        return this;
    }

    public boolean isTabSelected(String tabName) {
        By tab = By.xpath(String.format(TAB, tabName));
        try {
            return wait.until(ExpectedConditions.attributeContains(tab, "class", CURRENT_TAB_CLASS));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
