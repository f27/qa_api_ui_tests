package tests.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import tests.pages.LoginPage;
import tests.pages.MainPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static endpoints.UiEndpoints.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WebSteps {

    @Step("(UI) Set cookies")
    public static void setCookies(Map<String, String> cookiesMap) {
        open(FAVICON.getPath());
        cookiesMap.forEach((k, v) -> getWebDriver().manage().addCookie(new Cookie(k, v)));
    }

    @Step("(UI) Verify that logged in successfully")
    public static void verifyLogin(String email) {
        assertThat(open(MAIN.getPath(), MainPage.class).getAccountName()).isEqualTo(email);
    }

    @Step("(UI) Login")
    public static void login(String email, String password, Boolean rememberMe) {
        open(LOGIN.getPath(), LoginPage.class).login(email, password, rememberMe);
    }

    public static void login(String email, String password) {
        login(email, password, false);
    }
}
