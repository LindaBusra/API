package Questions;


import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Question11 extends DummyApiBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
    {
            “id”:”11”
             "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
        } gibi olduğunu test edin.
*/


 /*
    Given
        http://dummy.restapiexample.com/api/v1/employees
    When
        User send Get Request
    Then
        Status code is 200
   And
        5. worker's name is "Airi Satou"
   And
        employee number is 24
   And
        The salary of the last second employee is 106450
  And
        Among the employees there is employees whose ages are 40,21,19
  And
        11.employee enformasjon is

{
            “id”:”11”
             "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
        }




     */

    @Test
    public void get10() {

        //Set the Url
        spec.pathParams("f",  "employees");

        //Set the expected data

        //Set the request and Set the response
        Response response = given().spec(spec).when().get("/{f}");
        //response.prettyPrint();

        //Validation
        JsonPath json = response.jsonPath();
        Set<Integer> expected = new HashSet<>();
        expected.add(40);
        expected.add(21);
        expected.add(19);


        System.out.println((json.getString("data.employee_name")));
        System.out.println(json.getList("data.employee_name").size());
        System.out.println(json.getInt("data.employee_salary[-2]"));
        System.out.println(json.getList("data.employee_age").containsAll(expected));

        assertEquals("Airi Satou", json.getString("data.employee_name[4]"));   //or
        //List<String> name = response.jsonPath().getList("data.findAll{it.id==5}.employee.name");
        assertEquals(24, json.getList("data.employee_name").size());
        assertEquals(106450, json.getInt("data.employee_salary[-2]"));


        assertEquals(11, json.getInt("data.id[10]"));
        assertEquals("Jena Gaines", json.getString("data.employee_name[10]"));
        assertEquals(90560, json.getInt("data.employee_salary[10]"));
        assertEquals(30, json.getInt("data.employee_age[10]"));
        assertEquals("", json.getString("data.profile_image[10]"));


    }

}
