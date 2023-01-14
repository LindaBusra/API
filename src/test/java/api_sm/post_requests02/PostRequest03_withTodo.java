package api_sm.post_requests02;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.TodosPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class PostRequest03_withTodo extends JsonPlaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
  {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": …
   }
*/

    @Test
    public void test03() {

        //set url
        spec.pathParam("first", "todos");


        // Expected Data
        TodosPojo expectedTodo = new TodosPojo(21, 201, "Tidy your room", false);



        // send request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedTodo).when().post("/{first}");
        response.prettyPrint();



        //do assertion
        TodosPojo actulaData = response.as(TodosPojo.class);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedTodo.getUserId(), actulaData.getUserId());
        assertEquals(expectedTodo.getId(), actulaData.getId());
        assertEquals(expectedTodo.getTitle(), actulaData.getTitle());
        assertEquals(expectedTodo.isCompleted(), actulaData.isCompleted());


    }

}