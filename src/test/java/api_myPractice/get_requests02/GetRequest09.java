package api_myPractice.get_requests02;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends DummyApiBaseUrl {


 /*
Go to URL http://dummy.restapiexample.com/api/v1/employee/12.
Verify data:
   1) With Matcher CLASS
   2) with JsonPath.


  //with postman
  postman-->GET http://dummy.restapiexample.com/api/v1/employee/12

  {
    "status": "success",
    "data": {
        "id": 12,
        "employee_name": "Quinn Flynn",
        "employee_salary": 342000,
        "employee_age": 22,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}
 */

    @Test
    public void test09(){

     //set url
     spec.pathParams("first", "employee", "second", 12);

     //set expected data


     //send request and get response
     Response response = given().spec(spec).when().get("/{first}/{second}");
//     response.prettyPrint();

     //do assertion


     response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
     body("data.employee_name", equalTo("Quinn Flynn"),
           "data.employee_salary", equalTo(342000),
            "data.employee_age", equalTo(22) );

     //JSON PATH

        JsonPath json = response.jsonPath();

        //get information
        System.out.println(json.getString("data.employee_name"));
        System.out.println(json.getInt("data.employee_age"));
        System.out.println(json.getInt("data.employee_salary"));

        assertEquals("Quinn Flynn",json.getString("data.employee_name"));
        assertEquals(342000,json.getInt("data.employee_salary"));
        assertEquals(22,json.getInt("data.employee_age"));







    }

}
