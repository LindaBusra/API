package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import java.util.HashMap;

public class HerOkuAppBaseUrl {

    protected RequestSpecification spec;

    @Before//If you see @Before annotation at the top of a method, it will be executed before every test method.
    //@Before runnig -->first:public void setUp()  -->second:public void get05(){}


    public void setUp(){

        //System.out.println("This is coming from setUp method");

        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

    }



}