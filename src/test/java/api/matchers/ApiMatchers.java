package api.matchers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiMatchers {
    private static final String myEmailOnMainPattern = "<a href=\"/customer/info\" class=\"account\">(.*?[@].*?)</a>";

    public static String getEmailFromMain(String pageText) {
        Pattern pattern = Pattern.compile(myEmailOnMainPattern);
        Matcher matcher = pattern.matcher(pageText);
        if (matcher.find())
            return matcher.group(1);
        else
            return "Email not found";
    }
}
