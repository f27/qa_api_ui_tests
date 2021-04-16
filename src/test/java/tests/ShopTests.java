package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import tests.steps.ApiSteps;

@Owner("f27")
@DisplayName("Shop tests")
public class ShopTests extends TestBase {

    @Test
    @Tag("API")
    @DisplayName("Add to cart from catalog via API")
    void addSimpleItemToCartTest() {
        String qtyToAdd = TestData.getSimpleItemQuantity();
        ApiSteps.addToCartFromCatalog(TestData.getSimpleItemId(), qtyToAdd);
        ApiSteps.verifyAddToCart(TestData.getSimpleItemName(), qtyToAdd);
    }

    @Test
    @Tag("API")
    @DisplayName("Find and add to cart item with details via API")
    void addWithDetailsItemToCartTest() {
        String qtyToAdd = TestData.getWithDetailsItemQuantity();
        ApiSteps.addToCartWithDetails(
                TestData.getWithDetailsItemId(),
                TestData.getWithDetailsItemName(),
                TestData.getWithDetailsItemDetails(),
                qtyToAdd);

        ApiSteps.verifyAddToCart(TestData.getWithDetailsItemName(), qtyToAdd);
    }
}
