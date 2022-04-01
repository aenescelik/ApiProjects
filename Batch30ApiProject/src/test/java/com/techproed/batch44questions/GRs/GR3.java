package com.techproed.batch44questions.GRs;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GR3 {
    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
    GET request'i yolladigimda gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in "Sally"
    ve lastname'in "Ericsson"
    ve checkin date'in 2018-10-07"
    ve checkout date'in 2020-09-30 oldugunu test edin
     */

    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking/7";

        Response response = given().accept("application/json").when().get(url);

        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType("application/json")
                .body("firstname",equalTo("Mark"),
                        "lastname", equalTo("Jones") ,

                       "bookingdates.checkin",equalTo("2017-01-02") );

    }
}
