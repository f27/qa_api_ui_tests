package api.spec;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    public static RequestSpecification spec = RestAssured
            .given()
            .log().uri()
            .contentType(ContentType.URLENC);

    public static RequestSpecification authorizedSpec = RestAssured
            .given(spec);
}
