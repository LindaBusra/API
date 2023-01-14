package Questions;


import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Question8 extends DummyApiBaseUrl {
      /*
http://dummy.restapiexample.com/api/v1/employees
         When a request is sent to the url,
         your status code is 200,
         incoming body,
         5. employee's name is "Airi Satou",
         6th employee's salary is "372000",
         There are 24 employees in total,
         That "Rhona Davidson" is one of the employees
         Test there are employees aged "21", "23", "61"
     */


    @Test
    public void get06() {

        //Set the url
        spec.pathParams("first", "api", "second", "v1", "third", "employees");

        //Set the expected data
        //Send the Get Request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        //response.prettyPrint();


        response.then().assertThat().
                statusCode(200).
                body("data.id", hasSize(24),
                        "data.employee_name", hasItem("Rhona Davidson"),
                        "data.employee_age", hasItems(21, 23, 61));


        //validation
        JsonPath json = response.jsonPath();

        //to print on the console
        //System.out.println(json.getString("data.employee_name[4]"));
        //System.out.println(json.getInt("data.employee_salary[5]"));
        //System.out.println(json.getList("data.id").size());

        assertEquals("Airi Satou", json.getString("data.employee_name[4]"));
        assertEquals(372000, json.getInt("data.employee_salary[5]"));
        assertEquals(24, json.getList("data.id").size());


    }
}