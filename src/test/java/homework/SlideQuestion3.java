package homework;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SlideQuestion3 extends DummyApiBaseUrl {

/*
    When I send a GET request to REST API URL http://dummy.restapiexample.com/api/v1/employees
    And Accept type is “application/JSON”
    Then HTTP Status Code should be 200
    And “Garrett Winters” should be displayed among data     */


    @Test

    public void Question3(){


        //Set the url
        spec.pathParams("first",  "employees").
        queryParam("data.employee_name","Garrett Winters");


        //Set the expected data


        //Send the request and get the response
        Response response=given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();


        //Do Assertion
        response.then().assertThat().statusCode(200)
                .body("data.employee_name", hasItems("Garrett Winters"));







    }




}
