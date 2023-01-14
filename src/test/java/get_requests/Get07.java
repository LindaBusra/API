package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {


    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */


    @Test
    public void get07() {

        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        //Do Assertion
        //1)Status code is 200

        assertEquals(200,response.statusCode());


        //2)Print all ids greater than 190 on the console
        //Assert that there are 10 ids greater than 190

        //1st way
        JsonPath jsonPath = response.jsonPath();
//        List<Integer> ids = jsonPath.getList("id");
//        List<Integer> idsGreaterThan190 = new ArrayList<>();
//
//        System.out.println("ids = " + ids);
//
//        for(int w: ids) {
//
//            if(w>190) {
//                idsGreaterThan190.add(w);
//            }
//        }
//
//        System.out.println("idsGreaterThan190" + idsGreaterThan190);
//
//        assertEquals(10, idsGreaterThan190.size());


        //2nd Way: Recommended
        List<Integer> idsGreaterThan190Groovy = jsonPath.getList("findAll{it.id>190}.id");  //Groavy Language--findAll{it} filter ( 'it' is like 't->' in lambda
        System.out.println("idsGreaterThan190Groovy = " + idsGreaterThan190Groovy);
        assertEquals(10, idsGreaterThan190Groovy.size());


        //3)Print all userIds whose ids are less than 5 on the console
        //Assert that the number of userIds whose ids are less than 5 is 4

        List<Integer> userIds = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("userIds = " + userIds);
        assertEquals(4, userIds.size());


        //4)Print all titles whose ids are less than 5
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5

        List<String> titles = jsonPath.getList("findAll{it.id<5}.title");  //Groavy Language--findAll{it} filter ( 'it' is like 't->' in lambda
        System.out.println("titles = " + titles);

        //1.st way
        assertTrue("'delectus aut autem' does not exist",  titles.contains("delectus aut autem"));

        //2.nd way
        assertTrue("'delectus aut autem' does not exist",  titles.stream().anyMatch(t->t.equals("delectus aut autem")));

        //3rd. way
        assertTrue(titles.contains("delectus aut autem"));

        //There are many API Test type, unit, integration ....., we are doing function test
    }
}