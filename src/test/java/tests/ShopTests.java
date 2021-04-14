package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import tests.steps.ApiSteps;

import static tests.steps.ApiSteps.verifyAddToCart;

@Owner("f27")
@DisplayName("Shop tests")
public class ShopTests extends TestBase{

    @BeforeEach
    void clearCart() {
        ApiSteps.removeAllItemsInCart();
    }

    @Test
    @Tag("API")
    @DisplayName("Add to cart from catalog via API")
    void addSimpleItemToCart() {
        String qtyToAdd = TestData.getSimpleItemQuantity();
        ApiSteps.addToCartFromCatalog(TestData.getSimpleItemId(), qtyToAdd);
        verifyAddToCart(TestData.getSimpleItemName(), qtyToAdd);
    }
}
