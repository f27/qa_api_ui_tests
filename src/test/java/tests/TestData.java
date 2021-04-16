package tests;

import config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.*;

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

    public static List<String> getWithDetailsItemDetails() {
        String[] detailsData = testDataConfig.withDetailsItemDetails();
        return Arrays.asList(detailsData);
    }
}
