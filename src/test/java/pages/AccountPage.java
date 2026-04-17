package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    private final By profileLink = By.xpath("//a[text()='Профиль']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By accountContentBox = By.xpath("//div[contains(@class,'Account_contentBox')]");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Дождаться открытия личного кабинета")
    public void waitForAccountPageOpened() {
        waitForVisibility(profileLink);
    }

    @Step("Проверить, что личный кабинет открыт")
    public boolean isAccountPageOpened() {
        return isDisplayed(profileLink) || isDisplayed(accountContentBox);
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickLogoutButton() {
        click(logoutButton);
    }
}