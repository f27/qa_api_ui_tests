package api.elements;

import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Map;

public class ItemPageElements {
    private static final String detailsSelector = "div.attributes dl dd ul li input";

    public static Map<String,Map<String,String>> getDetails(String pageText) {
        Map<String,Map<String,String>> detailsInfo = new HashMap<>();
        Jsoup.parse(pageText).select(detailsSelector).forEach(detail -> {
            Map<String,String> detailInfo = new HashMap<String,String>() {{
                put("value", detail.attr("value"));
                put("attribute", detail.attr("name"));
            }};
            detailsInfo.put(detail.parent().text(), detailInfo);
        });
        return detailsInfo;
    }
}
