package api_review.post_http_request_method;

import Utils.JsonUtils;
import api_review.pojos.BookingDatesPojo;
import api_review.pojos.BookingPojo;
import api_review.pojos.BookingPostResponseBodyPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostGetWithObjectMapperAndPojo01 extends HerOkuAppBaseUrl {

    /*  POST Request: Creating a new data in database

    Given
        https://restful-booker.herokuapp.com/booking

          {
                "firstname": "Selim",
                "lastname": "Ak",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-09",
                    "checkout": "2021-09-21"
                },
                "additionalneeds": "Breakfast"
            }
    When
        I send POST Request to the Url
    And
        I send GET Request to the Url
    Then
        Status code is 200
    And
        Response body should be like {

            "firstname": "Selim",
            "lastname": "Ak",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                    "checkin": "2021-09-09",
                    "checkout": "2021-09-21"
            },
            "additionalneeds": "Breakfast"
        }

     */

    @Test
    public void postGetWithObjectMapperAndPojo01(){

        //TO SEND POST REQUEST WE DID 1.Step, 2.Step, 3.Step
        //1.Step: Set the URL
        spec.pathParams("first", "booking");

        //2.Step: Set the request body
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-09","2021-09-21" );
        BookingPojo requestBody = new BookingPojo("Selim", "AK", 11111, true, bookingDates, "Breakfast");

        System.out.println(requestBody);

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();   //response is in json format


        //CONVERT POST RESPONSE BODY TO JAVA OBJECT BY USING OBJECT MAPPER
        //I will convert response to Pojo. I will use Object Mapper. for to do it , I use Jutils.
        BookingPostResponseBodyPojo postResponseBodyInPojo = JsonUtils.convertJsonToJavaObject(response.asString(), BookingPostResponseBodyPojo.class);
        System.out.println(postResponseBodyInPojo);

        //FROM postResponseBodyInPojo WE GOT THE VALUE OF bookingId BY USING GETTER of BOOKINGiD
        Integer bookingId = postResponseBodyInPojo.getBookingid();


        //BY USING bookingId I WILL SEND GET REQUEST IN 1.Step, 2.Step, 3.Step
        //1.Step: Set the url for GET request
        spec.pathParams("first", "booking", "second", bookingId);

        //2.Step: Set the expected data
        //No need to create expected data, because the data which is sent in the POST Request will be expected data

        //3.Step: Send the GET request and get the response
        Response response2 = given().spec(spec).when().get("/{first}/{second}");
        response2.prettyPrint();


        //I CONVERT GET REPONSE BODY TO JAVA OBJECT BY USING OBJECT MAPPER
        BookingPojo getResponseBodyPojo = JsonUtils.convertJsonToJavaObject(response2.asString(), BookingPojo.class);
        System.out.println(getResponseBodyPojo);


        //BY USING requestBody AND getResponseBodyPojo, I DID ASSERTION
        //4.Step: Do assertions
        assertEquals(200, response2.getStatusCode());
        assertEquals(requestBody.getFirstname(), getResponseBodyPojo.getFirstname());
        assertEquals(requestBody.getLastname(), getResponseBodyPojo.getLastname());
        assertEquals(requestBody.getDepositpaid(), getResponseBodyPojo.getDepositpaid());
        assertEquals(requestBody.getTotalprice(), getResponseBodyPojo.getTotalprice());
        assertEquals(requestBody.getAdditionalneeds(), getResponseBodyPojo.getAdditionalneeds());
        assertEquals(requestBody.getBookingdates().getCheckin(), getResponseBodyPojo.getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(), getResponseBodyPojo.getBookingdates().getCheckout());


    }


}
