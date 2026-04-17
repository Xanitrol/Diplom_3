package utils;

import model.User;

public class UserGenerator {

    public static User randomUser() {
        long time = System.currentTimeMillis();
        return new User(
                "dima" + time + "@yandex.ru",
                "password123",
                "Dima" + time
        );
    }
}