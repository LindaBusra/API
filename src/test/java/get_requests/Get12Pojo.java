package get_requests;


import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/6838

        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
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
     */
    @Test
    public void get12(){
        //1.Step= set the url

        spec.pathParams("first","booking","second",6838);

        //2.Step : set the excepted data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2013-02-23","2014-10-23");

        BookingPojo expectedData =new BookingPojo("Sally","Brown",111,true, bookingDatesPojo,"Breakfast");
        System.out.println("expectedData: " + expectedData);


        //3.Step Send the get request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");


        //4.Step :Do Assertion

        BookingPojo actualData=response.as(BookingPojo.class);  //De-Serialization,  make conversion, for they both can be same data type

        assertEquals(200,response.getStatusCode());

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());

        //or (Recommended)
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
    }



}
