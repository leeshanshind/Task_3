package ru.praktikum.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.praktikum.stellarburgers.pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
@DisplayName("Раздел «Конструктор»")
class ConstructorTest extends BaseTest {

    // «Булки» выбраны по умолчанию, поэтому для проверки перехода к ним сначала уходим на «Соусы»
    @ParameterizedTest(name = "с вкладки «{0}» на вкладку «{1}»")
    @CsvSource({
            "Соусы, Булки",
            "Булки, Соусы",
            "Булки, Начинки"
    })
    @DisplayName("Работает переход к разделу конструктора")
    @Description("После клика по вкладке она становится выбранной")
    void tabClickSelectsSection(String fromTab, String toTab) {
        MainPage mainPage = new MainPage(driver).open().clickTab(fromTab);

        mainPage.clickTab(toTab);

        assertTrue(mainPage.isTabSelected(toTab), "Не выбрана вкладка «" + toTab + "»");
    }
}
