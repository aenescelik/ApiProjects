package com.techproed.day06;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class H09_ContainList extends DummyTestBase {
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
    public void test() {

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());
        // Assert.assertTrue(response.getStatusCode()==200);

        //gelen body de,
        //5. çalışanın isminin "Airi Satou" olduğunu ,
        Assert.assertEquals("Airi Satou",jsonPath.getString("data[4].employee_name"));
        //6. çalışanın maaşının "372000" olduğunu ,
        Assert.assertEquals(137500,jsonPath.getInt("data[6].employee_salary"));
        //Toplam 24 tane çalışan olduğunu,
        Assert.assertEquals(24,jsonPath.getList("data.employee_salary").size());
        //"Rhona Davidson" ın employee lerden biri olduğunu
        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));
        //"21", "23", "61" yaşlarında employeeler olduğunu test edin

        //List<Integer> list = Arrays.asList(21,23,61);

        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(21);
        list2.add(61);
        list2.add(23);
        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(list2));

    }
}
