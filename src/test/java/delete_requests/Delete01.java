package delete_requests;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
       Given
           https://jsonplaceholder.typicode.com/todos/198
       When
            I send DELETE Request to the Url
        Then
            Status code is 200
            And Response body is { }
    */
    @Test
    public void delete01(){

        // 1.Step: Set the url
        spec.pathParams("first","todos","second",198);

        // 2.Step: set the excepted data  (an empty map is expected data)
        Map<String,Object> exceptedData=new HashMap<>();


        //3. Step : Send the delete request and get the response
        Response response=given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4.Step : Do Assertion
        //1.way
        response.then().assertThat().statusCode(200);
        Map<String ,Object> actualData =response.as(HashMap.class);    //Jackson, gson  -->as


        assertEquals(exceptedData,actualData );

        //or
        assertEquals(exceptedData.size(),actualData.size() );

        //or
        assertTrue(actualData.size()==0);

        //or
        assertTrue(actualData.isEmpty());

        //or
        response.then().assertThat().body("isEmpty()", is(true));


        /*
        How to automate Delete request in API testing?
        1)Create a new data by using "Post Request"
        2)Use "Delete Request" to delete new data.

        Note:Do not delete existing data, create a data to delete.

         */




    }
}