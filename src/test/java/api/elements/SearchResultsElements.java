package api.elements;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class SearchResultsElements {
    private static final String
            itemInfoSelector = ".item-box",
            itemNameSelector = ".product-title",
            itemPriceSelector = ".prices";

    public static Map<String, Map<String,String>> getItems(String pageText) {
        Map<String, Map<String, String>> allItemsInfo = new HashMap<>();
        Elements itemsFullInfo = Jsoup.parse(pageText).select(itemInfoSelector);
        itemsFullInfo.forEach((itemFullInfo) -> {
            Map<String, String> itemsInfo = new HashMap<String, String>() {{
                put("name", itemFullInfo.select(itemNameSelector).text());
                put("price", itemFullInfo.select(itemPriceSelector).text());
            }};
            allItemsInfo.put(itemFullInfo.select(itemNameSelector).text(), itemsInfo);
        });

        System.out.println(allItemsInfo);

        return allItemsInfo;
    }
}
