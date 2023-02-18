package api_myPractice.get_requests03;


import base_urls.HerOkuAppBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get01 extends HerOkuAppBaseUrl {

    /*
         https://restful-booker.herokuapp.com/booking/7 url'ine
        accept type'i "application/json" olan GET request'i yolladigimda
        gelen response'un
        status kodunun 200
        ve content type'inin "application/json"
        ve firstname'in "Susan"
        ve lastname'in "Jones"
        ve checkin date'in 2018-06-04"
        ve checkout date'in 2020-12-03 oldugunu test edin


        with Postman: GET https://restful-booker.herokuapp.com/booking/7 -->SEND

        {
    "firstname": "Susan",
    "lastname": "Jones",
    "totalprice": 392,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2018-06-04",
        "checkout": "2020-12-03"
    }
}


     */




    @Test
    public void test01(){

      //set url
        spec.pathParams("first", "booking", "second", 7);

     //set expected data


     //send request and get response
     Response response = given().spec(spec).when().get("/{first}/{second}");
     response.prettyPrint();



    //do assertion

    response.then()
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("firstname", equalTo("Susan"), "lastname", equalTo("Jones"),
                    "totalprice", equalTo(392), "depositpaid", equalTo(false),
                    "bookingdates.checkin", equalTo("2018-06-04"),
                    "bookingdates.checkout", equalTo("2020-12-03"));


    //do assertion with Json Path

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();

        Assert.assertEquals("Susan", json.getString("firstname"));
        Assert.assertEquals("Jones", json.getString("lastname"));
        Assert.assertEquals(392, json.getInt("totalprice"));
        Assert.assertEquals(false , json.getBoolean("depositpaid"));
        Assert.assertEquals("2018-06-04", json.getString("bookingdates.checkin"));
        Assert.assertEquals("2020-12-03", json.getString("bookingdates.checkout"));




    }

}