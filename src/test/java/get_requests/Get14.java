package get_requests;

import base_urls.RegresInBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14 extends RegresInBaseUrl {
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
        //or
        assertEquals(404, response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());


        assertEquals("cloudflare",response.getHeader("Server"));

        //body should be empty
        assertEquals(0, response.asString().replaceAll("[^a-zA-Z0-9]", "").length());

        //or body should be empty
        assertEquals(0, response.as(HashMap.class).size());





    }
}
