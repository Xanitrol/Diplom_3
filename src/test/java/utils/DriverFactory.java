package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            String browser = Config.getBrowser();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");

            if ("yandex".equalsIgnoreCase(browser)) {
                String yandexBinary = Config.getYandexBrowserBinary();

                if (yandexBinary == null || yandexBinary.isBlank()) {
                    throw new IllegalArgumentException("Не указан путь к Yandex Browser");
                }

                options.setBinary(yandexBinary.trim());
            }

            // 🔥 ВАЖНО — Selenium сам подтянет нужный драйвер
            driver = new ChromeDriver(options);
        }

        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}