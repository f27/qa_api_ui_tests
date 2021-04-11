package tests.steps;

import io.qameta.allure.Step;

import static endpoints.ApiEndpoints.MAIN;
import static api.LogFilter.filters;
import static api.spec.RequestSpec.authorizedSpec;
import static api.matchers.ApiMatchers.getEmailFromMain;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {

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
