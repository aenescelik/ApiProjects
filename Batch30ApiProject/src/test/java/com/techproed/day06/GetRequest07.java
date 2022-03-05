package com.techproed.day06;

import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
        spec02.pathParams("param1","booking",
                            "param2",5);

        Response response = given().
                accept("application/json").
                spec(spec02).
                when().get("/{param1}/{param2}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        Assert.assertEquals("Sally",jsonPath.getString("firstname"));
        Assert.assertEquals("Wilson",jsonPath.getString("lastname"));
        Assert.assertEquals(833,jsonPath.getInt("totalprice"));
        Assert.assertEquals(true,jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("2016-08-07",jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2019-02-26",jsonPath.getString("bookingdates.checkout"));
    }
}
