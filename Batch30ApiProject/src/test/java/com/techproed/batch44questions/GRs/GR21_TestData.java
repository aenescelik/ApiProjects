package com.techproed.batch44questions.GRs;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GR21_TestData extends JsonPlaceHolderTestBase {
     /*
   https://jsonplaceholder.typicode.com/todos/2
   1) Status kodunun 200,
   2) respose body'de,
            "completed": değerinin false
            "title”: değerinin “quis ut nam facilis et officia qui”
            "userId" sinin 1 ve
       header değerlerinden
            "via" değerinin “1.1 vegur” ve
            "Server" değerinin “cloudflare” olduğunu test edin…
   */
    @Test
    public void test(){
        spec01.pathParams("p1","todos",
                "p2",2);

        JsonPlaceHolderTestData obje = new JsonPlaceHolderTestData();
        HashMap<String,Object> expectedDataMap = (HashMap<String, Object>) obje.setUpTestData();
        System.out.println(expectedDataMap);

        Response response =given().contentType(ContentType.JSON)
                .spec(spec01).when().get("/{p1}/{p2}");
        response.prettyPrint();

        HashMap<String,Object> actulaDataMap= response.as(HashMap.class);

        assertEquals(expectedDataMap.get("userId"),actulaDataMap.get("userId"));
    }
}
