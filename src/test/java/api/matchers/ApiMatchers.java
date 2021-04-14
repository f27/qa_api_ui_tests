package api.matchers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiMatchers {
    private static final String myEmailOnMainPattern = "<a href=\"/customer/info\" class=\"account\">(\\w+@\\w+.\\w+)</a>";
    private static final String itemsInfoInCart =
            "product-name\">(.+)</a>[\\s\\S]+?</td>\\s+<td class=\"unit-price nobr\">\\s+<span class=\"td-title\">" +
                    "Price:</span>\\s+<span class=\"product-unit-price\">(.+)</span>\\s+</td>\\s+<td class=" +
                    "\"qty nobr\">\\s+<span class=\"td-title\">Qty.:</span>\\s+<input name=\"itemquantity(\\d+)\"" +
                    " type=\"text\" value=\"(\\d+)\" class=\"qty-input\" />";

    public static String getEmail(String pageText) {
        Pattern pattern = Pattern.compile(myEmailOnMainPattern);
        Matcher matcher = pattern.matcher(pageText);
        if (matcher.find())
            return matcher.group(1);
        else
            return "Email not found";
    }

    public static Map<String, Map<String, String>> getItemsInfoFromCart(String pageText) {
        Pattern pattern = Pattern.compile(itemsInfoInCart);
        Matcher matcher = pattern.matcher(pageText);
        Map<String, Map<String, String>> allItemsInfo = new HashMap<>();
        while (matcher.find()) {
            Map<String, String> itemsInfo = new HashMap<>();
            itemsInfo.put("price", matcher.group(2));
            itemsInfo.put("id", matcher.group(3));
            itemsInfo.put("qty", matcher.group(4));
            allItemsInfo.put(matcher.group(1), itemsInfo);
        }

        return allItemsInfo;
    }
}
