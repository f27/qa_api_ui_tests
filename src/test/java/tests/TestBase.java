package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.DriverHelper.*;

public class TestBase {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = TestData.getApiUrl();
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
