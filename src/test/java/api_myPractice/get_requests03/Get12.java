package api_myPractice.get_requests03;


import base_urls.JsonPlaceHolderBaseUrl;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12 extends JsonPlaceHolderBaseUrl {


    /*
    GetRequestWithObjectMapper01:
 https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
Dönen response ‘un status kodunun 200 ve body kısmının
 {
 "userId": 10,
 "id": 198,
 "title": "quis eius est sint explicabo",
 "completed": true
 }
     */


    @Test
    public void get12() {

        //Set the url
        spec.pathParams("first", "todos", "second", 198);


       //set expected data
      HashMap<String, Object> expectedData = new HashMap<>();
      expectedData.put("userId", 10);
      expectedData.put( "id", 198);
      expectedData.put( "title", "quis eius est sint explicabo");
      expectedData.put( "completed", true);


      //send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //do assertion
        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


        //with Json
        JsonPath json = response.jsonPath();

        assertEquals(expectedData.get("userId"), json.getInt("userId"));
        assertEquals(expectedData.get("id"), json.getInt("id"));
        assertEquals(expectedData.get("title"), json.getString("title"));
        assertEquals(expectedData.get("completed"), json.getBoolean("completed"));





    }
}
