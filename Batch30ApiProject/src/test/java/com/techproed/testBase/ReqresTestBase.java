package com.techproed.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class ReqresTestBase {

    protected RequestSpecification spec04;

    @Before
    public void setUp(){

        spec04=new RequestSpecBuilder().
                setBaseUri("https://reqres.in").
                build();

    }
}
