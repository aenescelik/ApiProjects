package com.techproed.batch44questions.GRs;

import com.techproed.utilities.Authentication;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GR12 extends Authentication{

    String endPoint;

    @Test
    public void test(){
        endPoint = "http://www.gmibank.com/api/tp-customers";

        Response response = given().contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ generateToken())
                .when()
                .get(endPoint).then().extract().response();

        response.prettyPrint();
        //response.prettyPeek(); boyle header body ne varsa veriyor.
        response.then().assertThat().statusCode(200);

    }

}
