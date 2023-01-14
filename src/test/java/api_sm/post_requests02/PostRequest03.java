package api_sm.post_requests02;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {
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
        JsonPlaceHolderTestData obje = new JsonPlaceHolderTestData();

        JSONObject expectedRequest = obje.setUpPostData();
        System.out.println("expectedRequest = " + expectedRequest);



        // send request and get the response

        Response response=given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(expectedRequest.toString())  //  use toString() method, otherwise it gives error in JSONObject.
                .when()
                .post("/{first}");
        response.prettyPrint();



        //do assertion

        // 1-with Matcher class
        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("userId" , equalTo(expectedRequest.get("userId")),
                        "title" , equalTo(expectedRequest.get("title")),
                        "completed" , equalTo(false)
                );


        //2-with Matcher class
        response.then().assertThat().statusCode(expectedRequest.getInt("statusCode"))
                .body("userId",equalTo(expectedRequest.get("userId"))
                        , "title",equalTo(expectedRequest.get("title"))
                        , "completed",equalTo(expectedRequest.get("completed")));



        //2. Json Path
        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedRequest.get("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedRequest.get("statusCode"), json.getInt("statusCode"));
        Assert.assertEquals(expectedRequest.get("title"), json.getString("title"));
        Assert.assertEquals(expectedRequest.get("completed"), json.getBoolean("completed"));

        //3. De-Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.getBoolean("completed"), actualDataMap.get("completed"));
        Assert.assertEquals(expectedRequest.getString("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.get("statusCode"), response.statusCode());




    }


    @Test
    public void test() {
        //Set the url
        spec.pathParam("bir", "todos");

        //Set the expected Data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("id", 201);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);


        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{bir}");
        response.prettyPrint();

        //Validation
        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedData.get("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedData.get("id"), json.getInt("id"));
        Assert.assertEquals(expectedData.get("title"), json.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), json.getBoolean("completed"));
        Assert.assertEquals(201, response.statusCode());


    }
}