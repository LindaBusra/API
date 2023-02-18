package api_review.get_http_request_methods;

import api_review.pojos.BookingDatesPojo;
import api_review.pojos.BookingPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetWithPojo01 extends HerOkuAppBaseUrl {

        /*
        Given
            https://restful-booker.herokuapp.com/booking/2
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                    "firstname": "Jim",
                                    "lastname": "Smith",
                                    "totalprice": 831,
                                    "depositpaid": false,
                                    "bookingdates": {                   //inner json
                                        "checkin": "2020-08-26",
                                        "checkout": "2021-05-16"
                                    },
                                    "additionalneeds": "Breakfast"
                                 }
     */

    @Test
    public void getWithPojo01(){

        //1.Step: Set the URL
        spec.pathParams("first", "booking", "second", 2);

        //2.Step: Set the expected data
        //We are converting json to java classes by using pojo
        BookingDatesPojo bookingDates = new BookingDatesPojo("2020-08-26", "2021-05-16");
        BookingPojo expectedData = new BookingPojo("Jim", "Smith", 831, false, bookingDates, "Breakfast");


        //3.Step: Send request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do assertion
        //I want to convert response body to pojo class.
        BookingPojo actualData= response.as(BookingPojo.class);


        assertEquals(200, response.statusCode());
        assertEquals("First names are not maching" + expectedData.getFirstname(), actualData.getFirstname());
        assertEquals("Last names are not maching" + expectedData.getLastname(), actualData.getLastname());
        assertEquals("Total prices are not maching" + expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals("Deposit paid is not maching" + expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals("Additional needs are not maching" + expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
        assertEquals("Check in dates are not maching" + expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals("Check out dates are not maching" + expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());

    }
}
