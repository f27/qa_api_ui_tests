package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.steps.ApiSteps;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Owner("f27")
@DisplayName("Search tests")
public class SearchTests extends TestBase{

    @Test
    @Tag("API")
    @DisplayName("Search and verify that results contains item via API")
    void searchItemTest() {
        Map<String, Map<String,String>> searchResults =
                ApiSteps.search(TestData.getSimpleItemName());
        assertThat(searchResults.keySet()).contains(TestData.getSimpleItemName());
    }

    @Test
    @Tag("API")
    @DisplayName("Search and verify that results is empty via API")
    void advancedNegativeSearchItemTest() {
        Map<String,String> searchParams = new HashMap<String,String>(){{
            put("q", TestData.getSimpleItemName());
            put("As", "true");//Advanced search
            put("Cid", "3");//Category id. 2-Desktops
            put("Isc", "false");//Automatically search sub categories
            put("Mid", "0");//Manufacturer. 0- all
            put("Pf", "");//price from
            put("Pt", "");//price to
            put("Sid", "");//Search In product descriptions
        }};
        Map<String, Map<String,String>> searchResults = ApiSteps.advancedSearch(searchParams);
        assertThat(searchResults).isEmpty();
    }
}
