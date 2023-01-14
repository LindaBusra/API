package Questions;


import base_urls.HerOkuAppBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;


public class Question5 extends HerOkuAppBaseUrl {


    /*
    Given
        "https://restful-booker.herokuapp.com/booking/5 "
    When
      I send a GET request to the Url
   And
       Accept type is "application/json"
   Then
       HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       first name should be “Der”
   And
       And total price should be 11111
   And
       And checkin date should be "2020-11-02"

 */

    /*
    {
    "firstname": "Nick",
    "lastname": "Smith",
    "totalprice": 100,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-11-11",
        "checkout": "2022-12-12"
    },
    "additionalneeds": "Breakfast"
}
     */


    @Test
    public void get() {

        //Set the url

        spec.pathParams("first","booking","second",5);

        //Set the expected data
        Map<String, Object> bookingData = new HashMap<String, Object>();
        bookingData.put("checkin", "2021-11-11");
        bookingData.put("checkout", "2022-12-12");


        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Nick");
        expectedData.put("totalprice", 100);
        expectedData.put("bookingdates", bookingData);

        //Send the get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //1. Validation
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("Nick"),
                        "totalprice", equalTo(100),
                        "bookingdates.checkin", equalTo("2021-11-11"));


        //2.Validation
        JsonPath json = response.jsonPath();

        assertEquals(json.getString("firstname"), "Nick");
        assertEquals(json.getInt("totalprice"), 100);
        assertEquals(json.getString("bookingdates.checkin"), "2021-11-11");

        //3.way
        System.out.println(expectedData);
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(bookingData.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingData, actualData.get("bookingdates"));


        assertEquals(response.contentType(), "application/json; charset=utf-8");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.jsonPath().getString("firstname"), "Nick");
        assertEquals(response.jsonPath().getString("totalprice"), "100");
        assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2021-11-11");

    }

}