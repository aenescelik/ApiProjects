package com.techproed.testBase;

import com.techproed.utilities.Authentication;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GMIBankTestBase extends Authentication {

    protected RequestSpecification spec05;

    @Before
    public void setUp(){
        spec05=new RequestSpecBuilder()
                .setBaseUri("https://www.gmibank.com/api").build();
    }
}
