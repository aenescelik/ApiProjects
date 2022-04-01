package com.techproed.batch44questions.GRs;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GR16_ExpectedData extends JsonPlaceHolderTestBase {
     /*
   https://jsonplaceholder.typicode.com/todos/7
   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */

    @Test
    public void test16(){
        //1) URL OLUSTURMA
        spec01.pathParams("p1","todos",
                "p2",7);
        //2) EXPECTED(BEKLENEN) DATA OLUSTUR
        JsonPlaceHolderTestData obje = new JsonPlaceHolderTestData();
        HashMap<String,Object> expectedData = (HashMap<String, Object>) obje.setUpTestData();
        System.out.println(expectedData);

        //3) REQUEST VE RESPONSE
        Response response = given().contentType(ContentType.JSON)
                .spec(spec01).when().get("/{p1}/{p2}");

        response.prettyPrint();

        //DATAYI JSON FORMATI -> JAVA'YA: De-Serialization
        //DATAYI JAVA'DAN -> JSON'A: Serialization

        //4 - actual map creating

        HashMap<String,Object> actualData = response.as(HashMap.class);

         /*
    De-Serialization aşağıdaki JSON formatındaki datayı map'e dönüştür.
    {
    "userId": 1,
    "id": 7,
    "title": "illo expedita consequatur quia in",
    "completed": false
    }
    JSON DATASININ MAP'E DÖNÜŞMÜŞ HALİ
    ACTUAL DATA: {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}
         */

      // Assertion

        //   "userId": 1,
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        //   "id": 7,
        assertEquals(expectedData.get("id"),actualData.get("id"));
        //   "title": "illo expedita consequatur quia in",
        assertEquals(expectedData.get("title"),actualData.get("title"));
        //   "completed": false
        assertEquals(expectedData.get("completed"), actualData.get("completed"));




    }
}
