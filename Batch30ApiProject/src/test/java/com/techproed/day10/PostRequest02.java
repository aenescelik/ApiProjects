package com.techproed.day10;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerokuAppTestBase {
     /*
    https://restful-booker.herokuapp.com/booking
    { "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }
 gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
 "booking": {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
     */

    @Test
    public void test() {
        // url
        spec02.pathParam("parametre1","booking");

        HerokuAppTestData testData = new HerokuAppTestData();
        //requestBody   ve expected Data aynı olduğu için tek bir JSONObject kullanılması yeterlidir.
        JSONObject expectedRequestData = testData.setUpTestAndRequestData();
        System.out.println(expectedRequestData);

        Response response = given().contentType(ContentType.JSON).spec(spec02).body(expectedRequestData.
                        toString())//map olmadigi icin toString yapilmali
                .auth().basic("admin","password123")
                .when().post("/{parametre1}");

        response.prettyPrint();

        //De Serialization Yöntemi
        HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        //actualdata masp olmali

        Assert.assertEquals(200,response.getStatusCode());

        // dönen response body nin ,
        // "booking": {
        //         "firstname": " Selim ",
        Assert.assertEquals(expectedRequestData.getString("firstname"),
                ((Map<?, ?>)actualDataMap.get("booking")).get("firstname"));
        //         "lastname": " Ak ",
        Assert.assertEquals(expectedRequestData.getString("lastname"),
                ((Map<?, ?>)actualDataMap.get("booking")).get("lastname"));
        //         "totalprice":  11111,
        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("totalprice"));
        //         "depositpaid": true,
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("depositpaid"));
        //         "bookingdates": {
        //             "checkin": "2020-09-01",
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        //              "checkout": " 2020-09-21”
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));


        // 2- jonPath ile

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedRequestData.getString("firstname"),
                jsonPath.getString("booking.firstname"));

        Assert.assertEquals(expectedRequestData.getString("lastname"),
                jsonPath.getString("booking.lastname"));

        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                jsonPath.getInt("booking.totalprice"));

        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                jsonPath.getBoolean("booking.depositpaid"));


        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                jsonPath.getString("booking.bookingdates.checkin"));


        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                jsonPath.getString("booking.bookingdates.checkout"));
    }
}
