package com.techproed.testData;

import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.HashMap;

public class HerokuAppTestData {
/*
    {
        "firstname": "Eric",
            "lastname": "Smith",
            "totalprice": 555,
            "depositpaid": false,
            "bookingdates": {
        "checkin": "2016-09-09",
                "checkout": "2017-09-21"
    }
    }
*/

    public HashMap<String, Object> setUpTestData() {

        HashMap<String, Object> bookingdates = new HashMap<String, Object>();
        bookingdates.put("checkin", "2016-04-12");
        bookingdates.put("checkout", "2017-07-21");


        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("firstname", "Susan");
        expectedData.put("lastname", "Jones");
        expectedData.put("totalprice", 582);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);

        return expectedData;

    }
}