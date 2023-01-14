package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

    public class GetRequest01 {

        @Test
        public void test01(){

            String url= "https://restful-booker.herokuapp.com/booking";

            Response response = given().when().get(url); // I put url into response container.
            response.prettyPrint();  // to print it


            System.out.println("Status Code: "+   response.statusCode());  // output: integer
            System.out.println("Content Type: "+  response.contentType());
            System.out.println("Status Line: " + response.statusLine());
            System.out.println("Response Time: " +  response.time());


            Assert.assertEquals(200,response.statusCode());
            Assert.assertEquals("application/json; charset=utf-8",response.contentType());
            Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());


            response.
                    then().
                    assertThat().
                    statusCode(200).
                    contentType("application/json; charset=utf-8").
                    statusLine("HTTP/1.1 200 OK");




        }
    }