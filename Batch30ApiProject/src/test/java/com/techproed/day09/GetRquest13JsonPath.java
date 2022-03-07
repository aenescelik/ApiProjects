package com.techproed.day09;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRquest13JsonPath extends DummyTestBase {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
    {
 “id”:”11”
        "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
} gibi olduğunu test edin.
*/

    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setUpTestData();
        System.out.println(expectedDataMap);

        Response response = given().accept("application/json")
                .spec(spec03).when().get("/{parametre1}");

        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        //    Status kodun 200 olduğunu,
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        //      5. Çalışan isminin "Airi Satou" olduğunu ,
        Assert.assertEquals(expectedDataMap.get("besincicalisan"),jsonPath.getString("data[4].employee_name"));
        //      çalışan sayısının 24 olduğunu,
        Assert.assertEquals(expectedDataMap.get("calisansayisi"),jsonPath.getList("data.id").size());
        //    Sondan 2. çalışanın maaşının 106450 olduğunu
        Assert.assertEquals(expectedDataMap.get("sondanikincicalisanmaasi"),jsonPath.getInt("data[-2].employee_salary"));
        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        Assert.assertTrue(jsonPath.getList("data.employee_age").
                containsAll((List) expectedDataMap.get("arananyaslar")));
        //11. Çalışan bilgilerinin
        //    {
        // “id”:”11”
        Assert.assertEquals(((Map)expectedDataMap.get("onbirincicalisan")).get("id"),
                jsonPath.getInt("data[10].id"));
        //        "employee_name": "Jena Gaines",
        Assert.assertEquals(((Map)expectedDataMap.get("onbirincicalisan")).get("employee_name"),
                jsonPath.getString("data[10].employee_name"));
        //            "employee_salary": "90560",
        Assert.assertEquals(((Map)expectedDataMap.get("onbirincicalisan")).get("employee_salary"),
                jsonPath.getInt("data[10].employee_salary"));
        //            "employee_age": "30",
        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_age"),
                jsonPath.getInt("data[10].employee_age"));
        //            "profile_image": "" }
        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("profile_image"),
                jsonPath.getString("data[10].profile_image"));

    }
}
