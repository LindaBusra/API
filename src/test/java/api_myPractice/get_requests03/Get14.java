package api_myPractice.get_requests03;


import base_urls.HerOkuAppBaseUrl;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get14 extends HerOkuAppBaseUrl {


    /*
    Given https://restful--booker.herokuapp.com/booking/10000
    When user sends a GET request to the url
     Then HTTP status code should be 404
     And response body contains "Not Found"
     And Status Line should be HTTP/1.1 404 Not Found
     And body does not contain " twitter"
     and Server is "Cowboy
     */

    @Test
    public void get() {

        //Set the url
        spec.pathParams("first", "booking", "second", 10000);

        //Set the expected data

        // Send the get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");


        //do assertion
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        System.out.println(response.asString());

        assertEquals("Not Found", response.asString());
        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("twitter"));
        assertEquals("Cowboy", response.getHeader("Server"));





    }

}
