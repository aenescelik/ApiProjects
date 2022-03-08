package com.techproed.testData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
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

    public HashMap<String, Object> setUpTestData(){

        List<Integer> ages = new ArrayList<Integer>();
        ages.add(40);
        ages.add(21);
        ages.add(19);

        HashMap<String,Object> onBirinci=new HashMap<>();
        onBirinci.put("id",11);
        onBirinci.put("employee_name","Jena Gaines");
        onBirinci.put("employee_salary",90560);
        onBirinci.put("employee_age",30);
        onBirinci.put("profile_image","");

        HashMap<String,Object> expectedData=new HashMap<String, Object>();
        expectedData.put("statusCode",200);
        expectedData.put("besincicalisan","Airi Satou");
        expectedData.put("calisansayisi",24);
        expectedData.put("sondanikincicalisanmaasi",106450);
        expectedData.put("arananyaslar",ages);
        expectedData.put("onbirincicalisan",onBirinci);

        return expectedData;
    }
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */
    public HashMap<String, Integer> setUpTestData02(){
        HashMap<String,Integer> expectedData=new HashMap<String, Integer>();
        expectedData.put("statusCode",200);
        expectedData.put("enYuksekMaas",725000);
        expectedData.put("enKucukYas",19);
        expectedData.put("ikinciYuksekMaas",675000);
        return expectedData;
    }

    public HashMap<String, String> setUpRequestBody(){
        HashMap<String,String> requestBody=new HashMap<String, String>();
        requestBody.put("name", "batch30");
        requestBody.put("salary","123000");
        requestBody.put("age","20");
        return requestBody;
    }
    public HashMap<String, Object> setUpExpectedData(){
        HashMap<String,Object> innerData=new HashMap<String, Object>();
        innerData.put("name","batch30");
        innerData.put("salary","123000");
        innerData.put("age","20");

        HashMap<String,Object> expectedData=new HashMap<String, Object>();
        expectedData.put("statusCode",200);
        expectedData.put("status","success");
        expectedData.put("data",innerData);
        expectedData.put("message","Successfully! Record has been added.");

    return expectedData;
    }
    public JSONObject setUpDeleteExpectedData(){
        /*
        {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
         */

        JSONObject expectedData=new JSONObject();

        expectedData.put("status", "success");
        expectedData.put("data", "2");
        expectedData.put("message", "Successfully! Record has been deleted");
        return expectedData;


    }
}
