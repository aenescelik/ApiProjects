package com.techproed.day05;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest06 extends JsonPlaceHolderTestBase {
    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare"
    ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
    */
    @Test
    public void test01(){
        //String url="https://jsonplaceholder.typicode.com/todos/123";
        spec01.pathParams("parametre1","todos",
                     "parametre2",123);

        Response response = given().contentType(ContentType.JSON).spec(spec01)
                        .when().get("/{parametre1}/{parametre2}");

        response.prettyPeek();// postmana sormadan headerleri vs. almak icin gerekli

        response.then().assertThat().statusCode(200).contentType("application/json")
                .body("userId",equalTo(7)
                ,"id",equalTo(123),
                        "title",equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));
    }
}
