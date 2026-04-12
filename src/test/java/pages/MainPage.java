package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String URL = "https://stellarburgers.education-services.ru/";

    private final By loginToAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//a[@href='/account']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']/ancestor::a");
    private final By logoButton = By.xpath("//div[contains(@class,'AppHeader_header__logo')]/a");

    private final By bunsTab = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Булки']]");
    private final By saucesTab = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Соусы']]");
    private final By fillingsTab = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Начинки']]");

    private final By bunsTitle = By.xpath("//h2[text()='Булки']");
    private final By saucesTitle = By.xpath("//h2[text()='Соусы']");
    private final By fillingsTitle = By.xpath("//h2[text()='Начинки']");

    private final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
    }

    @Step("Кликнуть по кнопке 'Войти в аккаунт'")
    public void clickLoginToAccountButton() {
        click(loginToAccountButton);
    }

    @Step("Кликнуть по кнопке 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        click(personalAccountButton);
    }

    @Step("Кликнуть по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        click(constructorButton);
    }

    @Step("Кликнуть по логотипу Stellar Burgers")
    public void clickLogo() {
        click(logoButton);
    }

    @Step("Перейти в раздел 'Булки'")
    public void clickBunsSection() {
        click(bunsTab);
        wait.until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current"));
    }

    @Step("Перейти в раздел 'Соусы'")
    public void clickSaucesSection() {
        click(saucesTab);
        wait.until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current"));
    }

    @Step("Перейти в раздел 'Начинки'")
    public void clickFillingsSection() {
        click(fillingsTab);
        wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current"));
    }

    @Step("Проверить, что активен раздел 'Булки'")
    public boolean isBunsSectionActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить, что активен раздел 'Соусы'")
    public boolean isSaucesSectionActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить, что активен раздел 'Начинки'")
    public boolean isFillingsSectionActive() {
        return isTabActive(fillingsTab);
    }

    @Step("Проверить, что кнопка 'Оформить заказ' отображается")
    public boolean isMakeOrderButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
            return driver.findElement(makeOrderButton).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    private boolean isTabActive(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String classValue = element.getAttribute("class");
            return classValue != null && classValue.contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
    }

    private void click(By locator) {
        waitOverlayDisappear();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void waitOverlayDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
        } catch (Exception ignored) {
        }
    }
}