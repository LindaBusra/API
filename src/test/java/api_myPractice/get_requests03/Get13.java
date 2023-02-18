package api_myPractice.get_requests03;


import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponsePojo;
import static io.restassured.RestAssured.given;

import base_urls.DummyApiBaseUrl;

public class Get13 extends DummyApiBaseUrl {

    /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                        Status code is 200
                             {
                              "status": "success",
                              "data": {
                                  "id": 1,
                                  "employee_name": "Tiger Nixon",
                                  "employee_salary": 320800,
                                  "employee_age": 61,
                                  "profile_image": ""
                              },
                              "message": "Successfully! Record has been fetched."
                             }
*/
    @Test
    public void test() {

        //set url
        spec.pathParams("first","employee", "second", 1);

        //Set the expected data
        DummyApiDataPojo expectedData = new DummyApiDataPojo("Tiger Nixon", 320800, 61, "");


        //Get the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Validation
        DummyApiResponsePojo actualData = response.as(DummyApiResponsePojo.class);
        System.out.println("ActualData: " + actualData);
        System.out.println("ExpectedData: " + expectedData);









    }
}