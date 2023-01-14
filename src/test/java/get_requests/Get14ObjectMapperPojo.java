package get_requests;


import Utils.JsonUtils;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapperPojo extends JsonPlaceHolderBaseUrl {
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
    public void get15(){

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

        //we use objectmapper  -->I created this "convertJsonToJavaObject" for handle expections
        HashMap<String,Object> expectedData= JsonUtils.convertJsonToJavaObject(expectDataInString, HashMap.class);
//      JsonPlaceHolderPojo expectedData1= JsonUtils.convertJsonToJavaObject(expectDataInString,  JsonPlaceHolderPojo.class);  we can use what er want
        System.out.println("expectedData: " + expectedData);

        JsonUtils.convertJavaObjectToJson(expectedData);

        //3.Step : Send the get  request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
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

    @Test
    public void getPojo02(){

        // Set the url
        spec.pathParams("first","todos","second",198);


        // Set the expected data


        String  expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true );
        JsonPlaceHolderPojo expectedData= JsonUtils.convertJsonToJavaObject(expectedDataInString, JsonPlaceHolderPojo.class);
        System.out.println("expectedData: " + expectedData);


       //3.Step : Send the get  request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //4.Step : Do Assertion

        JsonPlaceHolderPojo actualData= JsonUtils.convertJsonToJavaObject(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData : "  + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());



    }
}