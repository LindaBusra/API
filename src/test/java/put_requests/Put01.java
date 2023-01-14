package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

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

    //put is for updating data. I am going to change this body. First send it with postmann


    @Test
    public void put01() {

        //1.Step : set the url
        spec.pathParams("first", "todos", "second", 198);


        //2.Step set the expected data-->we are going to create our payload
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataJPH(21, "Wash the dishes", false);
        System.out.println("expectedData: " + expectedData);

        //3.Step : Send Put request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        // 4. Step : Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //Do Assertion

    }

        @Test
        public void put02(){
            spec.pathParams("parametre1","todos",
                    "parametre2",198);

            JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
            JSONObject expectedRequest= testObje.setUpPutData();
            System.out.println(expectedRequest);

            Response response=given().
                    contentType(ContentType.JSON).
                    spec(spec).
                    auth().basic("admin","password123").
                    body(expectedRequest.toString()).
                    when().
                    put("/{parametre1}/{parametre2}");
            response.prettyPrint();

            //JsonPath

            JsonPath json=response.jsonPath();
            Assert.assertEquals(200,response.getStatusCode());
            Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
            Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
            Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));




        }

    @Test
    public void put03(){

        //set url
        spec.pathParams("first", "todos", "second", 198);


        //set expected data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 21);
        expectedData.put("title", "Wash the dishes");
        expectedData.put("completed", false);

        //send request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //do assertion

        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));



    }

    }
