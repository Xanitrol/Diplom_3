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

public class ProfilePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By constructorButton = By.xpath("//p[text()='Конструктор']/ancestor::a");
    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By profileLink = By.xpath("//a[@href='/account/profile']");
    private final By profileForm = By.xpath("//main[contains(@class,'Account')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Проверить, что страница профиля открыта")
    public boolean isProfilePageOpened() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/account/profile"),
                    ExpectedConditions.visibilityOfElementLocated(profileLink),
                    ExpectedConditions.visibilityOfElementLocated(logoutButton),
                    ExpectedConditions.visibilityOfElementLocated(profileForm)
            ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Кликнуть по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        click(constructorButton);
    }

    @Step("Кликнуть по кнопке 'Выход'")
    public void clickLogoutButton() {
        click(logoutButton);
    }

    private void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}