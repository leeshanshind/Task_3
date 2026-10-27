package ru.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.praktikum.stellarburgers.api.User;
import ru.praktikum.stellarburgers.pages.LoginPage;
import ru.praktikum.stellarburgers.pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
@DisplayName("Регистрация")
class RegistrationTest extends BaseTest {
    private static final String INCORRECT_PASSWORD_MESSAGE = "Некорректный пароль";

    @Test
    @DisplayName("Успешная регистрация переводит на страницу входа")
    @Description("После регистрации с корректными данными открывается страница входа")
    void successfulRegistrationOpensLoginPage() {
        user = User.random();

        new RegisterPage(driver).open().register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(new LoginPage(driver).isOpened(), "После регистрации не открылась страница входа");
    }

    @ParameterizedTest(name = "пароль из {0} символов")
    @ValueSource(ints = {1, 5})
    @DisplayName("Пароль короче шести символов вызывает ошибку")
    @Description("При пароле меньше шести символов под полем показывается «Некорректный пароль»")
    void shortPasswordShowsError(int length) {
        User shortPasswordUser = User.random();
        RegisterPage registerPage = new RegisterPage(driver).open();

        registerPage.register(shortPasswordUser.getName(), shortPasswordUser.getEmail(), "x".repeat(length));

        assertTrue(registerPage.isPasswordErrorVisible(), "Не показана ошибка некорректного пароля");
        assertEquals(INCORRECT_PASSWORD_MESSAGE, registerPage.getPasswordErrorText());
    }
}
