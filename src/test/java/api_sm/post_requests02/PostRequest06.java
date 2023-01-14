package api_sm.post_requests02;

import base_urls.HerOkuAppBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo2;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostRequest06 extends HerOkuAppBaseUrl {
        /*
    https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "Selim",
                   "lastname": "Ak",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2020-09-09",
                       "checkout": "2020-09-21"
                    }
                 }
   Status code is 200
    response body  {
                            "bookingid": 11,
                            "booking": {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
                            }
                         }
     */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "booking");

        //Set the expected Data
        BookingDatesPojo bookingdates = new BookingDatesPojo("2020-09-09","2020-09-21" );

        BookingPojo2 expectedData = new BookingPojo2("Selim", "Ak", 15000, true, bookingdates);



        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
//        response.prettyPrint();

       //Validation

        JsonPath json = response.jsonPath();

        System.out.println(bookingdates.getCheckin());
        System.out.println(json.getString("booking.bookingdates.checkin"));
        assertEquals(expectedData.getFirstname(), json.getString("booking.firstname"));
        assertEquals(expectedData.getLastname(), json.getString("booking.lastname"));
        assertEquals(expectedData.getTotalprice(), json.getInt("booking.totalprice"));
        assertEquals(expectedData.isDepositpaid(), json.getBoolean("booking.depositpaid"));
        assertEquals(bookingdates.getCheckin(), json.getString("booking.bookingdates.checkin"));
        assertEquals(bookingdates.getCheckout(), json.getString("booking.bookingdates.checkout"));





    }

}