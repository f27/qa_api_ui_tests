package tests.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.Map;

import static endpoints.ApiEndpoints.LOGIN;
import static endpoints.ApiEndpoints.MAIN;
import static api.LogFilter.filters;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {

    @Step("(API) Login")
    public static Map<String, String> login(String email, String password, Boolean rememberMe) {
        return given()
                .filter(filters().withCustomTemplates())
                .log().uri()
                .contentType(ContentType.URLENC)
                .formParam("Email", email)
                .formParam("Password", password)
                .formParam("RememberMe", rememberMe)
                .when()
                .post(LOGIN.getPath())
                .then()
                .statusCode(302)
                .extract()
                .cookies();
    }

    public static Map<String, String> login(String email, String password) {
        return login(email, password, false);
    }

    @Step("(API) Get page {page}")
    private static String getPage(String page, Map<String,String> cookies) {
        return given()
                .filter(filters().withCustomTemplates())
                .cookies(cookies)
                .log().uri()
                .contentType(ContentType.URLENC)
                .when()
                .get(page)
                .then()
                .statusCode(200)
                .extract()
                .body().asString();
    }

    @Step("(API) Verify that page {page} has text {text}")
    private static void pageHasText(String page, String text, Map<String,String> cookies) {
        assertThat(getPage(page, cookies)).contains(text);
    }

    @Step("(API) Verify that logged in successfully")
    public static void verifyLogin(String email, Map<String,String> cookies) {
        pageHasText(MAIN.getPath(), email, cookies);
    }
}
