package tests;

import config.DriverConfig;
import config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;

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
}
