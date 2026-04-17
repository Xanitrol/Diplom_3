package tests;

import api.UserClient;
import base.BaseTest;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import utils.TestDataGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {

    private User user;
    private UserClient userClient;
    private String accessToken;

    @BeforeEach
    public void setUpUser() {
        userClient = new UserClient();
        user = TestDataGenerator.createRandomUser();
        userClient.create(user);
        accessToken = userClient.login(user).then().extract().path("accessToken");
    }

    @AfterEach
    public void deleteUser() {
        if (accessToken != null) {
            try {
                userClient.delete(accessToken);
            } catch (Exception ignored) {
            }
        }
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.open();
        mainPage.clickLoginToAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isMakeOrderButtonVisible());

        mainPage.clickPersonalAccountButton();
        assertTrue(profilePage.isProfilePageOpened());

        profilePage.clickLogoutButton();

        assertTrue(loginPage.isLoginButtonVisible());
    }
}