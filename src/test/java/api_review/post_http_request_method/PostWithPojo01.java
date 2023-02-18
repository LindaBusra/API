package api_review.post_http_request_method;

import api_review.pojos.JsonPlaceHolderPojo;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

public class PostWithPojo01 extends JsonPlaceHolderBaseUrl {


    //Pojo is most common usage in API testing

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
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }                                           */

    @Test
    public void postWithPojo(){

        //1-Set the url
        spec.pathParam("first", "todos");

        //2-Set the request body
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55,"Tidy your room", false);


        //3-Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();


        //4-Do assertion
        //1.way with Pojo
        response.then().assertThat().statusCode(201)
                .body( "userId", equalTo(requestBody.getUserId()),  //from json equalTo(from pojo)
                        "title", equalTo(requestBody.getTitle()),
                        "completed", equalTo(requestBody.getCompleted()));

        //2.way Use De-serialization
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println(actualData);
        //Here I get an error when I convert response to Pojo, because in my pojo class I have not id. Id coming from API automatically. In my pojo I have 3 keys, but here 4 keys.
        //Structures are different. -->Pojo:userId. completed, title     //Response:userId. completed, title, id
        //So I used @JsonIgnoreProperties(ignoreUnknown = true) before clas  //if you dont know some variable just ignore it

        assertEquals(requestBody.getUserId(), actualData.getUserId());
        assertEquals(requestBody.getTitle(), actualData.getTitle());
        assertEquals(requestBody.getCompleted(), actualData.getCompleted());
    }

}
