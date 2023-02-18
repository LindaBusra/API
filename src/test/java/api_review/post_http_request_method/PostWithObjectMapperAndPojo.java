package api_review.post_http_request_method;

import Utils.JsonUtils;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWithObjectMapperAndPojo extends JsonPlaceHolderBaseUrl {

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
                                    }

    //I used HashMap in ObjectMapper. Now I will use pojo in ObjectMapper.
    //Map is Java object. Pojo is my object, I created it. I wil test, I can use my object in ObjectMApper  */


    @Test
    public void postWithObjectMapperAndPojo01(){

        //1.Step: Set the url
        spec.pathParam("first", "todos");

        //2.Step: Set the request body
        JsonPlaceHolderTestData expected = new JsonPlaceHolderTestData();
        String expectedData = expected.expectedDataInString(55, "Tidy your room", false);  //That method return String

        JsonPlaceHolderPojo expectedDataPojo = JsonUtils.convertJsonToJavaObject(expectedData, JsonPlaceHolderPojo.class);
        System.out.println(expectedDataPojo);

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataPojo).when().post("/{first}");
        response.prettyPrint();

        JsonPlaceHolderPojo actualDataPojo = JsonUtils.convertJsonToJavaObject(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println(actualDataPojo);

        //4.Step: Do assertion
        assertEquals(201, response.getStatusCode());   //201: new data is created successfully
        assertEquals(expectedDataPojo.getUserId(), actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(), actualDataPojo.getCompleted());





    }
}