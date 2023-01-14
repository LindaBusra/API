

package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*; //all from library
import static org.testng.AssertJUnit.assertEquals;

public class Get01 {
    /*
    1) Postman is used for manual API testing.
    2) We use Rest-Assured Library for API Automation Testing.
    3) To type automation script follow the given steps:
        a) Understand the requirement
        b) Type test cases
            To type test cases We use 'Gherkin Language'
            The keywords are    x) Given: It is for pre-conditions
                                y) When: It is for actions
                                z) Then: It is for outputs
                                t) And: It is for multiple given, when and then.

        c) Start to type Automation Script
            i) Set the URL
            ii) Set the expected Data(Post-Put-Patch)
            iii) Type code to send the Request -->Send the request and get the response
            iv) Do Assertion (to verify)
     */

      /*
        Given
            https://restful-booker.herokuapp.com/booking/10  -->It is for pre-conditions (We try it i postman GET URL)
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */



   @Test  //Test org junit :we select this

    public void get01(){


        //Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/10";


        //Set the expected data


        //Send the request and get the response

        Response response=given().when().get(url);  //when()-->action part, I will send request, get response
        response.prettyPrint();  //pretty like sout   //all code is in response.   //header:the data of data

        //Do Assertion -->to be sure, if it is working expected or not
        //if it is no 200, or json, it is not working expected. and I will be red.


        // HTTP Status Code should be 200
        //Content Type should be JSON
        //Status Line should be HTTP/1.1 200 OK
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");


       //2.way
       assertEquals(200, response.statusCode());
       assertEquals("application/json; charset=utf-8", response.contentType());
       assertEquals("HTTP/1.1 200 OK", response.statusLine());
;


        //How to print "Status Code" on the console
        System.out.println("Status Code: "+response.statusCode());

        //How to print "Content Type" on the console
        System.out.println("Content Type: "+response.contentType());

        //How to print "Status Line" on the console
        System.out.println("Status Line: "+response.statusLine());

        //How to print "Header" on the console
        System.out.println(response.getHeader("Server"));

        System.out.println("================");

        //How to print a specific part of Headers on the console
        System.out.println(response.getHeader("Connection"));  //just for specific one  output:Connection:keep-alive

       System.out.println(response.getHeader("Server"));  //just for specific one  output:Server:cowboy


       System.out.println("================");

       //How to print "Headers" on the console
       System.out.println(response.getHeaders());       //if I will see all the headers

        //How to print "time" on the console
        System.out.println(response.getTime());   //1543 ms






    }
}
