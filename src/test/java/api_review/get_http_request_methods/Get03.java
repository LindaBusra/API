package api_review.get_http_request_methods;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.*;

public class Get03 extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos/23
    When
        User send GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Response should be "application/json"
    And
        "title" is "et itaque necessitatibus maxime molestiae qui quas velit"
    And
        "completed" is false
    And
        "userId" is 2
     */

    @Test
    public void get03(){

        //1-Set the url
        spec.pathParams("first", "todos", "second", 23);


        //2-Set the expected data


        //3-Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //Note: Instead of using "application/json" you can use ContentType.JSON    (ContentType is enum)

        //1.Way
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(2));


        //2.Way - no repetition
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));

    }


}
