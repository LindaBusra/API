package api_review.post_http_request_method;

import api_review.pojos.BookingDatesPojo;
import api_review.pojos.BookingPojo;
import api_review.pojos.BookingPostResponseBodyPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWithPojo02 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/

                                {
                                    "firstname": "Linda",
                                    "lastname": "Berg",
                                    "totalprice": 1000,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2023-05-10",
                                        "checkout": "2023-05-20"
                                    },
                                    "additionalneeds": "Breakfast with tea"
                                 }
        When
 		    I send POST  Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like    {
                                    "firstname": "Linda",
                                    "lastname": "Berg",
                                    "totalprice": 1000,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2023-05-10",
                                        "checkout": "2023-05-20"
                                    },
                                    "additionalneeds": "Breakfast with tea"
                                 }                                                     */

    @Test
    public void postWithPojo02(){     //First way by creating a new pojo

        //1.Step-Set the Url
        spec.pathParam("first", "booking");


        //2.Step-Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2023-05-10","2023-05-20");
        BookingPojo requestBody = new BookingPojo("Linda", "Berg", 1000, true, bookingDates,"Breakfast with tea" );
        System.out.println(requestBody);

        //3.Step-Send request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();


        //I will create pojo according to response body.  When I send this request it brings bookingid. So request body and response have different number of keys.
        //Convert response body to Pojo by using Gson. Gson convert response by using as() to BookingPostResponseBodyPojo.class
        BookingPostResponseBodyPojo actualData= response.as(BookingPostResponseBodyPojo.class);


        //4.Step-Do assertion
        assertEquals(200, response.getStatusCode());
        assertEquals(requestBody.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(requestBody.getLastname(), actualData.getBooking().getLastname());
        assertEquals(requestBody.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(requestBody.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(requestBody.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
        assertEquals(requestBody.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());


    }




    //For testing  after you create data, you should delete it. Sometimes we run a test 10 times. It means you create a lot of data.


    //For 1. way
    /*  BookingPostResponseBodyPojo--> When we send request the answer is like that. So we need another pojo with bookingid and booking:
{
    "bookingid": 9514,
    "booking": {
        "firstname": "Linda",
        "lastname": "Berg",
        "totalprice": 1000,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2023-05-10",
            "checkout": "2023-05-20"
        },
        "additionalneeds": "Breakfast with tea"
    }
}  */



}
