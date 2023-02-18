package api_review.patch_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Patch01 extends JsonPlaceHolderBaseUrl {

        /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,                   //in some country it accept just double, so it uses like 10.0
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */



    /* In Postman I will  change just title in this body with -->""title": "tidy your room"
    {
        "userId": 10,
            "id": 198,
            "title": "quis eius est sint explicabo",
            "completed": true
    }


//-------------------------------------------------------------------------------------------------------------
    To make it in Postman--->PATCH https://jsonplaceholder.typicode.com/todos/198
    Body -raw -json
    {

    "title": "tidy your room"
  }        and SEND
//-------------------------------------------------------------------------------------------------------------



When I make same with PUT method, it remove the others, we have just
{
    "title": "tidy your room",
    "id": 198
}

    in PATCH missing data doesn't be touch. In PUT, API think "they dont sent "completed", it will be deleted, and set null
    PUT method everytime touches all data.

    PUT is for fully updated
    PATCH is for partial updated
    */

    @Test
    public void patch01(){

    //1-Set the url
        spec.pathParams("first", "todos", "second", 198);


    //2-Set the request body data
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataSetUpWithSomeKeys(null, "Wash the dishes", null);
        System.out.println(requestBodyMap);  //it give me just  {title=Wash the dishes}, because I made userId and completed null,
        // I sendt just "Wash the dishes"-->in patch there is just partial update

    //3-Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        response.prettyPrint();


    //4-Do assertion

    //1.Logic:No need to verify data which did you not touch because You did not touch "userId" and "completed", so no need to do assertion for them
    response.then().assertThat().statusCode(200)
            .body("title", equalTo(requestBodyMap.get("title")));

    //2.Logic:You did not touch "userId" and "completed" but maybe changing "title" affected the "userId" and "completed"
    //So I need to do assertion for "userId" and "completed" as well even you did not touch them.

        Map<String, Object> expectedDataMap =  requestBody.expectedDataSetUpWithAllKeys(10, "Wash the dishes", true );
        response.then().assertThat().statusCode(200).body("userId", equalTo(expectedDataMap.get("userId")),
                "title", equalTo(expectedDataMap.get("title")),
                "completed", equalTo(expectedDataMap.get("completed")));

    //When we came this part we have to assert userId, completed and title. Inside the requestBodyMap there is no completed and userId. I can not do assertion
    //Because of that I created a ny Map, expectedDataMap with userId, completed and title. and I used them in assertion part.
    }





    @Test
    public void patch02(){

        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 198);

        //2.Step: Set the Request Body
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataSetUpWithSomeKeys(null, "Wash the dishes", null);

        //3.Step: Send the PATCH Request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertions
        //1.Way:
        response.then().assertThat().statusCode(200).body("title", equalTo(requestBodyMap.get("title")));

        //When you do PATCH Assertion, just the data you updated should be asserted. But if someone insists on assert all parts do the following
        Map<String, Object> MapToAssertAllDetails =  requestBody.expectedDataWithAllKeys(10, "Wash the dishes", true);
        response.then().assertThat().statusCode(200).body("title", equalTo(MapToAssertAllDetails.get("title")),
                "userId", equalTo(MapToAssertAllDetails.get("userId")),
                "completed", equalTo(MapToAssertAllDetails.get("completed")));

    }
}
