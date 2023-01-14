package Questions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Question12 {
    /*
       https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
       donen Response’in
            status code'unun 200,
            ve content type'inin ContentType.JSON,
            ve response body'sinde bulunan userId'nin 5,
            ve response body'sinde bulunan title'in "optio dolor molestias sit"
            oldugunu test edin.
        */
    @Test
    public void get01(){

        //set url
        String url = "https://jsonplaceholder.typicode.com/posts/44";

        // Set expected data

        // Send request and get response
        Response response = given().when().get(url);

        //Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("userId",Matchers.equalTo(5)).
                body("title",Matchers.equalTo("optio dolor molestias sit"));
    }
}