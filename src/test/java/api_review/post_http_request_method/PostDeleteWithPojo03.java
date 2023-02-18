package api_review.post_http_request_method;

import api_review.pojos.JsonPlaceHolderPojo;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class PostDeleteWithPojo03 extends JsonPlaceHolderBaseUrl {

        /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
            I send DELETE Request to the Url
        Then
            response body is like {  }

         */


    @Test
    public void PostDeleteWithPojo01() {

        //1-Set the url
        spec.pathParam("first", "todos");

        //2-Set the post request body
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //3-Send the request and get the response body
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        //Note:Response body has "id", I need "id" value to use "delete()". So I should get id value from response body.

        //Get the id of newly created data  ***
        JsonPath json = response.jsonPath();
        Integer id = json.getInt("id");

        //Set the url for delete() method. I will use that id in Delete url
        spec.pathParams("first", "todos", "second", id);

        //Send the delete request and get the response
        Response response2 = given().spec(spec).when().delete("/{first}/{second}");
        response2.prettyPrint();

        //Convert response2 to a Map
        Map<String, Object> actualData = response2.as(HashMap.class);

        assertTrue(actualData.isEmpty()); //***             //or actualData.size()==0

        //You can run this method 1000 times, because after you created data, you will delete it.

        //you can make a method  and  use ***  part in this method and use this method with @After annotation,
        //Junit will deleted every data after your test method


    }


}
