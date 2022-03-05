package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class H03_BodyAssert {

    @Test
    public void test() {
         /*
       https://restful-booker.herokuapp.com/booking/7 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in "Mary"
    ve lastname'in "Jones"
    ve checkin date'in 2018-10-07"
    ve checkout date'in 2020-09-30 oldugunu test edin
     */

        String url = "https://restful-booker.herokuapp.com/booking/7";

        Response response = given().accept("application/json")
                .when().get(url);

        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", equalTo("Susan")
                ,"lastname", equalTo("Wilson")
                ,"bookingdates.checkin", equalTo("2015-06-16")
                ,"bookingdates.checkout", equalTo("2020-05-05"));

    }
}