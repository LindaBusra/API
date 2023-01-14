package api_sm.get_requests02;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class GetRequest10 extends DummyApiBaseUrl {

        /*
http://dummy.restapiexample.com/api/v1/employees
     When a request is sent to the url,
     Test using JSONPATH :
     your status code is 200,
     incoming body,
     5. employee's name is "Airi Satou",
     6th employee's salary is "372000",
     There are 24 employees in total,
     That "Rhona Davidson" is one of the employees
     that there are employees aged "21", "23", "61"


    //postman -->get

        "status": "success",
    "data": [
        {
            "id": 1,
            "employee_name": "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
            "profile_image": ""
        },
*/


    @Test
    public void test10(){

    //set url
    spec.pathParam("first", "employees");

    //set expected data

    //send request and get response
    Response response = given().spec(spec).when().get("/{first}");
    response.prettyPrint();

    //do assertion
    response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
    body("data.id", hasSize(24), "data.employee_name", hasItem("Rhona Davidson"),
    "data.employee_age", hasItems(21,23,61));



    //with JSON

    JsonPath json = response.jsonPath();

    assertEquals("Airi Satou", json.getString("data[4].employee_name")) ;
    assertEquals(372000, json.getInt("data[5].employee_salary")) ;
    assertEquals(24, json.getList("data.id").size());   //2.way for 24 employees
    assertTrue(json.getString("data.employee_name").contains("Rhona Davidson"));    //2nd way to find "Rhona Davidson"

    //2.way for find ages 21,23,61
    List<Integer> ages = Arrays.asList(21,23,61);

    assertTrue(json.getList("data.employee_age").containsAll(ages));

    //or
    List<Integer> ages1 = new ArrayList<>();
    ages1.add(21);
    ages1.add(23);
    ages1.add(61);
    assertTrue(json.getList("data.employee_age").containsAll(ages1));








    }
}
