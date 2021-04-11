package tests;

import api.Auth;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.DriverHelper.*;

public class TestBase {
    static Map<String, String> authCookies;

    @BeforeAll
    static void setup(TestInfo info) {
        RestAssured.baseURI = TestData.getApiUrl();
        authCookies = new Auth().login(TestData.getUserEmail(), TestData.getUserPassword());
        configureDriver();
    }

    @AfterEach
    void addAttachments(TestInfo info) {
        if (!info.getTags().contains("API")) {

            String sessionId = getSessionId();

            attachScreenshot("Last screenshot");
            attachPageSource();
            attachAsText("Browser console logs", getConsoleLogs());
            if (isVideoOn()) attachVideo(sessionId);

            closeWebDriver();
        }
    }
}
