package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends BasePage {

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    private By emailInput = By.xpath("//input[@type='text']");
    private By restoreButton = By.xpath("//button[text()='Восстановить']");

    private By codeInput = By.xpath("//input[@type='text']");
    private By passwordInput = By.xpath("//input[@type='password']");
    private By saveButton = By.xpath("//button[text()='Сохранить']");

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickRestore() {
        driver.findElement(restoreButton).click();
    }

    public void enterCode(String code) {
        driver.findElement(codeInput).sendKeys(code);
    }

    public void enterNewPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }
}