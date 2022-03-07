package com.techproed.day08;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

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

        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

        Assert.assertEquals(  ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        // neden böyle yaptik. check in ve check out degerleri de bir map

        Assert.assertEquals(   ((Map) expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map) actualDataMap.get("bookingdates")).get("checkout")  );

        // daha once casting islemi yapildigi icin java bunu hafizasinda tutar.
        Assert.assertEquals( ((Map) expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map<?, ?>)// java can bunu otomatik olusturdu.
                        actualDataMap.get("bookingdates")).get("checkin"));
    }
}