package Questions;



import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Question7 extends DummyApiBaseUrl {

/*
    http://dummy.restapiexample.com/api/v1/employees url
    In the given url
    1) Let's print the names of all employees on the console
    2) Let's print the name of the 3rd employee on the console
    3) Let's print the names of the first 5 employees on the console
    4) Let's print the last employee's name on the console
 */

    @Test
    public void get06() {

        //Set the url
        spec.pathParams("first", "api", "second", "v1", "third", "employees");

        //Set the expected data


        //Send the Get Request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        //response.prettyPrint();

        //validation
        JsonPath json = response.jsonPath();
        System.out.println(json.getList("data.employee_name"));  //1
        System.out.println(json.getString("data.employee_name[2]")); //2
        System.out.println(json.getString("data.employee_name[0,1,2,3,4]")); //3
        System.out.println(json.getString("data.employee_name[-1]"));       //4


        assertEquals(200, response.getStatusCode());
        assertEquals("Ashton Cox", json.getString("data.employee_name[2]"));
        assertEquals("Doris Wilder", json.getString("data.employee_name[-1]"));




    }


}