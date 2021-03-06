package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class H01 {

    @Test
    public void test01(){
        /*
   https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde
   donecek cevap(response) icin
   HTTP status kodunun 200
   Content Type’in Json
   Ve Status Line’in HTTP/1.1 200 OK
   Oldugunu test edin
  */
        String url ="https://restful-booker.herokuapp.com/booking/3";

        Response response = given().accept("application/json")
                .when().get(url);

        response.prettyPrint();

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());
        Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");


    }
}
