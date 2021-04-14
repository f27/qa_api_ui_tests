package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.steps.ApiSteps;

@Owner("f27")
@DisplayName("Search tests")
public class SearchTests extends TestBase{

    @Test
    @Tag("API")
    @DisplayName("Add to cart with details via API")
    void searchItemTest() {
        ApiSteps.searchAndCheck(TestData.getSimpleItemName(), TestData.getSimpleItemName());
    }
}
