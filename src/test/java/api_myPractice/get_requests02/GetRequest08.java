package api_myPractice.get_requests02;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyApiBaseUrl {

    /*
  located at the url http://dummy.restapiexample.com/api/v1/employees
   1) Let's print the names of all employees on the console
   2) Let's print the name of the 3rd employee on the console
   3) Let's print the names of the first 5 employees on the console
   4) Let's print the last employee's name on the console
*/

   @Test
   public void test08(){

  //set url
  spec.pathParam("first", "employees");


  //set expexted data

  //send request and get response
  Response response = given().spec(spec).when().get("/{first}");
//  response.prettyPrint();


  //do assertion
  response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
  JsonPath json = response.jsonPath();


  //1-Let's print the names of all employees on the console  (1.way)
  System.out.println(json.getList("data.employee_name"));


  //(2.way)
  System.out.println(json.getString("data.employee_name"));


  //2-Let's print the name of the 3rd employee on the console
  System.out.println(json.getString("data[2].employee_name"));


  //3-Let's print the names of the first 5 employees on the console
      for (int i = 0; i < 5; i++) {
         System.out.println(i+1+ ". employee : " + json.getString("data["+i+" ].employee_name"));
      }
   //or

       System.out.println(json.getString("data[0,1,2,3,4].employee_name"));
       System.out.println(json.getString("data.employee_name[0,1,2,3,4]"));


  //4) Let's print the last employee's name on the console

       System.out.println( json.getString("data[-1].employee_name") );
       System.out.println( json.getString("data.employee_name[-1]") );






   }
}
