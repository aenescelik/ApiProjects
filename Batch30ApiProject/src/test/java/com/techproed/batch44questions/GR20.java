package com.techproed.batch44questions;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import org.junit.Test;

public class GR20 extends JsonPlaceHolderTestBase {
     /*
   https://jsonplaceholder.typicode.com/todos/2
   1) Status kodunun 200,
   2) respose body'de,
            "completed": değerinin false
            "title”: değerinin “quis ut nam facilis et officia qui”
            "userId" sinin 1 ve
       header değerlerinden
            "via" değerinin “1.1 vegur” ve
            "Server" değerinin “cloudflare” olduğunu test edin…
   */

    @Test
    public void test20() {

        //1) URL OLUSTUR
        spec01.pathParams("parametre1", "todos", "parametre2", 2);


    }
}
