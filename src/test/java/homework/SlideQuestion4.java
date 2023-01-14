package homework;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class SlideQuestion4 extends DummyApiBaseUrl {


    /*
When I send a GET request to REST API URL http://dummy.restapiexample.com/api/v1/employee/1
And Accept type is “application/JSON”
Then HTTP Status Code should be 200
And employee name should be “Tiger Nixon”
And employee salary should be “320800”
And employee age should be “61”
     */


    @Test

    public void Question4(){

        //Set the url
        spec.pathParams("first", "employee","second" , 1);


        //Set the expected data


        //Send the request and get the response
        Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do assertion
        response.then().
                assertThat().
                statusCode(200).
                body("employee_name", equalTo("Tiger Nixon"),
                        "employee_salary",equalTo(320800),
                        "employee_age",equalTo(61));

    }
}
