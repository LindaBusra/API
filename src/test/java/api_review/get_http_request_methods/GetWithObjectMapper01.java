package api_review.get_http_request_methods;

import Utils.JsonUtils;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetWithObjectMapper01 extends JsonPlaceHolderBaseUrl {

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
    public void getWithObjectMapper01() {

        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 198);

        //2.Step: Set the expected data

        //1.way: Not recommended
//        String expectedData ="{\n" +
//                "    \"userId\": 10,\n" +
//                "   \"id\": 198,\n" +
//                "    \"title\": \"quis eius est sint explicabo\",\n" +
//                "    \"completed\": true\n" +
//                " }";
//        HashMap<String, Object> expectedDataMap = JsonUtils.convertJsonToJavaObject(expectedData, HashMap.class);


        //2.way   get String expectedData from testdata
        JsonPlaceHolderTestData expected = new JsonPlaceHolderTestData();
        String expectedData = expected.expectedDataInString(10, "quis eius est sint explicabo", true);

        HashMap<String, Object> expectedDataMap = JsonUtils.convertJsonToJavaObject(expectedData, HashMap.class);
    //That methods needs string parameter in the first part. If I put the given response body in ""  it will be string

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String, Object> actualDataMap = JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);

        //4.Step: Do assertion
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));



    }
}