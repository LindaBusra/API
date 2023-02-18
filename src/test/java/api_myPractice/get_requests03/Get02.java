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

public class Get02 extends HerOkuAppBaseUrl {

    /*
    "https://restful-booker.herokuapp.com/booking/5 "
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in “Sally"
    ve totalprice’in 100
    ve checkin date'in 2021-11-11"oldugunu test edin
    */


    @Test
    public void test01(){

        //set url
        spec.pathParams("first", "booking", "second", 5);

        //set expected data


        //send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();



        //do assertion

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Sally"),
                        "totalprice", equalTo(100),
                        "bookingdates.checkin", equalTo("2021-11-11"));



        //do assertion with Json Path

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();

        Assert.assertEquals("Sally", json.getString("firstname"));
        Assert.assertEquals(100, json.getInt("totalprice"));
        Assert.assertEquals("2021-11-11", json.getString("bookingdates.checkin"));





    }

}