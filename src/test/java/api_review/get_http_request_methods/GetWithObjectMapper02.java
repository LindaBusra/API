package api_review.get_http_request_methods;

import Utils.JsonUtils;
import base_urls.HerOkuAppBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetWithObjectMapper02 extends HerOkuAppBaseUrl {


    /*
        Given
	            https://restful-booker.herokuapp.com/booking/2
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                {
                    "firstname": "Jim",
                    "lastname": "Smith",
                    "totalprice": 649,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2015-09-16",
                        "checkout": "2018-04-09"
                    },
                    "additionalneeds": "Breakfast"
                }
     */

    @Test
    public void getWithObjectMapper01() {

        //1.Step: Set the URL
        spec.pathParams("first", "booking", "second", 2);

        //2.Step: Set the expected data

        //1.way:
        String expectedData = "{\n" +
                "\"firstname\": \"Sally\",\n" +
                "\"lastname\": \"Smith\",\n" +
                "\"totalprice\": 473,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2021-12-07\",\n" +
                "\"checkout\": \"2022-07-12\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";
        HashMap<String, Object> expectedDataMap = JsonUtils.convertJsonToJavaObject(expectedData, HashMap.class);
        System.out.println(expectedDataMap);

        //2.way: Create a setUp method to convert Json data to String dynamically



        //3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String, Object> actualDataMap = JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);

        //4.Step: Do assertion

        assertEquals(200, response.statusCode());
        assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        assertEquals( ( (Map) expectedDataMap.get("bookingdates")).get("checkin"), ( (Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals( ( (Map) expectedDataMap.get("bookingdates")).get("checkout"), ( (Map)actualDataMap.get("bookingdates")).get("checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));

        //expectedDataMap.get("bookingdates") is object. Because in the Map values are object. You need to convert object to Map
        //and then you can use get() method one more time.


        //https://lms.techproeducation.com/mod/book/view.php?id=3545&chapterid=9875   1:36 dayim

    }
}