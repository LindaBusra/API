package api_sm.get_requests03;



import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get06 extends DummyApiBaseUrl {

/*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiralim
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
     response.then().assertThat().statusCode(200);

     //Let's print the names of all employees on the console
     System.out.println("Employee name list :" + json.getList("data.employee_name"));

    //Let's print the name of the 3rd employee on the console
     System.out.println("3rd employee name : " + json.getString("data[2].employee_name"));

     //Let's print the names of the first 5 employees on the console
     System.out.println("First 5 employees name : " + json.getString("data[0,1,2,3,4].employee_name"));

     //Let's print the last employee's name on the console
     System.out.println("3rd employee name : " + json.getString("data[-1].employee_name"));


     assertEquals(200, response.getStatusCode());
     assertEquals("Ashton Cox", json.getString("data[2].employee_name"));
     assertEquals("Doris Wilder", json.getString("data[-1].employee_name"));









    }
}