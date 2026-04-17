package tests;

import base.BaseTest;
import model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.TestDataGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    public void registerSuccessTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        User user = TestDataGenerator.createRandomUser();

        mainPage.open();
        mainPage.clickLoginToAccountButton();
        loginPage.clickRegisterLink();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(loginPage.isLoginButtonVisible());
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле")
    public void registerWithInvalidPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        User user = TestDataGenerator.createRandomUser();

        mainPage.open();
        mainPage.clickLoginToAccountButton();
        loginPage.clickRegisterLink();
        registerPage.register(user.getName(), user.getEmail(), "12345");

        assertTrue(registerPage.isIncorrectPasswordErrorVisible());
    }
}