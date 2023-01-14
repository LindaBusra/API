package api_sm.get_requests02;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetRequest01 {


    @Test

    public void test01() {

        //set url
        String url= "https://restful-booker.herokuapp.com/booking";


        //set the expected data



        //send request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();     // I printed the variable I assigned with prettyPrint.

// I get the information from the url and assign it to a container.
// print it like this so I can see it in my terminal. By assigning, I get the information from a variable.

        //Do assertion
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
//status code should always be 200 -->It means the API is working fine.

        //2nd way
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());


        //to print
        System.out.println("Status Line: " + response.statusLine());
        System.out.println("Status Code: "+ response.statusCode());
        System.out.println("Content Type: "+ response.contentType());
        System.out.println("Time for test: " + response.time());

        //to print headers
        System.out.println(response.getHeaders());      //print all headers
        System.out.println(response.getHeader("Date"));
        System.out.println(response.getHeader("Via"));
        System.out.println(response.getHeader("Server"));


    }

}
