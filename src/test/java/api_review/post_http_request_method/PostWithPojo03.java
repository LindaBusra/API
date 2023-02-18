package api_review.post_http_request_method;

import api_review.pojos.BookingDatesPojo;
import api_review.pojos.BookingPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWithPojo03 extends HerOkuAppBaseUrl {

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
 		    I send GET   Request to the URL to get the newly created data
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
    public void postWithPojo03(){

        //1.Step-Set the Url
        spec.pathParam("first", "booking");


        //2.Step-Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2023-05-10","2023-05-20");
        BookingPojo requestBody = new BookingPojo("Linda", "Berg", 1000, true, bookingDates,"Breakfast with tea" );
        System.out.println(requestBody);

        //3.Step-Send request and get the response1
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response1.prettyPrint();

        /*
        After creating new data in Databse, you will need "bookingid" to be able to use GEt method.
        So you need to get "bookingid" from POST Response Body

        I send POST request-->API-->everything is okey-->sending data created in database
        When we get Post response from Database it is changing data format. and we have another data: bookingid
        if request structure is different from response structure you have two options:
        1-Create new pojo for response --> Look postWithPojo03()
        2-Use GET :If you are sure GET response data structure is same with POST request data structure. For make sure you can use Postman           */

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //4.Step-Do assertion
        JsonPath json = response1.jsonPath();
        Integer bookingId = json.getInt("bookingid");  //24
        System.out.println(bookingId);          //I will use that one with sending request

        //I am creating a new data in database by using GET method I will read it. I am creating data and reading it.

        //Set the url for GET Method
        spec.pathParams("first", "booking", "second", bookingId);

        //Send the GET Request and get the response
        Response response2 = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response2.prettyPrint();

        //Do the assertion
        BookingPojo actualData = response2.as(BookingPojo.class);
        assertEquals(200, response2.statusCode());
        assertEquals(requestBody.getFirstname(), actualData.getFirstname());
        assertEquals(requestBody.getLastname(), actualData.getLastname());
        assertEquals(requestBody.getTotalprice(), actualData.getTotalprice());
        assertEquals(requestBody.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), actualData.getAdditionalneeds());

        //The data which you send and the data which you get must be same.

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
