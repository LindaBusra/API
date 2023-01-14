package post_requests01;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

 /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,

                                    }
     */

    @Test
    public void post01(){


        //1.Step : Set the url
        spec.pathParam("first","todos");

        //2.Step : Set the excepted data,  this obj is my expected data
        JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        //pojo -->plan old java object --> pojo class is faster, and more secure.  -->Payload:pojo class

        //3.Step : Send the post request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");


        // 4.Step : Do Assertion
        JsonPlaceHolderPojo actualData= response.as(JsonPlaceHolderPojo.class);  //De-Serialization
        System.out.println("actualData: " + actualData);



        assertEquals(201, response.statusCode());   // or  response.then().assertThat().statusCode(201);
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());

        assertEquals(expectedData.toString(),actualData.toString());

        /*First create inner Pojo
        create a class for bookingdates.   -->class BookingdatesPojo{
        //1-create private variables for all keys
        2-create constructors with all parameters and without parameter
        3-create public getters and setters and toString() method.
}


        /Then create outer Pojo


*/








    }





}