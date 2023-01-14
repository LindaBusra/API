package api_sm.get_requests03;


import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.testng.AssertJUnit.assertEquals;

public class Get17 extends DummyApiBaseUrl {


         /*
    Given https://dummy.restapiexample.com/api/v1/employee/7
    When I send a Get request to the Url
    Then HTTP Status Code should be 200
    And Response format should be JSon
    And Herrod Chandler should be the employee name
    And "employee_salary":137500 should be the expected salary
    And "id":7 should be expected id
     */

    @Test
    public void get07() {

        //set url
        spec.pathParams("first", "employee", "second", 7);


        //set the expected data


        //send request and get request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        response.then().assertThat()
                .contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();

        System.out.println(json.getString("data.employee_name"));
        System.out.println(json.getString("data"));
        System.out.println(json.getString("data.employee_salary"));

        //or we can get like salary like that:
        int salary = json.getInt("data.employee_salary");
        System.out.println(salary);

        //Hard assertion
        assertEquals(200, response.getStatusCode());
        assertEquals("Herrod Chandler", json.getString("data.employee_name"));
        assertEquals(137500, json.getInt("data.employee_salary"));
        assertEquals(7, json.getInt("data.id"));


        //Soft Assertion
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(json.getInt("data.employee_salary"), 137500);
        softAssert.assertEquals(json.getString("data.employee_name"), "Herrod Chandler");
        softAssert.assertEquals(json.getInt("data.id"), 7);

        softAssert.assertAll();
    }
}