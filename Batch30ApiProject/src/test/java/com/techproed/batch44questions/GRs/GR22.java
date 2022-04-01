package com.techproed.batch44questions.GRs;

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
import static org.junit.Assert.*;

public class GR22 extends HerokuAppTestBase {
    /*
  https://restful-booker.herokuapp.com/booking/47
         {
     "firstname": "Ali",
     "lastname": "Can",
     "totalprice": 700,
     "depositpaid": true,
     "bookingdates": {
         "checkin": "2022-02-01",
         "checkout": "2022-02-11"}
         }
  1) JsonPhat
  2) De-Serialization
  */
    @Test
    public void test22() {
        //1) URL OLUSTUR
        spec02.pathParams("first", "booking", "second", 17);

        //expectedData
        HerokuAppTestData testObje = new HerokuAppTestData();
        HashMap<String,Object> expectedDataMap=testObje.setUpTestData();
        System.out.println(expectedDataMap);

        //request and response
        Response response = given().contentType(ContentType.JSON)
                .spec(spec02).when().get("/{first}/{second}");
        response.prettyPrint();

        //actualMap

        HashMap<String,Object> actualDataMap = response.as(HashMap.class);

        //assertion
        //json
        JsonPath jsonPath = response.jsonPath();
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));

        //deserialization
        //"firstname": "Ali",
        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        //     "lastname": "Can",
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("firstname"));
        //     "totalprice": 700,
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        //     "depositpaid": true,

        //     "bookingdates": {
        //         "checkin": "2022-02-01",
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        //         "checkout": "2022-02-11"






    }
}
