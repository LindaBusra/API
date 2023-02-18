package api_myPractice.get_requests03;


import base_urls.HerOkuAppBaseUrl;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get05 extends HerOkuAppBaseUrl {

       /*
    https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
    HTTP Status Code’unun 200
    ve response content type’inin “application/JSON” oldugunu
    ve response body’sinin asagidaki gibi oldugunu test edin
{
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 100,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-11-11",
        "checkout": "2022-12-12"
    },
    "additionalneeds": "Dinner"
}
    */

@Test
    public void test01(){

    //set url
    spec.pathParams("first", "booking", "second", 5);

    //set expected data


    //send request and get response
    Response response = given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();


    //do assertion
    response.then().assertThat().statusCode(200).contentType("application/json");

    JsonPath json = response.jsonPath();
    assertEquals("Sally", json.getString("firstname"));
    assertEquals("Brown", json.getString("lastname"));
    assertEquals(100, json.getInt("totalprice"));
    assertEquals(true, json.getBoolean("depositpaid"));
    assertEquals("Dinner", json.getString("additionalneeds"));
    assertEquals("2021-11-11", json.getString("bookingdates.checkin"));
    assertEquals("2022-12-12", json.getString("bookingdates.checkout"));


}


    @Test
    public void test02(){

        //set url
        spec.pathParams("first", "booking", "second", 5);

        //set expected data
        HashMap<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2021-11-11");
        bookingdates.put("checkout", "2022-12-12");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Sally");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 100);
        expectedData.put("depositpaid", true);
        expectedData.put("additionalneeds", "Dinner");
        expectedData.put("bookingdates",bookingdates );



        //send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //do assertion
        response.then().assertThat().statusCode(200).contentType("application/json");

        HashMap<String, Object> actualData = response.as(HashMap.class);

       assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
       assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
       assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
       assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
       assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
       assertEquals(bookingdates.get("checkin"),  ((Map) actualData.get("bookingdates")).get("checkin"));


    }



}
