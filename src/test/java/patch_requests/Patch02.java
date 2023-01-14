package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Patch02 extends JsonPlaceHolderBaseUrl {


        /*
        Given URL:
    https://jsonplaceholder.typicode.com/todos/198
   {
      "title": "I have to work with API"
     }
      When
           I send PATCH Request to the Url
      Then
            Status code is 200
            And response body is like
{
 "userId": 10,
 "title": "I have to work with API"
 "completed": true,
 "id": 198
}
     */




    @Test
    public void patch01(){

      //set url
      spec.pathParams("first", "todos","second", 198)  ;

      //set request and expected data

        JsonPlaceHolderTestData obj1 = new JsonPlaceHolderTestData();
        JSONObject requestData = obj1.setUpPatchRequestData();
        System.out.println("requestData: " + requestData);


        JsonPlaceHolderTestData obj2 = new JsonPlaceHolderTestData();
        JSONObject expectedData = obj2.setUpPatchExpectedData();
        System.out.println("expectedData: " + expectedData);

       //send request

        Response response =given().contentType(ContentType.JSON).spec(spec)           // I can add these : auth().basic("admin","password123").
        .body(requestData.toString()).when().patch("/{first}/{second}");
        response.prettyPrint();


        //De-serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(expectedData.get( "userId"), actualData.get( "userId"));
        Assert.assertEquals(expectedData.get( "title"), actualData.get(  "title"));
        Assert.assertEquals(expectedData.get(  "completed"), actualData.get(  "completed"));
        Assert.assertEquals(expectedData.get( "id"), actualData.get( "id"));






    }



}
