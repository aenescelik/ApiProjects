package com.techproed.batch44questions.GRs;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GR10_ListFalan extends DummyTestBase {
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
   JSONPATH KULLARAK
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

        assertEquals(200, response.getStatusCode());
        // Assert.assertTrue(response.getStatusCode()==200);

        //   5. çalışanın isminin "Airi Satou" olduğunu ,
        assertEquals("Airi Satou",jsonPath.getString("data[4].employee_name"));

        //   6. çalışanın maaşının "372000" olduğunu ,
        assertEquals(372000,jsonPath.getInt("data[5].employee_salary"));
        //   Toplam 24 tane çalışan olduğunu,
        assertEquals(24,jsonPath.getList("data.id").size());
        //   "Rhona Davidson" ın employee lerden biri olduğunu
        assertTrue(jsonPath.getString("data.employee_name").contains("Rhona Davidson"));
        //   "21", "23", "61" yaşlarında employeeler olduğunu test edin

        List<Integer> list = Arrays.asList(21,23,61);

        assertTrue(jsonPath.getList("data.employee_age").containsAll(list));

        List<Integer> list2 =new ArrayList<>();

        list2.add(21);
        list2.add(23);
        list2.add(61);

        assertTrue(jsonPath.getList("data.employee_age").containsAll(list2));

    }
}
