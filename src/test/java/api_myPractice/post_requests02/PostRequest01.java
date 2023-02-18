package api_myPractice.post_requests02;


import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends HerOkuAppBaseUrl {
     /*
https://restful-booker.herokuapp.com/booking/47
       {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-02-01",
               "checkout": "2022-02-11"
          }
       }
1) JsonPhat
2) De-Serialization
*/

    @Test
    public void test01() {

        // Set url

        spec.pathParam("first" , "booking");

        // Expected Data

        HerOkuAppTestData obj  = new HerOkuAppTestData();
        JSONObject expectedRequestData = obj .setUpTestAndRequestData();
        System.out.println("expectedRequestData = " + expectedRequestData);

        // REQUEST and RESPONSE

        Response response = given()
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin","password123")
                .spec(spec)
                .body(expectedRequestData.toString())
                .when()
                .post("/{first}");

        response.prettyPrint();


        // do assertion
        // 1.way- JSON

        JsonPath json = response.jsonPath();

        response.then().assertThat().statusCode(200);

        Assert.assertEquals(expectedRequestData.getString("firstname"), json.getString("booking.firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"), json.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"), json.getInt("booking.totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"), json.getBoolean("booking.depositpaid"));


        // 1.wayCasting
        Assert.assertEquals(((JSONObject)expectedRequestData.get("bookingdates")).getString("checkin") ,
                json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(((JSONObject)expectedRequestData.get("bookingdates")).getString("checkout") ,
                json.getString("booking.bookingdates.checkout"));


        // 2. way
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin") ,
                json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout") ,
                json.getString("booking.bookingdates.checkout"));






    }


}