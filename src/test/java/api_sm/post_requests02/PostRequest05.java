package api_sm.post_requests02;

import base_urls.DummyApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest05 extends DummyApiBaseUrl {

            /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
 "name":"Ahmet Aksoy",
 "salary":"1000",
 "age":"18",
  }
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
{
 "status": "success",
 "data": {
 “id”:…
 },
 "message": "Successfully! Record has been added."
 }
olduğunu test edin
     */


 /*
 Postman : Post url-->send

response olarak gelen : Yani 4940 id nosu ile yeni data uretildi
 {
    "status": "success",
    "data": {
        "id": 4940
    },
    "message": "Successfully! Record has been added."
}
  */




    @Test
    public void post01() {

     //set url
     spec.pathParam("first", "create");


     //set expected data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("name","Ahmet Aksoy");
        expectedData.put("salary","1000");
        expectedData.put("age","18");


     //send the request, and get the response
     Response response = given().spec(spec).contentType("application/json").body(expectedData).post("/{first}");
     response.prettyPrint();

    //do assertion
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actulData:" + actualData);

        assertEquals(expectedData.get("name"), ( (Map)actualData.get("data")).get("name") );
        assertEquals(expectedData.get("salary"), ( (Map) actualData.get("data")).get("salary") );
        assertEquals(expectedData.get("age"), ( (Map)actualData.get("data") ). get("age") );


    }
}