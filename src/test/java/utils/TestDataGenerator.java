package utils;

import model.User;

import java.util.UUID;

public class TestDataGenerator {

    public static User createRandomUser() {
        String unique = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String email = "dima_" + unique + "@yandex.ru";
        String password = "123456";
        String name = "Dima" + unique;

        return new User(email, password, name);
    }
}