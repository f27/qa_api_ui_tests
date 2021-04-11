package tests;

import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static helpers.CookieHelper.setCookies;
import static tests.steps.ApiSteps.loginViaApi;

public class LoginTests extends TestBase{
    @Test
    void firstTest() {
        open("/favicon.ico");
        setCookies(loginViaApi(TestData.getUserEmail(), TestData.getUserPassword()));
        open();

    }
}
