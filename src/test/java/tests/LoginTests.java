package tests;

import api.Auth;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.steps.ApiSteps;
import tests.steps.WebSteps;

import java.util.Map;

@Owner("f27")
public class LoginTests extends TestBase{

    @Test
    @Tag("API")
    void loginOnlyApiTest() {
        ApiSteps.verifyLogin(TestData.getUserEmail());
    }

    @Test
    @Tag("MIXED")
    void mixedLoginTest() {
        WebSteps.setCookies(authCookies);
        WebSteps.verifyLogin(TestData.getUserEmail());
    }

    @Test
    @Tag("UI")
    void uiLoginTest() {
        WebSteps.login(TestData.getUserEmail(), TestData.getUserPassword());
        WebSteps.verifyLogin(TestData.getUserEmail());
    }
}
