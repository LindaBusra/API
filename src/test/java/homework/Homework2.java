package homework;

import base_urls.RegresInBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;


public class Homework2 extends RegresInBaseUrl {

 /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */


    @Test
    public void homework2(){


        //Set the url
        spec.pathParams("first","users","second",23);


        //Set the expected data


        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        response.then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");

        assertEquals("cloudflare",response.getHeader("Server"));
        assertEquals(0, response.as(HashMap.class).size());

        //or
        assertEquals(0, response.asString().replaceAll("[^A-Za-z0-9]", "").length());






    }
}
