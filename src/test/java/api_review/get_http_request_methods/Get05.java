package api_review.get_http_request_methods;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User send a request to the URL
    Then
        Status code is 200
    And
        Among the data there should be someone whose first name is "John" and last name is "Smith"
     */

    //If you want to get specific data from a resource you need to use QueryParam. Syntax: ?firstname=John&lastname=Smith
    //https://restful-booker.herokuapp.com/booking?firstname=John&lastname=Smith
    //pathParam is making the resource smaller.  There is no key-value structure in pathParam.
    //QueryParam is for selecting specific data. We use ? and key-value structure, for multiply query use &


    @Test
    public void get05(){

        //1-Set the url
        spec.pathParam("first", "booking")
                .queryParams("firstname", "John", "lastname", "Smith");


        //2-Set the expected data


        //3-Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        //4-Do the assertion
        response.then().assertThat().statusCode(200);
        assertTrue("the data look for does not exist", response.asString().contains("bookingid"));  //If I can se booking id, it means John Smith exists.

        /*if data does not exist I see just [ ]
        but if exist :
        [
        {
            "bookingid": 1
        }
        ]


        */


    }

}
