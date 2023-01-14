package homework;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SlideQuestion2 extends HerOkuAppBaseUrl  {

/*
When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/5
And Accept type is “application/JSON”
Then HTTP Status Code should be 200
And first name should be “Der”
And total price should be 11111        */


   @Test

   public void Question2(){

      //Set the url
      spec.pathParams("first","booking","second",5);

      //Set the expected data


      //Send the request and get the response
      Response response = given().spec(spec).when().accept("application/JSON").get("/{first}/{second}");
      response.prettyPrint();

      //Do assertion

      response.then().
              assertThat().
              statusCode(200).
              body("firstname", equalTo("Der"),
                      "totalprice",equalTo(11111));




   }


}
