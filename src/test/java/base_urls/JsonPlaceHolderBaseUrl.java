package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification spec;   //spec has no any value now.   //I am going to use spec, i stedet of typing url


    @Before//If you see @Before annotation at the top of a method, it will be executed before every test method.
            //Before org unit.   It is going to assigned value (following value) to spec.
            //The following method is going to working before--> public void get04(){}
            //@Before runnig -->first:public void setUp()  -->second:public void get04(){}

    public void setUp(){

        //System.out.println("This is coming from setUp method");
        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();




 }


}
