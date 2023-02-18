package api_review.get_http_request_methods;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;  //I want all static methods from restassured library

public class Get01 {

    /*
    In API testing we will use Gherkin Language. It has some simple structures.
    In Gherkin Language we use some keywords. Given, When, Then, And
    Given: It declares pre-requisites
    When: It is used  to declare actions
    Then: It is for outputs
    And: It is used for multiply Given, When, Then       */


    /*  Test Case - get01

    Given
        https://restful-booker.herokuapp.com/booking/3
    When
        User send a GET Request to the url
    Then
        HTTP Status code should be 200
    And
        Content type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK            */


    /*For automate a test case you have to should a test method. "Test" annotation is from Junit.
    JUnit is used for testing. When you create a functionality do you have to execute it. it works as expected or not.
    We dont need use main method. @Test annotation can run this method.  */

    //We may use IntellijIDE like Postman

    @Test
    public void get01(){

        //1-Set the url (endpoint)
        String url = "https://restful-booker.herokuapp.com/booking/3";


        //2-Set the expected data


        //3-Type automation script to send GET request   (same result from Postman)
        Response response = given().when().get(url);  //That codes send the request to the API which is in this url, and get response
        response.prettyPrint();  //to create response object we have to use prettyPrint()

        //4-Do assertion (verify)
        //If you have multiple errors on in your functionality, execution will be stopped in the first error. Next codes will not be executed.
        //You will not get any error messages about the second, third, etc. errors.
        //If the execution stopped after the first error, it is called "Hard Assertion". It is very strict, because of that it calls Hard Assertion.


        //Do we have Soft Assertion? Yes.
        //In Soft-Assertion (Verification) does not stop execution after errors, all codes will be executed and you will get report (error messages) for all errors.
        response.then().assertThat().statusCode(200).contentType("application/json")   //then() is for result
                .statusLine("HTTP/1.1 200 OK");


        //How to print status code, status line, headers, content type... on the console?
        System.out.println("Status code is " + response.getStatusCode());
        System.out.println("Content type is " + response.getContentType());
        System.out.println("Status line is " + response.getStatusLine());
        System.out.println("Execution Time is " + response.getTime());
        System.out.println("Headers are \n" + response.getHeaders());
        System.out.println("Via is " + response.getHeader("Via"));   //get a specific header
        System.out.println("Server header is " + response.getHeader("Server"));   //get a specific header


    }
}






















