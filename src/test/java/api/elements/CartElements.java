package api.elements;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class CartElements {
    private static final String itemFullInfoSelector = ".cart-item-row";
    private static final String itemIdSelector = "[name=removefromcart]";
    private static final String itemNameSelector = ".product-name";
    private static final String itemPriceSelector = ".product-unit-price";
    private static final String itemQtySelector = ".qty-input";
    public static Map<String, Map<String, String>> getItemsInfoFromCart(String pageText) {
        Map<String, Map<String, String>> allItemsInfo = new HashMap<>();
        Elements itemsFullInfo = Jsoup.parse(pageText).select(itemFullInfoSelector);
        itemsFullInfo.forEach((itemFullInfo) -> {
            Map<String, String> itemsInfo = new HashMap<String, String>(){{
                put("id", itemFullInfo.select(itemIdSelector).val());
                put("price", itemFullInfo.select(itemPriceSelector).text());
                put("qty", itemFullInfo.select(itemQtySelector).val());
            }};
            allItemsInfo.put(itemFullInfo.select(itemNameSelector).text(), itemsInfo);
        });

        return allItemsInfo;
    }
}
