package api_review.put_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

    //For full update we use put method, put is for updating exist data

    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void put01(){

        //1-Set the url
        spec.pathParams("first", "todos", "second", 198);

        //2-Set the request body data
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap= requestBody.expectedDataSetUpWithAllKeys(21, "Wash the dishes", false);

        //3-Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
        response.prettyPrint();                                       //body method use requestBodyMap as Json, so we need  contentType(ContentType.JSON)

        //4-Do assertion

        //1.way
        response.then().assertThat()
                .statusCode(200)
                .body("userId", equalTo(requestBodyMap.get("userId")),
                        "title", equalTo(requestBodyMap.get("title")),
                        "completed", equalTo(requestBodyMap.get("completed")));

        //2.way: Use Gson to convert response body to a map: De-serialization-->Converting Json Data to Java Object
        Map<String, Object> actualData = response.as(HashMap.class);  //response was in json type, I convert it Java Object -->MAp
        assertEquals(200, response.statusCode());
        assertEquals(requestBodyMap.get("userId"), actualData.get("userId") );
        assertEquals(requestBodyMap.get("title"), actualData.get("title") );
        assertEquals(requestBodyMap.get("completed"), actualData.get("completed") );

        //id is created by API most of the times. We dont make any assertion about it.



        //How to use GSON in Serialization:  Convert Java object to Json Data.
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Ali Can", 13);
        ages.put("Veli Han", 15);
        ages.put("Ayse Kan", 21);
        ages.put("Mary Star", 65);
        System.out.println(ages);

        //Convert ages map to json data..
        //1.Step- Create Gson object
        Gson gson = new Gson();
        //2.Step- By using "gson" object select method to convert Java Object to Json Data
        String gsonFromMap = gson.toJson(ages);  //toJson() method returns String
        System.out.println(gsonFromMap);


        //Json use 1-double-quites and 2-there is semicolon : between key and value
        //{"Mary Star":65,"Ayse Kan":21,"Ali Can":13,"Veli Han":15}

        //In map there is no double-quites, and there is =  between key and value
        //{Mary Star=65, Ayse Kan=21, Ali Can=13, Veli Han=15}
    }
}
