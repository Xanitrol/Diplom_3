package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    protected static WebDriver driver;

    @BeforeEach
    public void setUpDriver() {
        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");

        if ("yandex".equalsIgnoreCase(browser)) {
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
            WebDriverManager.chromedriver().browserVersion("144").setup();
        } else {
            WebDriverManager.chromedriver().setup();
        }

        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}