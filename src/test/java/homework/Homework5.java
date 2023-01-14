package homework;

import base_urls.RegresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Homework5 extends RegresInBaseUrl {

   /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void homework3(){


        //Set the url
        spec.pathParams("first","unknown","second", 3);


        //Set the expected data


        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json");


        JsonPath jsonPath = response.jsonPath();   //convert response to JsonPath

        //Soft Assertion

        //1.st: Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();


        //2nd. Do assertion
        softAssert.assertEquals(jsonPath.getInt("data.id"), 3, "id did not match");
        softAssert.assertEquals(jsonPath.getString("data.name"), "true red", "Name did not match");
        softAssert.assertEquals(jsonPath.getInt("data.year"), 2002, "year did not match");
        softAssert.assertEquals(jsonPath.getString("data.color"), "#BF1932", "color did not match");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"), "19-1664", "pantone_value name did not match");

        softAssert.assertEquals(jsonPath.getString("support.url"), "https://reqres.in/#support-heading", "support.url did not match");
        softAssert.assertEquals(jsonPath.getString("support.text"), "To keep ReqRes free, contributions towards server costs are appreciated!", "text  did not match");


        //3rd: Use AssertAll() method.
        softAssert.assertAll();

    }
}
