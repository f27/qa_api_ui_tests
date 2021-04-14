package api.matchers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiMatchers {
    private static final String myEmailOnMainPattern = "<a href=\"/customer/info\" class=\"account\">(\\w+@\\w+.\\w+)</a>";
    private static final String itemsInCart = "name=\"removefromcart\" value=\"(\\d+)\" />";

    public static String getEmail(String pageText) {
        Pattern pattern = Pattern.compile(myEmailOnMainPattern);
        Matcher matcher = pattern.matcher(pageText);
        if (matcher.find())
            return matcher.group(1);
        else
            return "Email not found";
    }

    public static List<String> getItemsFromCart(String pageText) {
        Pattern pattern = Pattern.compile(itemsInCart);
        Matcher matcher = pattern.matcher(pageText);
        List<String> itemIds = new ArrayList<>();
        while (matcher.find())
            itemIds.add(matcher.group(1));
        return itemIds;
    }
}
