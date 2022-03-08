package com.techproed.day12;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteRequest01 extends DummyTestBase {
     /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
     */
    @Test
    public void test(){
        spec03.pathParams("parametre1","delete",
                "parametre2",2);

        DummyTestData testObje = new DummyTestData();
        JSONObject expectedData = testObje.setUpDeleteExpectedData();
        System.out.println(expectedData);

        Response response = given().contentType(ContentType.JSON)
                .spec(spec03)
                .auth().basic("admin","password123")
                .when().delete("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());
        // "status": "success",
        Assert.assertEquals(expectedData.getString("status"),
                json.getString("status"));
        // "data": "2",
        Assert.assertEquals(expectedData.getString("data"),
                json.getString("data"));
        // "message": "Successfully! Record has been deleted"
        Assert.assertEquals(expectedData.getString("message"),
                json.getString("message"));

        // Matchers

        response.then().assertThat().statusCode(response.getStatusCode())
                .body("message", equalTo(expectedData.getString("message")),
                        "data", equalTo(expectedData.getString("data")),
                        "status", equalTo(expectedData.getString("status")));



    }

}
