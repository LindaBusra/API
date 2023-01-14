package delete_requests;


import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponsePojo;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Delete02_2 extends DummyApiBaseUrl {
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

        //2.solution


        //Set the url
        spec.pathParams("first","delete","second",2);


        DummyApiDataPojo dummyApiDataPojo =new DummyApiDataPojo();
        DummyApiResponsePojo expectedData=new DummyApiResponsePojo("success", dummyApiDataPojo,"Successfully! Record has been deleted");

        //Send the delete request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(dummyApiDataPojo).when().delete("/{first}/{second}");
        response.prettyPrint();

        //Validation
        response.then().assertThat().statusCode(200).
                body("status",equalTo("success"),"data",equalTo("2"),"message",equalTo("Successfully! Record has been deleted"));




    }
}