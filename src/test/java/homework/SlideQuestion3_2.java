package homework;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsIterableContaining.hasItems;

public class SlideQuestion3_2 extends DummyApiBaseUrl {

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


        List<String> names =  response.jsonPath().getList("data.employee_name");
        names.stream().filter(t-> t.equals("Garrett Winters")).forEach(System.out::println);

        System.out.println(response.jsonPath().getList("data.findAll{it.employee_name=='Garrett Winters'}.id"));
        System.out.println(response.jsonPath().getList("data.findAll{it.id==2}"));




    }




}
