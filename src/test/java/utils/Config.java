package utils;

public class Config {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public static final String YANDEX_BROWSER_PATH =
            System.getProperty(
                    "yandex.path",
                    "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe"
            );
}