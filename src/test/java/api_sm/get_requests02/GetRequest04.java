package api_sm.get_requests02;

import base_urls.DummyApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest04 extends DummyApiBaseUrl {

    /*
    To the url http://dummy.restapiexample.com/api/v1/employees
    When I send the GET request, the response
    The status code is 200 and the content type is "application/json"
    and the number of employees is 24
    and one of the employees "Tiger Nixon"
    and test that there is one of the values 61,63,66 in the coming years.

 */


    @Test
    public void test04() {

         //set the url
        spec.pathParam("first", "employees");


        //set the expected data


        //send request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();                 //response.then().log().all();


        response.then().assertThat().statusCode(200).contentType("application/json").
            body("data.id", hasSize(24), "data.employee_name", hasItem("Tiger Nixon"),
                "data.employee_age", hasItems(61,63,66));






    }





}
