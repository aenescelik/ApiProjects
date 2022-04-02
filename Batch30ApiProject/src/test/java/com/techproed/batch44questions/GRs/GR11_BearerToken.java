package com.techproed.batch44questions.GRs;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GR11_BearerToken {
    String endPoint = "http://www.gmibank.com/api/tp-customers";
    String bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaDQ0YXBpIiwiYXV0aCI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE2NDg5MzMxODZ9.Hz9TPY0iEFHqZbi6mXC7P9fWob2uCC6mvOd5sveZCNKmKioPPLRzeh1LH1CZiiXl-bqhWjXkQHq35D13dQ1wiA";
    @Test
    public void test(){

        Response response = given()
                .header("Authorization","Bearer " + bearerToken)
                .when().get(endPoint).then().extract().response();

        response.prettyPrint();
    }
}
