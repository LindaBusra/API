package api_review.get_http_request_methods;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        I send a GET request to the url
    And
        Accept type is "application/json"
    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
        There should be 200 todos
    And
        "quis ut nam facilis et officia qui" should be one of the todos
    And
        2,7 and 9 should be among the userIds.
     */


    @Test
    public void get04(){

        //1-Set the url
        spec.pathParam("first", "todos");

        //2-Set the expected data

        //3-Send the request and get the response
        Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        //4-Do the assertion
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasSize(200))    //when you type id, it will give you a list, to check number of data
                .body("title", hasItem("quis ut nam facilis et officia qui"))   //when you write title, java puts all title in a list, if response body has specific data
                .body("userId", hasItems(2,7,9));  //if response body has multiple data

    }
}
