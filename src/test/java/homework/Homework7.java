package homework;

import base_urls.RegresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework7 extends RegresInBaseUrl {


    /*
      Given
          1) https://reqres.in/api/users
          2) {
              "name": "morpheus",
              "job": "leader"
              }
      When
          I send POST Request to the Url
      Then
          Status code is 201
          And response body should be like {
                                              "name": "morpheus",
                                              "job": "leader",
                                              "id": "496",
                                              "createdAt": "2022-10-04T15:18:56.372Z"
                                            }
   */
    @Test
    public void post01(){

        //Set the url
        spec.pathParam("first","users");

        //Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("name","morpheus");
        expectedData.put("job","leader");
        expectedData.put("id",496);
        expectedData.put("createdAt","2022-10-04T15:18:56.372Z");

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.then().assertThat().statusCode(201);
        response.prettyPrint();


        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals("2022-10-04T15:18:56.372Z",expectedData.get("createdAt"));

        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));
        assertEquals(496,actualData.get("id"));

    }


    @Test
    public void post02(){


        spec.pathParam("first", "users");


        RegresTestData reqresTestData = new RegresTestData();
        Map<String,String > expectedData = reqresTestData.regresTestData("morpheus","leader");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String,String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(expectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(expectedData.get("job"),actualData.get("job"));

    }





    }
















