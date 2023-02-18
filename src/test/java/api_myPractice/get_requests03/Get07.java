package api_myPractice.get_requests03;



import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.junit.Assert.assertEquals;

public class Get07 extends DummyApiBaseUrl {

      /*
         http://dummy.restapiexample.com/api/v1/employees
        url ine bir istek gönderildiğinde,
        status kodun 200,
        gelen body de,
        5. çalışanın isminin "Airi Satou" olduğunu ,
        6. çalışanın maaşının "372000" olduğunu ,
        Toplam 24 tane çalışan olduğunu,
        "Rhona Davidson" ın employee lerden biri olduğunu
        "21", "23", "61" yaşlarında employeeler olduğunu test edin
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
     response.then().assertThat().statusCode(200)
        .body("data.employee_name", hasItem("Rhona Davidson"),
        "data.employee_age" , hasItems(21,23,61) );


     assertEquals(200, response.getStatusCode());
     assertEquals("Airi Satou", json.getString("data[4].employee_name"));
     assertEquals(372000, json.getInt("data[5].employee_salary"));
     assertEquals(24, json.getList("data.id").size());










    }
}