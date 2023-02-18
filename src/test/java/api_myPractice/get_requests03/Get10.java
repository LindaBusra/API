package api_myPractice.get_requests03;



import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get10 extends DummyApiBaseUrl {

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

    @Test
    public void get06() {

      //set url
      spec.pathParam("first", "employees");


      //set expected data


     //send request and get response
     Response response = given().spec(spec).when().get("/{first}");



    //do assertion

     JsonPath json = response.jsonPath();


        //for age part
        Set<Integer> expectedAges = new HashSet<>();

        expectedAges.add(40);
        expectedAges.add(21);
        expectedAges.add(19);
        assertTrue( json.getList("data.employee_age").containsAll(expectedAges))  ;

        //or
//        response.then().assertThat().body("data.employee_age" , hasItems(40,21,19) );

     assertEquals(200, response.getStatusCode());
     assertEquals("Airi Satou", json.getString("data[4].employee_name"));
     assertEquals(106450, json.getInt("data[-2].employee_salary"));
     assertEquals(24, json.getList("data.id").size());

     assertEquals(11, json.getInt("data[10].id"));
     assertEquals("Jena Gaines", json.getString("data[10].employee_name"));
     assertEquals(90560, json.getInt("data[10].employee_salary"));
     assertEquals(30, json.getInt("data[10].employee_age"));
     assertEquals("", json.getString("data[10].profile_image"));



    //to print -just example
        System.out.println((json.getList("data.employee_name")));
        System.out.println(json.getList("data.employee_name").size());
        System.out.println(json.getInt("data.employee_salary[-2]"));







    }
}