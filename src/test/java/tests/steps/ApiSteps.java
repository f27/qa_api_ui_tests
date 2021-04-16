package tests.steps;

import api.elements.CartElements;
import api.elements.MainElements;
import api.elements.SearchResultsElements;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.LogFilter.filters;
import static api.elements.ItemPageElements.getDetails;
import static api.spec.RequestSpec.authorizedSpec;
import static endpoints.ApiEndpoints.*;
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

    @Step("(API) Get page {page} with params {params}")
    private static String getPage(String page, Map<String, String> params) {
        return given(authorizedSpec)
                .filter(filters().withCustomTemplates())
                .queryParams(params)
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

    @Step("(API) Post to page {page}")
    private static String postPage(String page) {
        return given(authorizedSpec)
                .filter(filters().withCustomTemplates())
                .when()
                .post(page)
                .then()
                .statusCode(200)
                .extract()
                .body().asString();
    }

    @Step("(API) Verify that email on {page} is equal to {email}")
    private static void pageHasEmail(String page, String email) {
        assertThat(MainElements.getEmail(getPage(page))).isEqualTo(email);
    }

    @Step("(API) Verify that logged in successfully")
    public static void verifyLogin(String email) {
        pageHasEmail(MAIN.getPath(), email);
    }

    @Step("(API) Clear cart")
    public static void removeAllItemsInCart() {
        List<String> itemsInCart = new ArrayList<>();
        CartElements.getItemsInfoFromCart(getPage(CART.getPath())).forEach((itemName, itemInfo) ->
                itemsInCart.add(itemInfo.get("id"))
        );

        if (itemsInCart.isEmpty()) return;

        List<String> updateCartString = new ArrayList<>();
        updateCartString.add("Update shopping cart");
        Map<String, List<String>> postData = new HashMap<String, List<String>>() {{
            put("removefromcart", itemsInCart);
            put("updatecart", updateCartString);
        }};

        postDataToPage(CART.getPath(), postData, true);
    }

    @Step("(API) Add product to cart from catalog")
    public static void addToCartFromCatalog(String itemId, String qty) {
        postPage(ADD_TO_CART.addPath("/catalog/" + itemId + "/1/" + qty));
    }

    @Step("(API) Verify that item added to cart and remove from cart")
    public static void verifyAddToCart(String itemName, String qty) {
        Map<String, Map<String, String>> itemsInCart = CartElements.getItemsInfoFromCart(getPage(CART.getPath()));
        assertThat(itemsInCart.keySet()).contains(itemName);
        assertThat(itemsInCart.get(itemName).get("qty")).isEqualTo(qty);

        Map<String, String> postData = new HashMap<String, String>() {{
            put("removefromcart", itemsInCart.get(itemName).get("id"));
            put("updatecart", "Update shopping cart");
        }};

        postDataToPage(CART.getPath(), postData);
    }

    @Step("(API) Add product to cart with details")
    public static void addToCartWithDetails(String itemId, String itemName, List<String> details, String qty) {
        Map<String, Map<String,String>> searchResults = search(itemName);
        Map<String, Map<String,String>> allDetails = getDetails(getPage(searchResults.get(itemName).get("href")));
        Map<String, String> postData = new HashMap<>();
        details.forEach((detailName) ->
                postData.put(allDetails.get(detailName).get("attribute"), allDetails.get(detailName).get("value"))
        );
        postData.put("addtocart_" + itemId + ".EnteredQuantity", qty);
        postDataToPage(ADD_TO_CART.addPath("/details/" + itemId + "/1"), postData);
    }

    @Step("(API) Search '{searchText}'")
    public static Map<String, Map<String,String>> search(String searchText) {
        Map<String,String> searchQuery = new HashMap<String,String>(){{
            put("q", searchText);
        }};

        return SearchResultsElements.getItems(getPage(SEARCH.getPath(), searchQuery));
    }

    @Step("(API) Advanced search with params '{searchParams}'")
    public static Map<String, Map<String,String>> advancedSearch(Map<String,String> searchParams) {

        return SearchResultsElements.getItems(getPage(SEARCH.getPath(), searchParams));
    }

}
