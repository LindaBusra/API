package api_review.post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {

        /*
        Given
            https://jsonplaceholder.typicode.com/todos
            {
           "userId": 10,
           "title": "Tidy your room",
           "completed": false
               }
    When
        I send POST request to the url
    Then
         Status code is 201
    And
        Response body is like {
           "userId": 10,
           "title": "Tidy your room",
           "completed": true,
           "id":201
              }
     */

    @Test
    public void post02(){

        //1-Set the url
        spec.pathParam("first", "todos");


        //2-Set expected data
//        Map<String, Object> expectedData = new HashMap<>();
//        expectedData.put("userId", 66);
//        expectedData.put("title", "Tidy your room");
//        expectedData.put("completed", false);

        //instead of the above, I made it dynamic with the following process, to make post() method short. Changing will make on test methods. not here.
        //I created an object, and with this object I can access expectedDataSetUp() method. This method returns a Map
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = expectedData.expectedDataSetUpWithAllKeys(66,"Tidy your room", false);

        //3-Send request and get the response
        Response response = given().spec(spec).
                auth().basic("admin", "1234").      //Authorization
                contentType(ContentType.JSON).      //sometime don't write "contentType(ContentType.JSON)" gives me error. It depend on API
                body(expectedDataMap).              //after all these we can start action.
                when().post("/{first}");

        response.prettyPrint();
        //I added Status Code to use it in assertion.
        expectedDataMap.put("Status Code", 201);  //mostly 200 is for get request, 201 is for post request (it means new date created)


        //4-Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data: " + actualData);

        assertEquals(expectedDataMap.get("Status Code"), response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));

    }
}
