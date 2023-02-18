package api_myPractice.get_requests02;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 {

    @Test
    public void test02(){

        //set url
        String url = "https://reqres.in/api/users";

        /* Postmann:

        {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }

         */

        //set the expected data


        //send request and get the response
        Response response = given().when().get(url);

        response.prettyPrint(); // Gets the body (part of the response)
        response.peek();        //gets all response with some informations about headers
        response.then().log().all();    //gets all response


        //Headers
        response.then().assertThat().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType("application/json; charset=utf-8");

        //Body
        response.then().body(
                "data[0].first_name", equalTo("George"),
                "data[0].last_name", equalTo("Bluth"));


        //Body
        response.then().body("data[1].id", equalTo(2),
                "data[1].email", equalTo("janet.weaver@reqres.in"),
                "data[1].first_name", equalTo("Janet"),
                "data[1].last_name", equalTo("Weaver"),
                "data[1].avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));


        response.then().body("data[2].email", equalTo("emma.wong@reqres.in"),
                "data[2].first_name", equalTo("Emma"));




    }
}
