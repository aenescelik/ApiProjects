package com.techproed.day06;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest09 extends DummyTestBase {
  /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde,
status kodun 200,
gelen body de,
5. çalışanın isminin "Airi Satou" olduğunu ,
6. çalışanın maaşının "372000" olduğunu ,
Toplam 24 tane çalışan olduğunu,
"Rhona Davidson" ın employee lerden biri olduğunu
"21", "23", "61" yaşlarında employeeler olduğunu test edin
     */

    @Test
    public void test(){

        spec03.pathParam("parametre1","employees");

        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        //status kodun 200,
        assertEquals(200,response.getStatusCode());

        //5. çalışanın isminin "Airi Satou" olduğunu ,
        assertEquals("Airi Satou",jsonPath.getString("data.employee_name[4]"));
        //6. çalışanın maaşının "372000" olduğunu ,
        assertEquals(372000,jsonPath.getInt("data[5].employee_salary"));
        //Toplam 24 tane çalışan olduğunu,
        assertEquals(24,jsonPath.getList("data.employee_age").size());
        //"Rhona Davidson" ın employee lerden biri olduğunu
        assertTrue(jsonPath.getString("data.employee_name").contains("Rhona Davidson"));
        //"21", "23", "61" yaşlarında employeeler olduğunu test edin

        List<Integer> arananyaslar = Arrays.asList(21,23,61);

//    List<Integer> arananyaslar=new ArrayList<Integer>();
//    arananyaslar.add(21);
//    arananyaslar.add(23);
//    arananyaslar.add(61);


        assertTrue(jsonPath.getList("data.employee_age").containsAll(arananyaslar));




    }



}