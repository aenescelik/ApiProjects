package com.techproed.testData;

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
        bookingdates.put("checkin", "2022-01-06");
        bookingdates.put("checkout", "2022-01-19");


        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("firstname", "Eric");
        expectedData.put("lastname", "Jackson");
        expectedData.put("totalprice", 305);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);

        return expectedData;

    }
    public JSONObject setUpTestAndRequestData(){

        JSONObject bookingdates=new JSONObject();
        bookingdates.put("checkin","2020-09-09");
        bookingdates.put("checkout","2020-09-21");

        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("firstname","Batch30");
        expectedRequest.put("lastname","bitti");
        expectedRequest.put("totalprice",123);
        expectedRequest.put("depositpaid",false);
        expectedRequest.put("bookingdates",bookingdates);

        return expectedRequest;

    }
}
