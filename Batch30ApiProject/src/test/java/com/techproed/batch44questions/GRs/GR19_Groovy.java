package com.techproed.batch44questions.GRs;

import com.techproed.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GR19_Groovy extends DummyTestBase {
    /*
   http://dummy.restapiexample.com/api/v1/employees
   1) Status kodunun 200,
   2) 10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
   3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
   4) Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın
      ve bunların içerisinde “Charde Marshall” olduğunu test edin
   */

    @Test
    public void test19() {

        spec03.pathParams("p1", "employees");

        Response response = given().contentType(ContentType.JSON).spec(spec03).when().get("/{p1}");
        response.prettyPrint();

        // 1) Status kodunun 200,
        assertEquals(200,response.getStatusCode());
        //   2) 10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        JsonPath json = response.jsonPath();
        System.out.println(json.getList("data.findAll{it.id>10}.id"));
        assertTrue(json.getList("data.findAll{it.id>10}.id").size()==14);
        //   3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
        System.out.println(json.getList("data.findAll{it.employee_age<30}.employee_age"));
        List<Integer> yasList = json.getList("data.findAll{it.employee_age<30}.employee_age");
        Collections.sort(yasList);
        assertTrue(yasList.get(yasList.size()-1)==23);

        //   4) Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın
        System.out.println(json.getList("data.findAll{it.employee_salary<350000}.employee_name"));
        //      ve bunların içerisinde “Charde Marshall” olduğunu test edin
        assertTrue(json.getList("data.findAll{it.employee_salary<350000}.employee_name").contains("Ashton Cox"));
    }
}