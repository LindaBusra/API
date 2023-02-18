package api_review.get_http_request_methods;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/1
    When
        User send a request to the URL
    Then
        Response body should be like that

                {                       //outher json-->Map2
            "firstname": "Mary",
            "lastname": "Brown",
            "totalprice": 423,
            "depositpaid": false,
            "bookingdates": {           //inner json -->Map1
                "checkin": "2017-10-28",
                "checkout": "2019-04-22"
            },
            "additionalneeds": "Breakfast"
        }                                            */




    @Test
    public void get09(){


        //1.Step:Set the url
        spec.pathParams("first", "booking", "second", 1);

        //2.Step: Set the expected data
        Map<String, String> expectedBookingdates = new HashMap<>();
        expectedBookingdates.put("checkin", "2017-10-28");
        expectedBookingdates.put("checkout", "2019-04-22");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Mary");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 423);
        expectedData.put("depositpaid", false);
        expectedData.put("expectedBookingdates", expectedBookingdates);
        expectedData.put("additionalneeds", "Breakfast");
        System.out.println(expectedData);


        //3.Step:Send request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //4.Step:Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        assertEquals(expectedBookingdates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingdates.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));


        //{checkin=2017-10-28, checkout=2019-04-22 }   it has object data type -->I have to convert it Map. Because get() method comes from Map.
        //By using Explicit TypeCasting I convert actualData.get("bookingdates")  to Map. -->((Map)actualData.get("bookingdates"))




    }




}
