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

    // по уже выбранной вкладке клик перехватывает контейнер вкладок, а «Булки» выбраны по умолчанию,
    // поэтому сначала всегда переходим на другую, невыбранную вкладку
    @ParameterizedTest(name = "с вкладки «{0}» на вкладку «{1}»")
    @CsvSource({
            "Соусы, Булки",
            "Начинки, Соусы",
            "Соусы, Начинки"
    })
    @DisplayName("Работает переход к разделу конструктора")
    @Description("После клика по вкладке она становится выбранной")
    void tabClickSelectsSection(String fromTab, String toTab) {
        MainPage mainPage = new MainPage(driver).open().clickTab(fromTab);
        assertTrue(mainPage.isTabSelected(fromTab), "Не выбрана исходная вкладка «" + fromTab + "»");

        mainPage.clickTab(toTab);

        assertTrue(mainPage.isTabSelected(toTab), "Не выбрана вкладка «" + toTab + "»");
    }
}
