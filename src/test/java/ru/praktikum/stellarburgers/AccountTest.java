package ru.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.praktikum.stellarburgers.pages.LoginPage;
import ru.praktikum.stellarburgers.pages.MainPage;
import ru.praktikum.stellarburgers.pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
@DisplayName("Личный кабинет")
class AccountTest extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        createUser();
        mainPage = new LoginPage(driver).open().login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible(), "Не удалось войти перед тестом");
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    @Description("Авторизованный пользователь попадает в профиль личного кабинета")
    void accountLinkOpensProfile() {
        mainPage.clickAccountLink();

        assertTrue(new ProfilePage(driver).isOpened(), "Не открылся профиль в личном кабинете");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    @Description("Из профиля по ссылке «Конструктор» открывается главная с конструктором")
    void constructorLinkOpensConstructorFromProfile() {
        ProfilePage profilePage = openProfile();

        assertTrue(profilePage.clickConstructorLink().isOpened(), "Не открылся конструктор");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    @Description("Из профиля по клику на логотип открывается главная с конструктором")
    void logoOpensConstructorFromProfile() {
        ProfilePage profilePage = openProfile();

        assertTrue(profilePage.clickLogo().isOpened(), "Не открылся конструктор");
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выход» в личном кабинете")
    @Description("После выхода открывается страница входа")
    void logoutButtonOpensLoginPage() {
        ProfilePage profilePage = openProfile();

        assertTrue(profilePage.clickLogoutButton().isOpened(), "После выхода не открылась страница входа");
    }

    private ProfilePage openProfile() {
        mainPage.clickAccountLink();
        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue(profilePage.isOpened(), "Не открылся профиль в личном кабинете");
        return profilePage;
    }
}
