package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.steps.ApiSteps;
import tests.steps.WebSteps;

@Owner("f27")
public class LoginTests extends TestBase{

    @Test
    @Tag("API")
    void loginOnlyApiTest() {
        ApiSteps.login(TestData.getUserEmail(), TestData.getUserPassword());
        ApiSteps.verifyLogin(TestData.getUserEmail());
    }

    @Test
    @Tag("MIXED")
    void mixedLoginTest() {
        WebSteps.setCookies(ApiSteps.login(TestData.getUserEmail(), TestData.getUserPassword()));
        WebSteps.verifyLogin(TestData.getUserEmail());
    }

    @Test
    @Tag("UI")
    void uiLoginTest() {
        WebSteps.login(TestData.getUserEmail(), TestData.getUserPassword());
        WebSteps.verifyLogin(TestData.getUserEmail());
    }
}
