package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorSectionTest extends BaseTest {

    @Test
    @DisplayName("Переход в раздел Булки")
    public void bunsTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();

        assertTrue(mainPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Переход в раздел Соусы")
    public void saucesTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickSaucesSection();

        assertTrue(mainPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    public void fillingsTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickFillingsSection();

        assertTrue(mainPage.isFillingsSectionActive());
    }
}