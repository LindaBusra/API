package api_sm.get_requests02;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class GetRequest05 extends JsonPlaceHolderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/todos/123 url’ine
   accept type’i “application/json” olan GET request’i yolladigimda
   gelen response’un
	status kodunun 200
   	ve content type’inin “application/json”
	ve Headers’daki “Server” in “cloudflare”,
	Pragmanin "no-cache",
	ve response body’deki “userId”’nin 7
	ve “title” in “esse et quis iste est earum aut impedit”
	ve “completed” bolumunun false oldugunu test edin
     */


    @Test
    public void test05(){

    //set url
    spec.pathParams("first", "todos", "second", 123);

    //set expected data


   //send request and get response
   Response response = given().spec(spec).when().get("/{first}/{second}");
   response.prettyPrint();

   //do assertion (use equalTo -->import equalTo() method as static)
   response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
   header("Server", equalTo("cloudflare")).
   header("Pragma", equalTo("no-cache")).
   body("userId", equalTo(7), "title", equalTo("esse et quis iste est earum aut impedit"),
           "completed", equalTo(false));


   //or use Matcher

   response.then().assertThat().contentType(ContentType.JSON).statusCode(200).
           headers("Server", Matchers.equalTo("cloudflare"), "Pragma",Matchers.equalTo("no-cache")).
                //header("Pragma",Matchers.equalTo("no-cache")).
             body("userId",Matchers.equalTo(7),
             "title",Matchers.equalTo("esse et quis iste est earum aut impedit"),
             "completed",Matchers.equalTo(false));

   //or
   response.then().assertThat().contentType(ContentType.JSON).statusCode(200).
           headers("Server", Matchers.equalTo("cloudflare"), "Pragma",Matchers.equalTo("no-cache"));

   response.then().assertThat().body("userId",Matchers.equalTo(7),
                        "title",Matchers.equalTo("esse et quis iste est earum aut impedit"),
                        "completed",Matchers.equalTo(false));


    }


}
