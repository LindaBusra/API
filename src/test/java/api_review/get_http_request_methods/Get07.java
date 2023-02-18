package api_review.get_http_request_methods;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class Get07 extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        User send a request to the URL
    Then
        1)HTTP Status Code should be 200
        2)Print all ids greater than 190 on the console
            Assert that there are 10 ids greater than 190
        3)Print all userIds less than 5 on the console
            Assert that maximum userId less than 5 is 4
        4)Print all titles whose ids are less than 5
            Assert that "delectus aut autem"" is one of the titles whose id is less than 5.

     */

    @Test
    public void get07(){

        //Set the url
        spec.pathParams("first", "todos");
        //access modifier of spec is protected, to be able to use protected one we have to extend parent class, baseurl.

        //Set the expected data

        //Send request and get the response
        Response response = given().spec(spec).when().get("/{first}");  //response should be store in response container
        response.prettyPrint();

        //Do assertion
        response.then().assertThat().statusCode(200);

        //Print all ids greater than 190 on the console
        JsonPath jsonPath = response.jsonPath();  //converting response object to JsonPath, I created a jsonPath object.
        //jsonPath() is from JasonPath class and it is for converting response to the JsonPath. Now jsonPath object contains everything which response object have.

        List<Integer> idList = jsonPath.getList("findAll{it.id>190}.id"); //That syntax is from Grovy Language. GL is working under java.
        //it.id-->it fetch all ids.   this.name-->this : from the class which you are in. (in java)
        //it.  --> from the Json Data which we are working in. (in Grovy)
        //in first part (findAll{it.id>190} ) gives you id whose greater than 190, but with whole keys. id, userId, title, completed.
        //so we add .id   for select just id
        System.out.println(idList);

        //Assert that there are 10 ids greater than 190
        assertEquals("Id list does not have expected size", idList.size(), 10);

        //Print all userIds less than 5 on the console
        List<Integer> userIdList = jsonPath.getList("findAll{it.userId<5}.userId");
        System.out.println(userIdList);

        //Assert that maximum userId less than 5 is 4
        Collections.sort(userIdList);   //Elements are sorted in ascending order
        assertEquals((Integer)4, userIdList.get(userIdList.size()-1));  //it complains because of assertEquals works with Object, Object. When we put 4, it accepts is as object.
        //We need to do explicit casting.

        //Print all titles whose ids are less than 5
        List<String> titleList = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println(titleList);

        //Assert that "delectus aut autem"" is one of the titles whose id is less than 5.
        //1.way
        assertTrue(titleList.contains("delectus aut autem"));

        //2.way
        titleList.stream().anyMatch(t->t.equals("delectus aut autem"));



    }
}
