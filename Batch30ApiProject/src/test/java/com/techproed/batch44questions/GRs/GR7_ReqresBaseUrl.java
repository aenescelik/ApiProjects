package com.techproed.batch44questions.GRs;

import com.techproed.testBase.ReqresTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GR7_ReqresBaseUrl extends ReqresTestBase {
    /*
    https://reqres.in/api/users URL request olustur.
    body icerisindeki idsi 5 olan datayi
    1) Matcher CLASS ile
    2) JsonPath ile dogrulayin.
    */

    @Test
    public void test(){

        spec04.pathParams("parametre1","api",
                "parametre2","users");

        Response response =given().accept(ContentType.JSON).spec(spec04).
                when().get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //1)  body icerisindeki idsi 5 olan datayi Matcher CLASS ile
        response.then().assertThat().body("data[4].email", equalTo("charles.morris@reqres.in"),
                "data[4].first_name", equalTo("Charles"),
                "data[4].last_name", equalTo("Morris"));

        //2) JsonPath ile dogrulayin.
        JsonPath jsonPath = response.jsonPath();

        assertEquals("charles.morris@reqres.in",jsonPath.getString("data[4].email"));
        assertEquals("Charles",jsonPath.getString("data[4].first_name"));
        assertEquals("Morris",jsonPath.getString("data[4].last_name"));

    }



}
