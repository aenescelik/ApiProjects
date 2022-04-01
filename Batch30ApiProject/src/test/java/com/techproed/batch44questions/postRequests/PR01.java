package com.techproed.batch44questions.postRequests;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PR01 extends HerokuAppTestBase {
     /*
       https://restful-booker.herokuapp.com/booking
       { "firstname": "Ali",
                  "lastname": "Can",
                  "totalprice": 500,
                  "depositpaid": true,
                  "bookingdates": {
                      "checkin": "2022-03-01",
                      "checkout": "2022-03-11"
                   }
    }
    gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    }
       "booking": {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
                               "checkin": "2022-03-01",
                                "checkout": "2022-03-11"
           }
       }
    }
    olduğunu test edin
        */

    @Test
    public void test01() {

        //1) URL OLUSTUR
        spec02.pathParam("parametre1", "booking");

        //
        HerokuAppTestData testObje = new HerokuAppTestData();
        JSONObject expectedRequestData = testObje.setUpTestAndRequestData();
        System.out.println(expectedRequestData);

        //

        Response response = given().contentType(ContentType.JSON)
                .spec(spec02).auth().basic("admin","password")
                .body(expectedRequestData.toString()).when().post("/{parametre1}");
        response.prettyPrint();


        JsonPath jsonPath = response.jsonPath();
//"booking": {
//           "firstname": "Ali",
        assertEquals(expectedRequestData.getString("firstname"),
                jsonPath.getString("booking.firstname"));
//           "lastname": "Can",
        assertEquals(expectedRequestData.getString("lastname"),
                jsonPath.getString("booking.lastname"));
//           "totalprice": 500,
        assertEquals(expectedRequestData.getInt("totalprice"),
                jsonPath.getInt("booking.totalprice"));
//           "depositpaid": true,
//           "bookingdates": {
//                               "checkin": "2022-03-01",
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                jsonPath.getString("booking.bookingdates.checkin"));
//                                "checkout": "2022-03-11"

    }
}
