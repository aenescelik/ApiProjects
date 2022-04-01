package com.techproed.batch44questions.GRs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GR4_HasSizeHasItem {
    /*
    http://dummy.restapiexample.com/api/v1/employees  url'ine
    GET request'i yolladigimda gelen response'un
    status kodunun 200 ve content type'inin "application/json"
    ve employees sayisinin 24
    ve employee'lerden birinin "Ashton Cox"
    ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
    */
    @Test
    public void test(){
        String url = "http://dummy.restapiexample.com/api/v1/employees";

        Response response  = given().contentType(ContentType.JSON).when().get(url);

        response.prettyPrint();

        response.then().statusCode(200).contentType("application/json")
                .body("data.id",hasSize(24),
                        "data.employee_name[2]",equalTo("Ashton Cox"),
                        "data[0].employee_name",equalTo("Tiger Nixon"),
                        "data.employee_name",hasItem("Ashton Cox"),

                "data.employee_age",hasItems(21,61,23));

    }
}
