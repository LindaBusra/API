package api_sm.get_requests03;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.TodosPojo;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get18 extends JsonPlaceHolderBaseUrl {


         /*
    Given https://jsonplaceholder.typicode/todos/2
    When I send a Get request to the Url
    Then the actual data should be as following:
        {
        "userId":1,
        "id":2,
        "title": "quis ut nam facilis et officia qui",
        "completed":false
        }
     */

    @Test
    public void get() {
        //Set the Url
        spec.pathParams("first", "todos", "second", 2);

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        //send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }


    @Test
    public void get2() {
        //Set the Url
        spec.pathParams("first", "todos", "second", 2);

        //Set the expected data
        TodosPojo expectedData = new TodosPojo(1,2,"quis ut nam facilis et officia qui", false);

        //send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //do assertion
        TodosPojo actualData = response.as(TodosPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.isCompleted(), actualData.isCompleted());

    }
}