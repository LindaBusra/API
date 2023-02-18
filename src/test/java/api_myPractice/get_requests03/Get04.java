package api_myPractice.get_requests03;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

public class Get04 extends JsonPlaceHolderBaseUrl {

        /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
    */

@Test
    public void test(){

  //set the url
  spec.pathParams("first", "todos", "second", 123);

   //set expected data


   //send request and get response
   Response response = given().spec(spec).when().get("/{first}/{second}");
   response.prettyPrint();


   //do assertion
    response.then().assertThat().statusCode(200).contentType("application/json")
            .body("userId", equalTo(7),
            "title", equalTo("esse et quis iste est earum aut impedit"),
                    "completed", equalTo(false) );



}


}
