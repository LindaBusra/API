package get_requests;


import Utils.JsonUtils;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12ObjectMapper extends HerOkuAppBaseUrl {


    /*
        Given
            https://restful-booker.herokuapp.com/booking/57070

        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {

{
    "firstname": "John",
    "lastname": "Smith",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */
    @Test
    public void get12(){

        //Object Mapper: It is the process of converting json file to java objects. It makes de-serialization

        //1.Step= set the url

        spec.pathParams("first","booking","second",57070);

        //2.Step : set the excepted data

        String expectedDataInString = new HerOkuAppTestData().expectedDataInString("John","Smith",111,true,"2018-01-01","2019-01-01", "Breakfast");
        HashMap<String, Object> expectedData = JsonUtils.convertJsonToJavaObject(expectedDataInString, HashMap.class);
        System.out.println("expectedData: " + expectedData);



        //3.Step Send the get request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response.prettyPrint();


        //4.Step :Do Assertion
        HashMap<String, Object> actualData = JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println("actualData: " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("checkin"), actualData.get("checkin"));
        assertEquals(expectedData.get("checkout"), actualData.get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));




    }



}
