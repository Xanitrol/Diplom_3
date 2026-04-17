package utils;

public class Config {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    private Config() {
    }

    public static String getBrowser() {
        return System.getProperty("browser", "chrome");
    }

    public static String getYandexBrowserBinary() {
        String propertyValue = System.getProperty("yandex.browser.binary");
        if (propertyValue != null && !propertyValue.isBlank()) {
            return propertyValue.trim();
        }

        String envValue = System.getenv("YANDEX_BROWSER_BINARY");
        if (envValue != null && !envValue.isBlank()) {
            return envValue.trim();
        }

        return null;
    }
}