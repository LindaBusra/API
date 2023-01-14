package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/32
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
                "firstname": "Mark",
                "lastname": "Jackson",
                "totalprice": 554,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2017-09-11",
                    "checkout": "2021-03-14"
                },
                "additionalneeds": "Breakfast"
                }
     */

    @Test
    public void get06() {
        //Set the url
        spec.pathParams("first", "booking", "second", 32);

        //Set the expected data


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        //1st Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("James"),
                        "lastname",equalTo("Brown"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));




        //2nd Way: We will use JsonPath Class
        JsonPath jsonPath = response.jsonPath();   //convert response to JsonPath

        //Hard Assertion
        assertEquals("James",jsonPath.getString("firstname"));  //if they are equals, it is passed
        assertEquals("Brown",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
        assertEquals(true,jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin")); //nested json
        assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));

        //if the first one feiled, the others can not executed.



        //Soft Assertion
        //to do Soft Assertion follow this 3 steps:

        //1.st: Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();


        //2nd. Do assertion
        softAssert.assertEquals(jsonPath.getString("firstname"), "James", "First name did not match");  //3rd parameter is for failure message
        softAssert.assertEquals(jsonPath.getString("lastname"), "Brown", "Lastname name did not match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "Totalprice name did not match");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"), true, "Depositpaid name did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "Checkin  did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "Checkout  did not match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast", "Additionalneeds  did not match");

        //3rd: Use AssertAll() method.
        softAssert.assertAll();


    }
}