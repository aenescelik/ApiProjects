package com.techproed.batch44questions;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GR11_BearerToken {
    String endPoint = "http://www.gmibank.com/api/tp-customers";
    String bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaDQ0YXBpIiwiYXV0aCI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE2NDcxMTY3NDF9.dFdRkzX_SDj7YarmrZ5fwxSDFpyETckIMw5a_u_R6X_RYs-8JED7uPAju9eOnmrqcJjkrCSocVjCqNCqa5-9dQ";
    @Test
    public void test(){

        Response response = given()
                .header("Authorization","Bearer " + bearerToken)
                .when().get(endPoint).then().extract().response();

        response.prettyPrint();
    }
}
