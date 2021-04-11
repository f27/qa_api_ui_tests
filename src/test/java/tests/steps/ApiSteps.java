package tests.steps;

import io.qameta.allure.Step;

import java.util.Map;

import static endpoints.ApiEndpoints.LOGIN;
import static endpoints.ApiEndpoints.MAIN;
import static api.LogFilter.filters;
import static api.spec.RequestSpec.spec;
import static api.spec.RequestSpec.authorizedSpec;
import static api.elements.ApiElements.getEmailFromMain;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {

    @Step("(API) Login")
    public static Map<String, String> login(String email, String password, Boolean rememberMe) {
        Map<String,String> authCookies =
                given(spec)
                .filter(filters().withCustomTemplates())
                .formParam("Email", email)
                .formParam("Password", password)
                .formParam("RememberMe", rememberMe)
                .when()
                .post(LOGIN.getPath())
                .then()
                .statusCode(302)
                .extract()
                .cookies();
        authorizedSpec.cookies(authCookies);

        return authCookies;
    }

    public static Map<String, String> login(String email, String password) {
        return login(email, password, false);
    }

    @Step("(API) Get page {page}")
    private static String getPage(String page) {
        return given(authorizedSpec)
                .filter(filters().withCustomTemplates())
                .when()
                .get(page)
                .then()
                .statusCode(200)
                .extract()
                .body().asString();
    }

    @Step("(API) Verify that page {page} has text {text}")
    private static void pageHasText(String page, String text) {
        assertThat(getEmailFromMain(getPage(page))).isEqualTo(text);
    }

    @Step("(API) Verify that logged in successfully")
    public static void verifyLogin(String email) {
        pageHasText(MAIN.getPath(), email);
    }
}
