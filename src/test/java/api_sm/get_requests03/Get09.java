package api_sm.get_requests03;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.junit.Assert.assertEquals;

public class Get09 extends JsonPlaceHolderBaseUrl {
    /*
        https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
         Dönen response un
         Status kodunun 200, dönen body de,
         "userId": 1,
         "id": 2,
          "title": "quis ut nam facilis et officia qui",
          "completed": false olduğunu test edin…
 */

  @Test
  public void test(){

      //set url
      spec.pathParams("first", "todos", "second", 2);

      //set expected data

      //send request and get response
      Response response = given().spec(spec).when().get("/{first}/{second}");


      //do assertion

      JsonPath json = response.jsonPath();

      assertEquals(200, response.getStatusCode());
      assertEquals(1, json.getInt("userId"));
      assertEquals(2, json.getInt("id"));
      assertEquals("quis ut nam facilis et officia qui", json.getString("title"));
      assertEquals(false, json.getBoolean("completed"));

  }



    @Test
    public void test2(){

        //set url
        spec.pathParams("first", "todos", "second", 2);

        //set expected data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put( "title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);


        //send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");


        //do assertion

        assertEquals(200, response.getStatusCode());
        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }


}
