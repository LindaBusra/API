package get_requests;

import Utils.JsonUtils;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class GetRequestWithObjectMapper01 extends JsonPlaceHolderBaseUrl {

        /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */


    @Test
    public void test(){

        //set url
        spec.pathParams("first", "todos","second", 198);

        //set expected data
          String jsonData ="{\n" +
                "\t\t\t\t\t\t\t\t\t    \"userId\": 10,\n" +
                "\t\t\t\t\t\t\t\t\t    \"id\": 198,\n" +
                "\t\t\t\t\t\t\t\t\t    \"title\": \"quis eius est sint explicabo\",\n" +
                "\t\t\t\t\t\t\t\t\t    \"completed\": true\n" +
                "\t\t\t\t\t\t\t\t\t  }";


       Map<String, Object> expectedData = JsonUtils.convertJsonToJavaObject(jsonData, Map.class);
        System.out.println("expectedData:" + expectedData);


        //send request and get response
        Response response = given().contentType(ContentType.JSON).spec(spec)
                .when().get("/{first}/{second}");

        response.prettyPrint();     //it is in json format


        //do assertion
        Map<String, Object> actualData =  JsonUtils.convertJsonToJavaObject(response.asString(), Map.class);
        System.out.println("actualData :" + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));




    }

}
