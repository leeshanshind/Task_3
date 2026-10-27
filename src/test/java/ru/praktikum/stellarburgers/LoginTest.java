package ru.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.praktikum.stellarburgers.pages.ForgotPasswordPage;
import ru.praktikum.stellarburgers.pages.LoginPage;
import ru.praktikum.stellarburgers.pages.MainPage;
import ru.praktikum.stellarburgers.pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
@DisplayName("Вход в аккаунт")
class LoginTest extends BaseTest {

    @BeforeEach
    void setUp() {
        createUser();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("После входа открывается главная с кнопкой «Оформить заказ»")
    void loginFromMainPageButton() {
        LoginPage loginPage = new MainPage(driver).open().clickLoginButton();

        assertLoggedIn(loginPage.login(user.getEmail(), user.getPassword()));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("После входа открывается главная с кнопкой «Оформить заказ»")
    void loginFromAccountLink() {
        new MainPage(driver).open().clickAccountLink();

        assertLoggedIn(new LoginPage(driver).login(user.getEmail(), user.getPassword()));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("После входа открывается главная с кнопкой «Оформить заказ»")
    void loginFromRegisterForm() {
        LoginPage loginPage = new RegisterPage(driver).open().clickLoginLink();

        assertLoggedIn(loginPage.login(user.getEmail(), user.getPassword()));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("После входа открывается главная с кнопкой «Оформить заказ»")
    void loginFromForgotPasswordForm() {
        LoginPage loginPage = new ForgotPasswordPage(driver).open().clickLoginLink();

        assertLoggedIn(loginPage.login(user.getEmail(), user.getPassword()));
    }

    private void assertLoggedIn(MainPage mainPage) {
        assertTrue(mainPage.isOpened(), "После входа не открылась главная страница");
        assertTrue(mainPage.isOrderButtonVisible(), "После входа не появилась кнопка «Оформить заказ»");
    }
}
