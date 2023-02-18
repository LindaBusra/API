package api_review.get_http_request_methods;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/5
    When
        User send a request to the URL
   Then
        HTTP Status Code should be 200
   And
        Response content type is "application/json"
   And
        Response body should be like;

{
    "firstname": "Jim",
    "lastname": "Smith",
    "totalprice": 908,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-09-09",
        "checkout": "2022-09-05"
    }
}
     */

    @Test
    public void get05() {

        //1-Set the url
        spec.pathParams("first", "booking", "second", 5);


        //2-Set the expected data


        //3-Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4-Do the assertion

        //1.way:
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Sally"),
                        "lastname", equalTo("Smith"),
                "totalprice", equalTo(510),
                "depositpaid", equalTo(false),
                "bookingdates.checkin", equalTo("2018-11-19"),   //bookingdates is a key  //nested json
                "bookingdates.checkout", equalTo("2022-08-29"));

        //2.way: Use JsonPath --> JsonPath is a class and it has many useful methods to navigate inside the Json Data
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);

        //There are two ways to create an object
        //1. way is using new keyword. you are creating object from scratch
        //JsonPath json = new JsonPath();  //1. way is using new keyword. you are creating object from scratch

        //2.way is using another object to create different object.
        //create JsonPath object from response object
        JsonPath json = response.jsonPath();  //json object has everything from response object.
        assertEquals("Firstname is not maching", "Sally", json.getString("firstname")); //That messages is displayed if test is failed.
        assertEquals("Lastname is not maching","Smith", json.getString("lastname"));
        assertEquals("Total price is not maching",510, json.getInt("totalprice"));
        assertEquals("Deposit paid value is not maching",false, json.getBoolean("depositpaid"));
        assertEquals("Checkin date is not maching","2018-11-19", json.getString("bookingdates.checkin"));
        assertEquals("Checkout date is not maching","2022-08-29", json.getString("bookingdates.checkout"));
        // From Junit -->assertEquals(message, expected, actual)


        //3.way: Sort Assertion : There is no stopping execution. Everything will be executed. You will get report for all of them.

        //1.step: Create SoftAssert object  (softAssert() comes from TestNG)
        SoftAssert softAssert = new SoftAssert();

        //By using softAssert object do assertion  //TestNG has more detail then JUnit.
        softAssert.assertEquals(json.getString("firstname"), "Sally", "Firstname is not maching");
        softAssert.assertEquals(json.getString("lastname"), "Smith", "Lastname is not maching");
        softAssert.assertEquals(json.getInt("totalprice"), 510, "Total price is not maching");
        softAssert.assertEquals(json.getBoolean("depositpaid"), false, "Deposit paid value is not maching");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2018-11-19", "Checkin date is not maching");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2022-08-29", "Checkout date is not maching");
        // From TestNG -->assertEquals(actual, expected, message)

        //Do not forget to use assertAll()
        softAssert.assertAll();  //if you don't use assertAll(), Java is not asserting the value.  you will get green everytime even test is fail. It is not meaningful.
    }
}