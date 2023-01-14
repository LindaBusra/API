package delete_requests;


import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import test_data.DummyTestData;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class Delete02_1 extends DummyApiBaseUrl {


    /*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
   HTTP Request Method: DELETE Request
   Test Case: Type by using Gherkin Language
   Given
   https://dummy.restapiexample.com/api/v1/delete/2
   When
   HTTP Request Method: DELETE Request
   Then
   Status code is 200
   And
   "status" is "success"
   And
   "data" is "2"
   And
   "message" is "Successfully! Record has been deleted"
   Assert:     i) Status code is 200
           ii) "status" is "success"
          iii) "data" is "2"
           iv) "message" is "Successfully! Record has been deleted"
 */

/*
http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"status": "success",
"data": "2",
"message": "Successfully! Record has been deleted"
}
*/


    @Test
    public void  delete01(){

        //1.solution

        //Set the url
        spec.pathParams("first",  "delete", "second", "2");

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("data", 2);
        expectedData.put("status", "success");
        expectedData.put("message", "Successfully! Record has been deleted");

        //Send the delete request and get the response
        Response response = given().spec(spec).when().delete("/{first}/{second}");

        //Validation
        response.then().assertThat().statusCode(200);

        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("data"), actualData.get("data"));
        assertEquals(expectedData.get("status"), actualData.get("status"));
        assertEquals(expectedData.get("message"), actualData.get("message"));



    }


    @Test
    public void delete02(){

        //url
        spec.pathParams("parametre1","delete",
                "parametre2",2);

        DummyTestData testData=new DummyTestData();
        JSONObject expectedData= testData.setUpDeleteExpectedData();

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec).
                auth().
                basic("admin","password123").
                when().
                delete("/{parametre1}/{parametre2}");

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                body("status", equalTo(expectedData.getString("status")),
                        "data",equalTo(expectedData.getString("data")),
                        "message",equalTo(expectedData.getString("message")));






    }

}