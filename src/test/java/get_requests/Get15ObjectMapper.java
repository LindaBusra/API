package get_requests;


import Utils.JsonUtils;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper  extends HerOkuAppBaseUrl {
    /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
{
    "firstname": "Edgar",
    "lastname": "Dominguez",
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
    public void get01() {
        //1.Step : set the url
        spec.pathParams("first", "booking", "second", 555);


        //2.Step :set the excepted data
        HerOkuAppTestData herOkuAppTestData = new HerOkuAppTestData();

        String expectedDataInString = herOkuAppTestData.expectedDataInString("Edgar", "Dominguez", 111, true, "2018-01-01", "2019-01-01", "Breakfast");

        //or we can write it like that
//      String expectedDataInString = new HerOkuAppTestData().expectedDataInString("Edgar", "Dominguez", 111, true, "2018-01-01", "2019-01-01", "Breakfast");


        BookingPojo expectedData = JsonUtils.convertJsonToJavaObject(expectedDataInString, BookingPojo.class);


        //3.Step : Sent the get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");


        //4.Step : Do Assertion
        BookingPojo actualDataBody = JsonUtils.convertJsonToJavaObject(response.asString(), BookingPojo.class);
//To use Pojo Class with Object Mapper is the best!!!

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstname(), actualDataBody.getFirstname());
        assertEquals(expectedData.getLastname(), actualDataBody.getLastname());
        assertEquals(expectedData.getTotalprice(), actualDataBody.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualDataBody.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualDataBody.getAdditionalneeds());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualDataBody.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualDataBody.getBookingdates().getCheckout());


        // soft assert
        //1.Adım Softassert objesi oluşturma

        SoftAssert softAssert = new SoftAssert();

        //2.assertion yap
        softAssert.assertEquals(200, response.getStatusCode());
        softAssert.assertEquals(expectedData.getFirstname(), actualDataBody.getFirstname());
        softAssert.assertEquals(expectedData.getLastname(), actualDataBody.getLastname());
        softAssert.assertEquals(expectedData.getTotalprice(), actualDataBody.getTotalprice());
        softAssert.assertEquals(expectedData.getDepositpaid(), actualDataBody.getDepositpaid());
        softAssert.assertEquals(expectedData.getAdditionalneeds(), actualDataBody.getAdditionalneeds());
        softAssert.assertEquals(expectedData.getBookingdates().getCheckin(), actualDataBody.getBookingdates().getCheckin());
        softAssert.assertEquals(expectedData.getBookingdates().getCheckout(), actualDataBody.getBookingdates().getCheckout());

        //3.SoftassertAll methodunu çalıştır
        softAssert.assertAll();
    }
}


