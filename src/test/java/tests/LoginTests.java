package tests;

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
        Map<String,String> cookies = ApiSteps.login(TestData.getUserEmail(), TestData.getUserPassword());
        ApiSteps.verifyLogin(TestData.getUserEmail(), cookies);
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
