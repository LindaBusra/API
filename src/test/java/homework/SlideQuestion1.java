package homework;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;



public class SlideQuestion1 {

/*
When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking
And Accept type is “application/JSON”
Then HTTP Status Code should be 200
And Response format type should be "application/JSON"
     */

    @Test
    public void Question1(){

        //Set the url
        String url = "https://restful-booker.herokuapp.com/booking";


        //Set the expected data

        //Send the request and get the response
        Response response = given().when().contentType(ContentType.JSON).get(url);
        response.prettyPrint();


        //Do Assertion
        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);
    }
}
