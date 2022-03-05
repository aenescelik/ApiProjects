package com.techproed.day06;

import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class H07_JsonPath extends HerokuAppTestBase {

    @Test
    public void test01() {
        //https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
        spec02.pathParams("param01", "booking",
                "param02", "5");

        Response response = given().accept("application/json").spec(spec02)
                .when().get("/{param01}/{param02}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        //HTTP Status Code’unun 200
        response.then().assertThat().statusCode(200).
        //ve response content type’inin “application/JSON” oldugunu
                contentType("application/json");
        //ve response body’sinin asagidaki gibi oldugunu test edin
        //{"firstname": Sally,
        Assert.assertEquals("Mary",jsonPath.getString("firstname"));
        //"lastname": "Wilson",
        Assert.assertEquals("Jones",jsonPath.getString("lastname"));
        //"totalprice": 833,
        Assert.assertEquals(919,jsonPath.getInt("totalprice"));
        //"depositpaid": true,
        Assert.assertEquals(true,jsonPath.getBoolean("depositpaid"));
        //"bookingdates": {
        //   "checkin": "2016-08-07",
        Assert.assertEquals("2021-09-06",jsonPath.getString("bookingdates.checkin"));
        //    "checkout":"2019-02-26"
        Assert.assertEquals("2022-02-02",jsonPath.getString("bookingdates.checkout"));


    }
}
