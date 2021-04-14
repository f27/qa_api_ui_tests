package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import tests.steps.ApiSteps;

import static tests.steps.ApiSteps.verifyAddToCart;

@Owner("f27")
@DisplayName("Shop tests")
public class ShopTests extends TestBase{

    @Test
    @Tag("API")
    @DisplayName("Add to cart from catalog via API")
    void addSimpleItemToCart() {
        String qtyToAdd = TestData.getSimpleItemQuantity();
        ApiSteps.addToCartFromCatalog(TestData.getSimpleItemId(), qtyToAdd);
        verifyAddToCart(TestData.getSimpleItemName(), qtyToAdd);
    }

    @Test
    @Tag("API")
    @DisplayName("Add to cart with details via API")
    void addWithDetailsItemToCart() {
        String qtyToAdd = TestData.getWithDetailsItemQuantity();
        ApiSteps.addToCartWithDetails(
                TestData.getWithDetailsItemId(),
                TestData.getWithDetailsItemDetails(),
                qtyToAdd);

        verifyAddToCart(TestData.getWithDetailsItemName(), qtyToAdd);
    }
}
