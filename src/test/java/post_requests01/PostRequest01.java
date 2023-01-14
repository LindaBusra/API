package post_requests01;

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
1) JsonPath
2) De-Serialization
*/


    @Test
    public void test01(){

        //Set url

        spec.pathParams("first", "booking");


        //set expexted data
        HerOkuAppTestData testData = new HerOkuAppTestData();
        JSONObject expectedRequestData = testData.setUpTestAndRequestData();
        System.out.println("expectedRequestData" + expectedRequestData);

        //send request and get response
        Response response = given().contentType(ContentType.JSON).auth().basic("admin","password123").
                spec(spec).body(expectedRequestData.toString()).when().post("/first");

        response.prettyPrint();

        // DOGRULAMA
        // 1. YÖNTEM - JSON İLE

        JsonPath json = response.jsonPath();

        response.then().assertThat().statusCode(200);

        Assert.assertEquals(expectedRequestData.getString("firstname"), json.getString("booking.firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"), json.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getString("totalprice"), json.getInt("booking.totalprice"));
        Assert.assertEquals(expectedRequestData.getString("depositpaid"), json.getBoolean("booking.depositpaid"));


        // 1.Yol Casting
        Assert.assertEquals(((JSONObject)expectedRequestData.get("bookingdates")).getString("checkin") ,
                json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(((JSONObject)expectedRequestData.get("bookingdates")).getString("checkout") ,
                json.getString("booking.bookingdates.checkout"));
        // 2. Yol
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin") ,
                json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout") ,
                json.getString("booking.bookingdates.checkout"));



    }



}
