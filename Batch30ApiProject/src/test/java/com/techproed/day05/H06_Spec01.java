package com.techproed.day05;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class H06_Spec01 extends JsonPlaceHolderTestBase {
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
    public void test01() {
        /*spec01.pathParams("param01","todos"
                            ,"param02","123");*/

        Response response = given().accept("application/json")
                .spec(spec01).when().get("/todos/123");

        response.prettyPrint();

        //    status kodunun 200
        response.then().assertThat().statusCode(200).
        //    ve content type'inin "application/json"
                contentType("application/json").
        //    ve Headers'daki "Server" in "cloudflare"
                header("Server", Matchers.equalTo("cloudflare")).
        //    ve response body'deki "userId"'nin 7
                body("userId",Matchers.equalTo(7)).
        //    ve "title" in "esse et quis iste est earum aut impedit"
                body("title",Matchers.equalTo("esse et quis iste est earum aut impedit")).
        //    ve "completed" bolumunun false oldugunu test edin
                body("completed",Matchers.equalTo(false));

    }
}
