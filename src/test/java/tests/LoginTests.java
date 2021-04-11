package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginTests extends TestBase{
    @Test
    void firstTest() {
        open("/");
    }
}
