package tests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static final SelenideElement
            emailInput = $("#Email"),
            passwordInput = $("#Password"),
            rememberMeCheckbox = $("#RememberMe"),
            loginButton = $(".login-button");

    public void login(String email, String password, Boolean rememberMe) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        rememberMeCheckbox.setSelected(rememberMe);
        loginButton.click();
    }
}
