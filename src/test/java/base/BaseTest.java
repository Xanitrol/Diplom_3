package base;

import api.UserClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected UserClient userClient;

    @BeforeEach
    public void setUp() {
        DriverFactory factory = new DriverFactory();
        factory.setUpDriver();
        driver = DriverFactory.getDriver();
        userClient = new UserClient();
    }

    @AfterEach
    public void tearDown() {
        DriverFactory factory = new DriverFactory();
        factory.tearDown();
    }
}