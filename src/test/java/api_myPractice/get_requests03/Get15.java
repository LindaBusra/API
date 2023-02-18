package api_myPractice.get_requests03;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get15 extends HerOkuAppBaseUrl {

    /*
    Given https://restful-booker.herokuapp.com/booking
    When User send a request to the Url
    Then Status code is 200
    And Among the data there should be someone whose firstname is "Jim" and last name is "Jones"
     */


    @Test
    public void get05() {

        //Set the Url
        spec.pathParam("first", "booking").queryParams("firstname", "Jim", "lastname", "Jones");

        //Send the request and Get the response
        Response response = given().spec(spec).when().get("/{first}");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);


    }


}