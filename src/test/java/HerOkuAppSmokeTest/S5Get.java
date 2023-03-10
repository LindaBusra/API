package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static HerOkuAppSmokeTest.S1Post.bookingid;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class S5Get extends HerOkuAppBaseUrl {

    //We are going to try get the data we deleted.

        /*
    Given
            https://restful-booker.herokuapp.com/booking/:id

   When
        User send Get request

   Then
          Status code is 404
    And
          Response body is like "Not Found"


     */

    @Test
    public void get02(){

        //set the url
        spec.pathParams("first","booking","second",bookingid);

        //set the expected data
        String expectedData = "Not Found";

        //send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion

        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());

    }
}
