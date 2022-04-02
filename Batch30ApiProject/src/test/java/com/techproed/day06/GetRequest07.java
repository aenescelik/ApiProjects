package com.techproed.day06;

import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest07 extends HerokuAppTestBase {
    /*
    https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
    HTTP Status Code’unun 200
    ve response content type’inin “application/JSON” oldugunu
    ve response body’sinin asagidaki gibi oldugunu test edin
    {"firstname": Sally,
            "lastname": "Wilson",
            "totalprice": 833,
            "depositpaid": true,
            "bookingdates": {
               "checkin": "2016-08-07",
                "checkout":"2019-02-26"
                 }
    }
*/
    @Test
    public void test01(){
        spec02.pathParams("parametre1","booking",
                "parametre2",5);

        Response response = given().contentType(ContentType.JSON)
                        .spec(spec02)
                                .when().get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        assertTrue(response.getStatusCode()==200);
        assertEquals("Mark",jsonPath.getString("firstname"));
        assertEquals("Wilson",jsonPath.getString("lastname"));
        assertEquals(380,jsonPath.getInt("totalprice"));
        assertEquals(true,jsonPath.getBoolean("depositpaid"));
        assertEquals("2019-07-14",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2020-05-05",jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));

    }
}
