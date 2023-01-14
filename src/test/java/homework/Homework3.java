package homework;

import base_urls.RegresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Homework3 extends RegresInBaseUrl {

    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */


    @Test
    public void homework3(){


        //Set the url
        spec.pathParams("first","users","second", 2);


        //Set the expected data


        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json");


        JsonPath jsonPath = response.jsonPath();   //convert response to JsonPath

        //Hard Assertion
        assertEquals("janet.weaver@reqres.in",jsonPath.getString("data.email"));
        assertEquals("Janet",jsonPath.getString("data.first_name"));
        assertEquals("Weaver",jsonPath.getString("data.last_name"));
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",jsonPath.getString("support.text"));


        //do assertion 2.way
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.email",equalTo("janet.weaver@reqres.in"),
                        "data.first_name",equalTo("Janet"),"data.last_name",equalTo("Weaver"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));



    }
}
