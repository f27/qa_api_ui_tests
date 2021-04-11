package tests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static final SelenideElement accountName = $("a.account");

    public String getAccountName() {
        return accountName.getText();
    }
}
