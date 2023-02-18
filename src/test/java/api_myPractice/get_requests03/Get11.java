package api_myPractice.get_requests03;


import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get11 extends DummyApiBaseUrl {

       /*
         http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        Status kodun 200 olduğunu,
        En yüksek maaşın 725000 olduğunu,
        En küçük yaşın 19 olduğunu,
        İkinci en yüksek maaşın 675000
        olduğunu test edin.
     */


    /*
    postman-->get url
    {
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
    public void get11() {

    //set url
    spec.pathParam("first", "employees");


    //set expected data


    //send request and get response
    Response response = given().spec(spec).when().get("/{first}");

    //do assertion

        JsonPath json = response.jsonPath();

        assertEquals(200, response.getStatusCode());

        //Test the highest salary is 725000,
        List<Integer> salaryList = json.getList("data.employee_salary");
        Collections.sort(salaryList);

        assertEquals((Integer) 725000, salaryList.get(salaryList.size()-1));


    //Test the youngest age is 19,

        List<Integer> ageList = json.getList("data.employee_age");
        Collections.sort(ageList);

        assertEquals((Integer) 19, ageList.get(0));


    //Test the second highest salary is 675000

        assertEquals((Integer) 675000, salaryList.get(salaryList.size()-2));









    }
}