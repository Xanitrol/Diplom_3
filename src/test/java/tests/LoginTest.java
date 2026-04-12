package tests;

import api.UserClient;
import base.BaseTest;
import io.restassured.response.Response;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    private final UserClient userClient = new UserClient();
    private User user;
    private String accessToken;

    @BeforeEach
    public void setUpUser() {
        user = createRandomUser();

        Response createResponse = userClient.create(user);
        accessToken = createResponse.path("accessToken");
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
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginFromMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickLoginToAccountButton();
        loginPage.login(getEmail(user), getPassword(user));

        assertTrue(mainPage.isMakeOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginFromPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.login(getEmail(user), getPassword(user));

        assertTrue(mainPage.isMakeOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegisterPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.open();
        mainPage.clickLoginToAccountButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.login(getEmail(user), getPassword(user));

        assertTrue(mainPage.isMakeOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        mainPage.open();
        mainPage.clickLoginToAccountButton();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();
        loginPage.login(getEmail(user), getPassword(user));

        assertTrue(mainPage.isMakeOrderButtonVisible());
    }

    private User createRandomUser() {
        String email = "dmitry_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10) + "@ya.ru";
        String password = "Pass123";
        String name = "Dmitry";

        try {
            User newUser = User.class.getDeclaredConstructor().newInstance();
            setValue(newUser, "email", email);
            setValue(newUser, "password", password);
            setValue(newUser, "name", name);
            return newUser;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать User. Проверь модель User.", e);
        }
    }

    private void setValue(User user, String fieldName, String value) {
        try {
            Method setter = User.class.getMethod(
                    "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1),
                    String.class
            );
            setter.invoke(user, value);
            return;
        } catch (Exception ignored) {
        }

        try {
            Field field = User.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(user, value);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось заполнить поле " + fieldName + " у User", e);
        }
    }

    private String getEmail(User user) {
        try {
            Method method = User.class.getMethod("getEmail");
            return (String) method.invoke(user);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить email у User", e);
        }
    }

    private String getPassword(User user) {
        try {
            Method method = User.class.getMethod("getPassword");
            return (String) method.invoke(user);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить password у User", e);
        }
    }
}