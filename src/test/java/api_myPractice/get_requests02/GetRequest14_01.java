package api_myPractice.get_requests02;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

import base_urls.HerOkuAppBaseUrl;

public class GetRequest14_01 extends HerOkuAppBaseUrl {

        /*
https://restful-booker.herokuapp.com/booking/47
{
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
1) JsonPhat
2) De-Serialization
*/


   @Test
   public void test22(){

    //set url
   spec.pathParams("first", "booking", "second", 47);

   //set expected data
       Map<String, String> bookingDates = new HashMap<>();
       bookingDates.put( "checkin","2013-02-23");
       bookingDates.put( "checkout","2014-10-23");

       Map<String, Object> expectedData = new HashMap<>();
       expectedData.put("firstname", "Sally");
       expectedData.put( "lastname", "Brown");
       expectedData.put( "totalprice", 111);
       expectedData.put( "depositpaid", true);
       expectedData.put( "additionalneeds", "Breakfast");
       expectedData.put( "bookingdates", bookingDates);



  //send request and get response
  Response response = given().spec(spec).when().get("/{first}/{second}")  ;
  response.prettyPrint();


  //do assertion

    //1. way
    response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
    body("firstname", equalTo("Sally"),"lastname", equalTo("Brown"), "totalprice", equalTo(111),
            "depositpaid", equalTo(true), "bookingdates.checkin", equalTo("2013-02-23"),
            "bookingdates.checkout", equalTo("2014-10-23"), "additionalneeds", equalTo("Breakfast"));


       Map<String, Object> actualData = response.as(HashMap.class);

       assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
       assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
       assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
       assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
       assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

       assertEquals( bookingDates.get("checkin"),     ( (Map) (actualData.get("bookingdates"))).get("checkin") );
       assertEquals( bookingDates.get("checkout"),     ( (Map) (actualData.get("bookingdates"))).get("checkout") );







   }



}
