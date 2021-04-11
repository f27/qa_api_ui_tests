package tests.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.Map;

import static api.LogFilter.filters;
import static io.restassured.RestAssured.given;

public class ApiSteps {

    @Step("Login via API")
    public static Map<String, String> loginViaApi(String email, String password, Boolean rememberMe) {
        return given()
                .filter(filters().withCustomTemplates())
                .log().uri()
                .contentType(ContentType.URLENC)
                .formParam("Email", email)
                .formParam("Password", password)
                .formParam("RememberMe", rememberMe)
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .log().body()
                .extract()
                .cookies();
    }

    public static Map<String, String> loginViaApi(String email, String password) {
        return loginViaApi(email, password, false);
    }
}
