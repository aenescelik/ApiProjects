package com.techproed.day08;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest12 extends HerokuAppTestBase {
    /*
   https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
dönen response body nin
{
"firstname": "Eric",
"lastname": "Smith",
"totalprice": 555,
"depositpaid": false,
"bookingdates": {
"checkin": "2016-09-09",
"checkout": "2017-09-21"
}
} gibi olduğunu test edin
    */
    @Test
    public void test() {

        // url oluştur

        spec02.pathParams("parametre1", "booking",
                "parametre2", 1);

        HerokuAppTestData expectedObje = new HerokuAppTestData();
        HashMap<String,Object>expectedDataMap=expectedObje.setUpTestData();

        System.out.println(expectedDataMap);

        //request gönder

        Response response=given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // Deserialization
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);

        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

        assertEquals(  ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
         ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        // neden böyle yaptik. check in ve check out degerleri de bir map

        assertEquals(((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map<?, ?>) actualDataMap.get("bookingdates")).get("checkout"));

        // daha once casting islemi yapildigi icin java bunu hafizasinda tutar.
        /*Assert.assertEquals( ((Map) expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map<?, ?>)// java can bu castingi otomatik olusturur.
                        actualDataMap.get("bookingdates")).get("checkin"));*/
        assertEquals(
                ((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map<?, ?>) actualDataMap.get("bookingdates")).get("checkout")
        );

        JsonPath jsonPath = response.jsonPath();
        assertEquals(expectedDataMap.get("firstname"),jsonPath.getString("firstname"));
        assertEquals(expectedDataMap.get("lastname"),jsonPath.getString("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),jsonPath.getInt("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),jsonPath.getBoolean("depositpaid"));

        assertEquals(
                ((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin")
        );
        assertEquals(
                ((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkout"),
                jsonPath.getString("bookingdates.checkout")
        );

    }
}
