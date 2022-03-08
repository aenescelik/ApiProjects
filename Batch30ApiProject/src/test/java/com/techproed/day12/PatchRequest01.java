package com.techproed.day12;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderTestBase {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {
      "title": "API calismaliyim"
     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}
     */

    @Test
    public void test() {

        // url
        spec01.pathParams("parametre1", "todos",
                "parametre2", 198);

        //expexted ve request data oluştur.
        JsonPlaceHolderTestData testObject = new JsonPlaceHolderTestData();
        JSONObject requestData = testObject.setUpPatchRequestData();
        JSONObject expectedData = testObject.setUpPatchExpectedData();
        System.out.println(requestData+"\n"+expectedData);

        //request Gönder
        Response response = given().contentType(ContentType.JSON)
                .spec(spec01)
                .auth().basic("admin","password123")
                .body(requestData.toString())
                .when().patch("/{parametre1}/{parametre2}");
        response.prettyPrint();

        // deserialization
        HashMap<String,Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(200,response.getStatusCode());

        // "userId": 10,
        Assert.assertEquals(expectedData.getInt("userId"),
                actualDataMap.get("userId"));
        // "title": "API calismaliyim"
        Assert.assertEquals(expectedData.getString("title"),
                actualDataMap.get("title"));
        // "completed": true,
        Assert.assertEquals(expectedData.getBoolean("completed"),
                actualDataMap.get("completed"));
        // "id": 198
        Assert.assertEquals(expectedData.getInt("id"),actualDataMap.get("id"));

        //Matchers

        response.then().assertThat().statusCode(200).body(
                "completed",Matchers.equalTo(expectedData.getBoolean("completed"))
        );


    }
}
