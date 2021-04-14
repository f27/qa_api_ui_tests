package tests;

import config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    private static final TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());

    public static String getWebUrl() {
        return testDataConfig.webUrl();
    }

    public static String getApiUrl() {
        return testDataConfig.apiUrl();
    }

    public static String getUserEmail() {
        return testDataConfig.userEmail();
    }

    public static String getUserPassword() {
        return testDataConfig.userPassword();
    }

    public static String getSimpleItemId() {
        return testDataConfig.simpleItemId();
    }

    public static String getSimpleItemName() {
        return testDataConfig.simpleItemName();
    }

    public static String getSimpleItemQuantity() {
        return testDataConfig.simpleItemQuantity();
    }

    public static String getWithDetailsItemId() {
        return testDataConfig.withDetailsItemId();
    }

    public static String getWithDetailsItemName() {
        return testDataConfig.withDetailsItemName();
    }

    public static String getWithDetailsItemQuantity() {
        return testDataConfig.withDetailsItemQuantity();
    }

    public static Map<String, String> getWithDetailsItemDetails() {
        Map<String, String> details = new HashMap<>();
        String[] detailsData = testDataConfig.withDetailsItemDetails();
        for (String detailData :detailsData) {
            String[] keyValue = detailData.split(":");
            details.put(keyValue[0], keyValue[1]);
        }
        return details;
    }
}
