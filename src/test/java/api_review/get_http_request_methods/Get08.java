package api_review.get_http_request_methods;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
    De-serialization:
    The biggest challenge in API Testing is data type. Because API using json data. It is getting json data and producing json data.
    Java does not use json data, it uses String, int, boolean... There is different data types between programming languages and API

    In Java we have Object (all non-primitives), Primitive data types, Maps. In API we have xml and json. They are using different languages.
    There is deserialization.-->you will get Json data and convert it to java object
    API (Json) --------->deserialization---------->Object (Java)

    Serialization:
    Sometimes we need to convert Object to Json data.  This is called serialization.
    Object (Java) --------->serialization---------->API (Json)

    1)Java uses Objects and Primitives as data type. API uses xml, Json, etc.
    Java and API are using diffret data types, but they sholud communicate with each other. Communication is impossible with diffrenet data types.

    We have 2 options:
    1)Convert Json to Java Object -->De-serialization  *It uses mostly. because we are typing our code in java.
    2)Convert Java Object to Json -->Serialization

    For De-serialization and Serialization, we have 2 options:
    a)GSON ---> Google created it.
    b)Object Mapper -->It is created after GSON, it is more popular in the market.       */

   /*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
        User send a request to the URL
    Then
        HTTP Status Code should be 200
    And
        "completed" is false
    And
        "userId" is 1
    And
        "title" is "quis ut nam facilis et officia qui"
    And
        header "Via" is "1.1 Vegur"
    And
        header "Server" is "cloudflare"

    if you want to convert json data to java you can prefer Maps, because of it look like json with key-value par.
    {
    "userId": 1,
    "id": 2,
    "title": "quis ut nam facilis et officia qui",
    "completed": false
    }                                                                                   */


    @Test
    public void get08(){


        //1.Step: Set the url
        spec.pathParams("first","todos", "second", 2);

        //2.Step: Set the expected data
        //Expected data in test case was in json format, I converted it to Java Object, Map is a JAva Object
        Map<String,Object> expectedData = new HashMap<>();  //I use HashMap because it is the fastest. HashMap puts the elements in random order.
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("Status Code", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        System.out.println(expectedData);       //Expected data in MAp format


        //3.Step: Send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();     //Actual data in json format


        /*  There are two ways to create an object.
        1)using new keyword ->You are creating object from scratch.
        2)By using another object. Here I use response, because I have response.


        Data which is coming from test case is expected data. Data which is coming from API is actualData
        Response is in json format now. I have to convert it to Java Object, Map. So we have to create a MAp.
        as() method is coming from Gson. as() method get the actual data from response container and convert it HashMap.class  */


        //4.Step: Do assertion
        //By using GSON we are able to convert Json Data which is coming from API to Java Object
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);     //actual Data in Map format

        assertEquals(expectedData.get("userId"), actualData.get("userId"));     //userId is key.
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("Status Code"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));






    }












}
