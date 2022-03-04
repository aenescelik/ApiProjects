package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GetRequest03 {

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

    @Test
    public void test() {

        String url = "https://restful-booker.herokuapp.com/booking/7";
        Response response = given().accept("application/json")
                .when().get(url);

        response.prettyPrint();
/*
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Mary")).
                body("lastname", Matchers.equalTo("Jackson")).
                body("totalprice", Matchers.equalTo(286)).
                body("depositpaid", Matchers.equalTo(true)).
                body("bookingdates.checkin", Matchers.equalTo("2017-01-19")).
                body("bookingdates.checkout", Matchers.equalTo("2021-09-06"));
*/
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Mary"),
                        "lastname", equalTo("Jackson"),
                        "totalprice", equalTo(903),
                        "bookingdates.checkin",equalTo("2017-01-19"),
                        "bookingdates.checkout",equalTo("2021-09-06"));
    }
}
