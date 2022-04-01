package com.techproed.batch44questions.GRs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GR6 {
     /*
    https://restful-booker.herokuapp.com/booking/5 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in “Jim"
    ve totalprice’in 600
    ve checkin date'in 2015-06-12"oldugunu test edin
     */

    @Test
    public void test06() {
        String url = "https://restful-booker.herokuapp.com/booking/5";

        Response response = given().contentType(ContentType.JSON)
                .when().get(url);

        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .body("firstname",equalTo("Sally"),
                        "lastname",equalTo("Smith") ,
                        "totalprice",equalTo(869),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2019-11-17"));
    }
}
