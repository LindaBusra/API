package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
public class Get10 extends GoRestBaseUrl {


    /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
           {
        "meta": null,
        "data": {
            "id": 13,
            "name": "Suresh Johar",
            "email": "suresh_johar@von-damore.biz",
            "gender": "female",
            "status": "active"
                 }
            }
     */


    @Test
    public void get01() {

        //1. Step: Set the Url
        spec.pathParams("first", "users", "second", 13);

        //2. Step: Set the expected data
        GoRestTestData obj = new GoRestTestData();//I created an object from class
        Map<String, String> goRestDataMap = obj.goRestDataMapSetUp("Suresh Johar", "suresh_johar@von-damore.biz", "female", "active");//inner map
        Map<String, Object> expectedData = obj.expectedDataMapSetUp(null, goRestDataMap);//outher map


        //3. Step: Send the request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");


        //4. Step: Do Assertion

        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization==> from Json to Java Object

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("meta"),actualData.get("meta"));      //meta: data obout data
        assertEquals(goRestDataMap.get("name"),((Map)(actualData.get("data"))).get("name"));
        assertEquals(goRestDataMap.get("email"),((Map)(actualData.get("data"))).get("email"));
        assertEquals(goRestDataMap.get("gender"),((Map)(actualData.get("data"))).get("gender"));
        assertEquals(goRestDataMap.get("status"),((Map)(actualData.get("data"))).get("status"));

    }
}

//De-Serialization==> from Json to Java Object