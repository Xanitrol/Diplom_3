package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utils.Config;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    private DriverFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new DriverFactory();
        driver = factory.getDriver();
        driver.get(Config.BASE_URL);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        factory.tearDown();
    }
}