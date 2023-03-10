package HerOkuAppSmokeTest;

import Utils.JsonUtils;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static HerOkuAppSmokeTest.S1Post.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S3Get extends HerOkuAppBaseUrl {

    //we are going to get data we are updated

    /*
    Given
            https://restful-booker.herokuapp.com/booking/:id

   When
        User send Get request

   Then
          Status code is 200
    And
          Response body is like

            {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 500,
        "depositpaid": false,
        "bookingdates": {
            "checkin": "2022-11-27",
            "checkout": "2022-11-29"
        }
     */

    @Test
    public void get01(){

     //set the url
     spec.pathParams("first", "booking", "second", bookingid);
     //because of bookingid we use run over package->Run Test in HerOkuAppSmokeTest

     //
    //set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2022-11-27","2022-11-29");
        BookingPojo expectedData = new BookingPojo("James","Brown",500,false,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

     //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

    //Do assertion
        BookingPojo actualData = JsonUtils.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
    }
}