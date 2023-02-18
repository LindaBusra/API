package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import java.util.HashMap;

public class HerOkuAppBaseUrl {

    protected RequestSpecification spec;
    //Create an object whose data type is RequestSpecification. RequestSpecifications in an Interface. It can be used as data type.
    //But we can not create an object from this class.


    @Before//If you use @Before annotation at the top of a method, it means this method will be executed before every test method.
    //@Before running -->first:public void setUp()  -->second:public void get05(){}

    public void setUp(){

        //System.out.println("This is coming from setUp method");

        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
        //spec object has base url now. I will use just spec for access base url.

    }



}