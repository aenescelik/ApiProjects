package com.techproed.batch44questions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GR5_AssertThatActualExpectedFarkiniGosteriyor {
     /*
       https://jsonplaceholder.typicode.com/todos/123 url'ine
        GET request'i yolladigimda gelen response’un
		status kodunun 200
   		ve content type'inin "application/json"
		ve Headers'daki "Server" in "cloudflare"
		ve response body'deki "userId"'nin 7
		ve "title" in "esse et quis iste est earum aut impedit"
		ve "completed" bolumunun false oldugunu test edin
     */

    @Test
    public void test(){
        String url = "https://jsonplaceholder.typicode.com/todos/123";

        Response response = given().contentType(ContentType.JSON).when().get(url);

        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType("application/json")
                .headers("Server", equalTo("cloudflare"),
                        "X-Content-Type-Options",equalTo("nosniff"))
                                .body("userId",equalTo(7),
                        "id",equalTo(123),
                        "title",equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));

    }

}
