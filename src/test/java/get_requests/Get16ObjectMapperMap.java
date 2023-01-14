package get_requests;


import Utils.JsonUtils;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;


import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get16ObjectMapperMap extends JsonPlaceHolderBaseUrl {
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
    public void get14(){

        //1.Step :Set the url
        spec.pathParams("first","todos","second",198);


        // 2Step : set the expected data.


        //This is not recommended

    /*    String expectDataInString = "{\n" +
                "   \"userId\": 10,\n" +
                "  \"id\": 198,\n" +                //you can delete id for example
                "   \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                "  }";      */

        //This is recommended

        String  expectDataInString = new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true );

        //we use objectmapper  -->I created this "convertJsonToJavaObject" for handle exceptions
        HashMap<String,Object> expectedData= JsonUtils.convertJsonToJavaObject(expectDataInString, HashMap.class);
        System.out.println("expectedData: " + expectedData);


        //3.Step : Send the get  request and get the response
        Response response=given().contentType(ContentType.JSON).spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step : Do Assertion

        HashMap<String,Object> actualData= JsonUtils.convertJsonToJavaObject(response.asString(),HashMap.class);
        //1.parameter must be string, 2. one can be any type of class
        System.out.println("actualData: " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }




    }
