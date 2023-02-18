package api_review.delete_http_request_methods;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Delete01 extends JsonPlaceHolderBaseUrl {

        /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */


    @Test
    public void delete01(){

        //1-Set the url
        spec.pathParams("f", "todos", "s", 198);

        //2-Set the expected data
        Map<String, Object> expectedMap = new HashMap<>();  //1    same with {}


        //3-Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().delete("/{f}/{s}");
        response.prettyPrint();

        //Gson
        Map<String, Object> actualMap = response.as(HashMap.class);
        System.out.println(actualMap);

        //4-Do the assertion
        response.then().assertThat().statusCode(200);
        //1.way to assert
        assertEquals(expectedMap, actualMap);  //I used Gson because they were in different data type.  //2
        //2.way to assert
        assertTrue(actualMap.size()==0);  //2.way for assertion, and we don't need 1 and 2
        //3.way to assert -->recommended
        assertTrue(actualMap.isEmpty());

    }

}
