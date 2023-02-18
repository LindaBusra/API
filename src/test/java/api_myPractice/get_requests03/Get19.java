package api_myPractice.get_requests03;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get19 extends HerOkuAppBaseUrl {

        /*
    Given I send Get request to https://restful-booker.herokuapp.com/booking/2
    then Response body should be like that;
    {
     "firstname": "Ali",
    "lastname": "Can",
    "totalprice": 500,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-03-01",
        "checkout": "2022-03-11"
        }
    }
     */

    @Test
    public void get09() {

        //Set the url
        spec.pathParams("f", "booking", "s", 2);


        //Set the expected data
        Map<String, Object> bookingData = new HashMap<>();
        bookingData.put("checkin", "2022-03-01");
        bookingData.put("checkout", "2022-03-11");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Ali");
        expectedData.put("lastname", "Can");
        expectedData.put("totalprice", 500);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingData);


        //Send the get request and get the response
        Response response = given().spec(spec).when().get("/{f}/{s}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals("does not match", expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals("does not match", expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals("does not match", expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals("does not match", expectedData.get("depositpaid"), actualData.get("depositpaid"));

        assertEquals("does not match", bookingData, actualData.get("bookingdates"));
        assertEquals("does not match", bookingData.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals("does not match", bookingData.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));


    }


}
