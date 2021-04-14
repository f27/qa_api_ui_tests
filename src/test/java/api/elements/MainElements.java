package api.elements;

import org.jsoup.Jsoup;

public class MainElements {
    private static final String myEmailSelector = "div.header-links ul li a.account";

    public static String getEmail(String pageText) {
        return Jsoup.parse(pageText).select(myEmailSelector).text();
    }
}
