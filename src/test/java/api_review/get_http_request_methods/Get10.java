package api_review.get_http_request_methods;

import Utils.JsonUtils;
import api_review.pojos.GorestDataPojo;
import api_review.pojos.GorestPojo;
import base_urls.GorestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GorestApiBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v1/users/459514    //sometimes version number is in base url.
    When
        User sent GET request to the URL
    Then
        Status code should be 200
    And
        Response body should be like

{
    "meta": null,
    "data": {
        "id": 459977,
        "name": "Arjun Somayaji",
        "email": "somayaji_arjun@grady.info",
        "gender": "male",
        "status": "inactive"
    }
}
*/

   @Test
    public void  get10(){

       //1.Step: Set the url
       spec.pathParams("first", "users", "second", 459977 );

       //2.Step: Set the expected data
       GorestDataPojo dataPojo = new GorestDataPojo("Arjun Somayaji", "somayaji_arjun@grady.info", "male", "inactive");
       System.out.println(dataPojo);

       GorestPojo expectedDataPojo = new GorestPojo(null, dataPojo);
       System.out.println(expectedDataPojo);

       //3.Step: Send request and get the response
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       GorestPojo actualDataPojo = JsonUtils.convertJsonToJavaObject(response.asString(), GorestPojo.class);
       System.out.println(actualDataPojo);

       //4.Step: Do assertion
      assertEquals(200, response.getStatusCode());
       assertEquals(expectedDataPojo.getMeta(), actualDataPojo.getMeta());
       assertEquals(expectedDataPojo.getData().getName(), actualDataPojo.getData().getName());
       assertEquals(expectedDataPojo.getData().getEmail(), actualDataPojo.getData().getEmail());
       assertEquals(expectedDataPojo.getData().getStatus(), actualDataPojo.getData().getStatus());
       assertEquals(expectedDataPojo.getData().getGender(), actualDataPojo.getData().getGender());

       //when you push your code you should be clear all sout and prettyPrint. These are just for checking.
   }
}
