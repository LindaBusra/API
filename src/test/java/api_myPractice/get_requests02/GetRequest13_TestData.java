package api_myPractice.get_requests02;



import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class GetRequest13_TestData extends JsonPlaceHolderBaseUrl {

        /*
  https://jsonplaceholder.typicode.com/todos/2
  1) Status kodunun 200,
  2) respose body'de,
           "completed": değerinin false
           "title": değerinin "quis ut nam facilis et officia qui"
           "userId" sinin 1 ve
      header değerlerinden
           "via" değerinin "1.1 vegur" ve
           "Server" değerinin "cloudflare" olduğunu test edin…
  */

    @Test
    public void test21() {
        //set url
        spec.pathParams("first","todos", "second", 2);

        //set expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expextedData = (HashMap<String, Object>) obj.setUpTestData();
        System.out.println("expextedData  :" + expextedData);   //(Inside the JsonPlaceHolderTestData)

        //{Server=cloudflare,
        // completed=false,
        // title=quis ut nam facilis et officia qui,
        // userId=1,
        // statusCode=200,
        // via=1.1 vegur}

        //send request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();



        //do assertion

        //1.with Matcher class
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                headers("Server", equalTo("cloudflare"), "via", equalTo("1.1 vegur") ).
                body("completed", equalTo(false), "title", equalTo("quis ut nam facilis et officia qui"),
                       "userId", equalTo(1) );

        //2.Json Path
        JsonPath json = response.jsonPath();
        assertEquals(200, response.statusCode());
        assertEquals("cloudflare", response.getHeader("Server"));   //header
        assertEquals("1.1 vegur", response.getHeader("via"));       //header

        assertEquals(false, json.getBoolean("completed"));
        assertEquals("quis ut nam facilis et officia qui", json.getString("title"));    //body
        assertEquals(1, json.getInt("userId"));     //body


        //3. with De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData:" + actualData);     //{id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}

        assertEquals(expextedData.get("completed"), actualData.get("completed"));
        assertEquals(expextedData.get("title"), actualData.get("title"));
        assertEquals(expextedData.get("userId"), actualData.get("userId"));


    }



}
