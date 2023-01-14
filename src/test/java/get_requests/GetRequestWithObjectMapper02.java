package get_requests;

import Utils.JsonUtils;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetRequestWithObjectMapper02  extends HerOkuAppBaseUrl {

        /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
{
    "firstname": "Josh",
    "lastname": "Allen",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    }
}
     */

    @Test
    public void test(){

      //set url
      spec.pathParams("first", "booking", "second", 22);

      //set expected data

        String json = "{\n" +
                "    \"firstname\": \"Josh\",\n" +
                "    \"lastname\": \"Allen\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    }\n" +
                "}";

        Map<String, Object> expectedData = JsonUtils.convertJsonToJavaObject(json, Map.class);
        System.out.println("expectedData: " + expectedData);


        //sent request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String, Object> actualData = JsonUtils.convertJsonToJavaObject(response.asString(), Map.class);
        System.out.println("actualData: " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals( ((Map)expectedData.get("bookingdates")).get("checkin"),  ((Map)actualData.get("bookingdates")).get("checkin") );
        assertEquals( ((Map)expectedData.get("bookingdates")).get("checkout"),  ((Map)actualData.get("bookingdates")).get("checkout") );

    }
}
