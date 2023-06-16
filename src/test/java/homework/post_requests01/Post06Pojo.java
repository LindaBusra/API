package post_requests01;


import base_urls.JsonPlaceHolderBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Test;
import pojos.TodosPojo;


import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post06Pojo extends JsonPlaceHolderBaseUrl {


        /*
    https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
 Request body{
 "userId": 21,
 "id": 201,
 "title": "Tidy your room",
 "completed": false
}
 Status kodun 201, response body ‘nin ise
{
 "userId": 21,
 "id": 201,
 "title": "Tidy your room",
 "completed": false
 }
     */


    @Test
    public void post(){

        //set url
        spec.pathParam("first", "todos");

        //set request and expected data

        TodosPojo requestExpected=new TodosPojo(21,201,"Tidy your room",false);
        System.out.println(requestExpected);

        //send request and get response
        Response response = given().contentType(ContentType.JSON).spec(spec).body(requestExpected).when().post("/{first}");    //we can add auth().basic("admin","password123").
        response.prettyPrint();

        //do assertion
        TodosPojo actualData = response.as(TodosPojo.class);

        assertEquals(201, response.statusCode());
        assertEquals(requestExpected.getId(), actualData.getId());
        assertEquals(requestExpected.getUserId(), actualData.getUserId());
        assertEquals(requestExpected.getTitle(), actualData.getTitle());
        assertEquals(requestExpected.isCompleted(), actualData.isCompleted());





    }




}
