package com.techproed.batch44questions.GRs;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GR23 extends DummyTestBase {
    /*
   http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
   Status kodun 200 olduğunu,
   14. Çalışan isminin "Haley Kennedy" olduğunu,
   Çalışan sayısının 24 olduğunu,
   Sondan 3. çalışanın maaşının 675000 olduğunu
   40,21 ve 19 yaslarında çalışanlar olup olmadığını
   10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
   {
           "id": 10,
           "employee_name": "Sonya Frost",
           "employee_salary": 103600,
           "employee_age": 23,
           "profile_image": ""
    }
     olduğunu test edin.
   */
    @Test
    public void test23() {

        //1) URL OLUSTUR
        spec03.pathParams("bir","employees");

        DummyTestData testObje = new DummyTestData();
        HashMap<String, Object> expectedTestDataMap = testObje.setUpTestData();
        System.out.println("EXPECTED TEST DATA: " + expectedTestDataMap);

        //3) REQUEST VE RESPONSE OLUSTUR
        Response response = given().spec(spec03).contentType(ContentType.JSON).when().get("/{bir}");
        //response.prettyPrint();

        //4) DOGRULAMA
        //De-Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedTestDataMap.get("statusCode"),response.getStatusCode());

        //11. Çalışan isminin "Jena Gaines" olduğunu,
        assertEquals(((Map)(expectedTestDataMap.get("onuncucalisan"))).get("employee_name"),
                ((Map) ((List) actualDataMap.get("data")).get(9)).get("employee_name"));
        //   Çalışan sayısının 24 olduğunu,
        assertEquals(expectedTestDataMap.get("calisansayisi"),((List<?>) actualDataMap.get("data")).size());

        //   Sondan 3. çalışanın maaşının 675000 olduğunu
        //   40,21 ve 19 yaslarında çalışanlar olup olmadığını
        //   10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
        //   {
        //           "id": 10,
        //           "employee_name": "Sonya Frost",
        //           "employee_salary": 103600,
        //           "employee_age": 23,
        //           "profile_image": ""

        assertEquals(expectedTestDataMap.get("onbirincicalisan"),
                ((Map)((List)actualDataMap.get("data")).get(10)).get("employee_name"));

    }
}
