package api;

import java.util.Map;

import static api.spec.RequestSpec.authorizedSpec;
import static api.spec.RequestSpec.spec;
import static endpoints.ApiEndpoints.LOGIN;
import static io.restassured.RestAssured.given;

public class Auth {
    public Map<String, String> login(String email, String password, Boolean rememberMe) {
        Map<String,String> authCookies =
                given(spec)
                        .formParam("Email", email)
                        .formParam("Password", password)
                        .formParam("RememberMe", rememberMe)
                        .when()
                        .post(LOGIN.getPath())
                        .then()
                        .statusCode(302)
                        .extract()
                        .cookies();
        authorizedSpec.cookies(authCookies);

        return authCookies;
    }

    public Map<String, String> login(String email, String password) {
        return login(email, password, false);
    }
}
