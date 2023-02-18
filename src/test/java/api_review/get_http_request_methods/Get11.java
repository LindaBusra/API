package api_review.get_http_request_methods;

import base_urls.GorestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GorestApiBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
       The number of users should be 10
    And
       We have at least one "active" status     //The number of active status is less than 5
    And
       "Aruna Devar", "Dhanu Patel", "Bhushan Namboothiri" are among the users
    And
        The female users are less than male users


        //Notes:The variable are changing constantly !!!!
     */


    @Test
    public void get11(){

        //1-Set the url
        spec.pathParam("first", "users");

        //2-Set the expected data

        //3-Send the GET request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        //4-Do assertion
        //1.way
        response.then().assertThat().statusCode(200)
                .body("meta.pagination.limit", equalTo(10),
                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10),
                "data.status", hasItem("active"),
                        "data.name", hasItems("Aruna Devar", "Dhanu Patel", "Bhushan Namboothiri"));


        //I will create JsonPath, by help of it I will navigate inside the Json data
        JsonPath json = response.jsonPath();

        //2.way: Use JsonPath to assert
        assertEquals(200, response.getStatusCode());
        assertEquals(10, json.getInt("meta.pagination.limit"));
        assertEquals("https://gorest.co.in/public/v1/users?page=1", json.getString("meta.pagination.links.current"));
        assertEquals(10, json.getList("data.id").size());
        assertTrue(json.getList("data.status").contains("active"));

        List<String> acceptedNames = Arrays.asList("Aruna Devar", "Dhanu Patel", "Bhushan Namboothiri");
        assertTrue(json.getList("data.name").containsAll( acceptedNames));   //containsAll acccepted collections


        //The number of females are more or equal to than the number of males.
        //1.way: Use foor loop
        List<String> genderList = json.getList("data.gender");
        System.out.println(genderList);

        int femaleCounter = 0;
        for(String w : genderList) {

            if(w.equals("female")) {
                femaleCounter++;
            }
        }

        assertTrue(femaleCounter>=genderList.size()-femaleCounter);

        //1.way: Use Groovy
        List<String> femaleList = json.getList("data.findAll{it.gender='female'}.gender");
        System.out.println("listOfFemale:" + femaleList.size());

        List<String> maleList = json.getList("data.findAll{it.gender='male'}.gender");
        System.out.println("listOfMale:" + maleList.size());
        assertTrue(femaleList.size()>= maleList.size());


        //The number of active status is less than 5
        //1.way: Used loop
        List<String> statusList = json.getList("data.status");
        int statusCounter = 0;
        for(String w : genderList) {

            if(w.equals("active")) {
                statusCounter++;
            }
        }

        assertTrue(statusCounter<5);


        //2.way: Use groovy
        List<String> listOfActiveStatus = json.getList("data.findAll{it.status='active'}.status");
        System.out.println(listOfActiveStatus);
        assertTrue(listOfActiveStatus.size()<5);


    }
}
