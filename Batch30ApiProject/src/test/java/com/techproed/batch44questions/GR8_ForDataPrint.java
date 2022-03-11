package com.techproed.batch44questions;

import com.techproed.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GR8_ForDataPrint extends DummyTestBase {
     /*
   http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
  1) Butun calisanlarin isimlerini consola yazdıralim
  2) 3. calisan kisinin ismini konsola yazdıralim
  3) Ilk 5 calisanin adini konsola yazdiralim
  4) En son calisanin adini konsola yazdiralim
*/

    @Test
    public void test(){
        spec03.pathParam("p1","employees");

        Response response = given().contentType(ContentType.JSON).spec(spec03).when().get("/{p1}");

        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        //1) Butun calisanlarin isimlerini consola yazdıralim
        System.out.println(jsonPath.getString("data.employee_name"));

        //  2) 3. calisan kisinin ismini konsola yazdıralim
        System.out.println(jsonPath.getString("data.employee_name[2]"));
        //System.out.println(jsonPath.getString("data[2].employee_name"));

        //  3) Ilk 5 calisanin adini konsola yazdiralim

        //1.Yol
        for (int i = 0; i <= 4; i++) {
            System.out.println(i+1 + ".calisan : "+ jsonPath.getString("data["+i+"].employee_name"));
        }

        //2. Yol
        System.out.println(jsonPath.getString("data.employee_name[0,1,2,3,4]"));

        //3. Yol
        System.out.println(jsonPath.getList("data[0,1,2,3,4].employee_name"));

        //  4) En son calisanin adini konsola yazdiralim
        System.out.println(jsonPath.getString("data.employee_name[-1]"));
        System.out.println(jsonPath.getString("data[-1].employee_name"));

     }
}
