package api;

import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URI = "https://stellarburgers.education-services.ru";
    private static final String CREATE_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String DELETE_PATH = "/api/auth/user";

    public Response create(User user) {
        return given()
                .baseUri(BASE_URI)
                .header("Content-type", "application/json")
                .body(user)
                .post(CREATE_PATH);
    }

    public Response login(User user) {
        return given()
                .baseUri(BASE_URI)
                .header("Content-type", "application/json")
                .body(user)
                .post(LOGIN_PATH);
    }

    public void delete(String accessToken) {
        given()
                .baseUri(BASE_URI)
                .header("Authorization", accessToken)
                .delete(DELETE_PATH);
    }
}