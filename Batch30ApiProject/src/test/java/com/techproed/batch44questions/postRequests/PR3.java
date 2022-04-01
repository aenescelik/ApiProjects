package com.techproed.batch44questions.postRequests;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class PR3 extends JsonPlaceHolderTestBase {
    /*
  https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
   {
   "userId": 55,
   "title": "Tidy your room",
   "completed": false
 }
   Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
 {
   "userId": 55,
   "title": "Tidy your room",
   "completed": false,
   "id": 201
  }
*/
    @Test
    public void test03() {

        //1) URL OLUSTUR
        spec01.pathParam("bir", "todos");

        JsonPlaceHolderTestData testObje= new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setUpPostData();
        System.out.println(expectedRequest);

        Response response = given().contentType(ContentType.JSON).spec(spec01).auth().basic("admin","password")
                .body(expectedRequest.toString()).when().post("/{bir}");
        response.prettyPrint();
        //

        response.then().assertThat().statusCode(expectedRequest.getInt("statusCode"))
                .body("completed", equalTo(expectedRequest.getBoolean("completed")),
                        "title",equalTo(expectedRequest.getString("title")),
                        "userId",equalTo(expectedRequest.getInt("userId")));

        JsonPath jsonPath = response.jsonPath();

        assertEquals(expectedRequest.getInt("statusCode"),response.getStatusCode());
        assertEquals(expectedRequest.getBoolean("completed"),
                jsonPath.getBoolean("completed"));

    }
}