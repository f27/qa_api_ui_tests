package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.steps.ApiSteps;
import tests.steps.WebSteps;

@Owner("f27")
@DisplayName("Login tests")
public class LoginTests extends TestBase{

    @Test
    @Tag("API")
    @DisplayName("Verify login via API")
    void loginOnlyApiTest() {
        ApiSteps.verifyLogin(TestData.getUserEmail());
    }

    @Test
    @Tag("MIXED")
    @DisplayName("Verify login via UI")
    void mixedLoginTest() {
        WebSteps.setCookies(authCookies);
        WebSteps.verifyLogin(TestData.getUserEmail());
    }

    @Test
    @Tag("UI")
    @DisplayName("Login via UI, verify login")
    void uiLoginTest() {
        WebSteps.login(TestData.getUserEmail(), TestData.getUserPassword());
        WebSteps.verifyLogin(TestData.getUserEmail());
    }
}
