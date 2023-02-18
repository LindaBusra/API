package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GorestApiBaseUrl {

    protected RequestSpecification spec;  //RequestSpecification is an interface, it is a datatype.


    @Before
    public void setUp(){


        spec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();



 }









}