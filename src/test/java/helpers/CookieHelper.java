package helpers;

import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CookieHelper {
    public static void setCookies(Map<String,String> cookiesMap) {
        System.out.println(cookiesMap);
        cookiesMap.forEach((k, v) -> getWebDriver().manage().addCookie(new Cookie(k, v)));
    }
}
