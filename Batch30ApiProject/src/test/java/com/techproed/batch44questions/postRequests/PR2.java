package com.techproed.batch44questions.postRequests;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PR2 extends DummyTestBase {
    /*
  http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
  {
      "name":"Ali Can",
      "salary":"2000",
      "age":"40",
  }
  gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,
  {
      "status": "success",
      "data": {
      “id”:…
  },
      "message": "Successfully! Record has been added."
  }
  olduğunu test edin
   */
    @Test
    public void test02() {

        //1) URL OLUSTUR
        spec03.pathParams("bir","create");

        //2) EXPECTED DATA
        DummyTestData obje = new DummyTestData();
        HashMap<String, String> requestBodyData = obje.setUpRequestBody();
        HashMap<String, Object> expectedDataMap = obje.setUpExpectedData();
        System.out.println(expectedDataMap);
        //Request ve response

        Response response = given().contentType(ContentType.JSON).spec(spec03).auth().basic("admin","password")
                .body(requestBodyData).when().post("/{bir}");
        response.prettyPrint();

        //dogrulama
HashMap<String,Object> actualDataMap= response.as(HashMap.class);
        JsonPath jsonPath = response.jsonPath();


      //"status": "success",
        assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        assertEquals(expectedDataMap.get("status"),jsonPath.getString("status"));
      //"data": {
        assertEquals(((Map)expectedDataMap.get("data")).get("name"),jsonPath.getString("data.name"));
      //“id”:…

      //"message": "Successfully! Record has been added."
        assertEquals(expectedDataMap.get("message"),jsonPath.getString("message"));
        assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));


    }
}
