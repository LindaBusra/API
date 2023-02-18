package api_review.get_http_request_methods;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get02 extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/1001
    When
        User send a GET Request to the url
    Then
        HTTP Status code should be 200
    And
        Status Line should be "HTTP/1.1 200 OK"
    And
        Response body contains "firstname"
    And
       Response body does not contain "TechProEd"
    And
        Server is "Cowboy"
     */

    //common part puts in parent class (Inheritance), like base url
    //pathPrams(path for parameters) : Path parameters are used to make resources smaller. (Amazon:base url--books, foods, shoes...)



    @Test
    public void get02(){

        //1-Set the url (endpoint)
//        String url = "https://restful-booker.herokuapp.com/booking/1001";   //not recommended
        spec.pathParams("first", "booking","second", 1001);
        //with using path parameters to make resource smaller

        //2-Set the expected data



        //3-Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();  //Not Found


        //4-Do Assertion        assertTrue(true) -->Green tick    assertTrue(false) -->Red cross
        response.then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK");

        //Normally response is in json data. We will convert json data to string.
        assertTrue(response.asString().contains("firstname")); //If response.asString().contains("Not Found") returns true, you will get green tick

        //assertFalse(false) -->Green tick      assertFalse(true) -->Red cross
        assertFalse(response.asString().contains("TechProEd"));  //If response.asString().contains("TechProEd") returns false, you will get green tick

        //Expected data comes from test case, Actual data comes from API
        //assertEquals() returns true(test passes) if the arguments (real data) match.
        assertEquals("Cowboy", response.getHeader("Server"));



    }
}
