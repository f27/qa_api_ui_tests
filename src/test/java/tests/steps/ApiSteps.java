package tests.steps;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.matchers.ApiMatchers.getItemsFromCart;
import static endpoints.ApiEndpoints.CART;
import static endpoints.ApiEndpoints.MAIN;
import static api.LogFilter.filters;
import static api.spec.RequestSpec.authorizedSpec;
import static api.matchers.ApiMatchers.getEmail;
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

    @Step("(API) Post {data} to page {page}")
    private static String postDataToPage(String page, Map<String, String> data) {
        return given(authorizedSpec)
                .filter(filters().withCustomTemplates())
                .formParams(data)
                .log().params()
                .when()
                .post(page)
                .then()
                .statusCode(200)
                .extract()
                .body().asString();
    }

    @Step("(API) Post {data} to page {page}")
    private static String postDataToPage(String page, Map<String, List<String>> data, Boolean dataAsList) {
        return given(authorizedSpec)
                .filter(filters().withCustomTemplates())
                .formParams(data)
                .log().params()
                .when()
                .post(page)
                .then()
                .statusCode(200)
                .extract()
                .body().asString();
    }

    @Step("(API) Verify that page {page} has text {text}")
    private static void pageHasText(String page, String text) {
        assertThat(getEmail(getPage(page))).isEqualTo(text);
    }

    @Step("(API) Verify that logged in successfully")
    public static void verifyLogin(String email) {
        pageHasText(MAIN.getPath(), email);
    }

    @Step("(API) Clear cart")
    public static void removeAllItemsInCart() {
        List<String> itemsInCart = getItemsFromCart(getPage(CART.getPath()));
        List<String> updateCartString = new ArrayList<>();
        updateCartString.add("Update shopping cart");
        Map<String, List<String>> postData = new HashMap<String, List<String>>() {{
            put("removefromcart", itemsInCart);
            put("updatecart", updateCartString);
        }};

        postDataToPage(CART.getPath(), postData, true);
    }

}
