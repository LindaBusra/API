package post_requests01;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pojos.TodosPojo;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.expectedDatasetup;

public class Post01 extends JsonPlaceHolderBaseUrl {

    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
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
                                    "id": 201
                                    }
     */

    @Test
    public void post01(){

        //Set the url
        spec.pathParam("first","todos");

        //Set the expected data
        Map<String,Object> expectedData = new HashMap<>();

        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.then().assertThat().statusCode(201);
        response.prettyPrint();


        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(201,actualData.get("id"));


//------------------------------------------------------------------------------------------

        //Do assertion with Json
        JsonPath json = response.jsonPath();

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("userId"), json.getInt("userId"));
        assertEquals(expectedData.get("title"), json.getString("title"));
        assertEquals(expectedData.get("completed"), json.getBoolean("completed"));
        assertEquals(201, json.getInt("id"));
    }



    @Test
    public void post02(){

        //Set the url
        spec.pathParam("first","todos");

        //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataJPH(55,"Tidy your room", false);
        System.out.println("expectedData: " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();


        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
    }




//with TodosPojo class

    @Test
    public void post03() {
        //Set the Url
        spec.pathParam("bir", "todos");

        //Set the expected data
        TodosPojo expectedTodo = new TodosPojo(21, 201, "Tidy your room", false);

        //Send the post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedTodo).when().post("/{bir}");
        response.prettyPrint();

        //Validation
        TodosPojo actualData = response.as(TodosPojo.class);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedTodo.getUserId(), actualData.getUserId());
        assertEquals(expectedTodo.getId(), actualData.getId());
        assertEquals(expectedTodo.getTitle(), actualData.getTitle());
        assertEquals(expectedTodo.isCompleted(), actualData.isCompleted());


    }







        //Serialization : To convert Java object to Json file
        //De-Serialization : To convert Json file to object
                            //response.jsonPath().getList()/getString()
                            //response.as(HashMap.class)
        //Payload:The data to send to the API



    @Test
    public void post04() {
        //Set the base Url
        spec.pathParams("bir", "todos");

        //Set the expected data
        Map<String, Object> expectedData = expectedDatasetup();

        //Send the Post request and Get the response
        Response response = given().spec(spec).auth().basic("admin", "1234").contentType(ContentType.JSON).
                body(expectedData).when().post("/{bir}");

        response.prettyPrint();

        //validate
        response.then().assertThat().statusCode(201).
                body("userId", equalTo(55), "title", equalTo("Tidy your room"),
                        "completed", equalTo(false));


    }



    @Test
    public void post05(){

        spec.pathParam("parametre1","todos");

        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest= testObje.setUpPostData();
        System.out.println(expectedRequest);


        Response response= given().
                contentType(ContentType.JSON).
                spec(spec).
                auth().basic("admin","password123").
                body(expectedRequest.toString()).
                when().
                post("/{parametre1}");

        //  response.prettyPrint();

        //Matchers class

        response.then().
                assertThat().statusCode(expectedRequest.getInt("statusCode"))
                .body("completed", equalTo(expectedRequest.getBoolean("completed")),
                        "title",equalTo(expectedRequest.getString("title")),
                        "userId",equalTo(expectedRequest.getInt("userId")));


        //JsonPath ile

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedRequest.getInt("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));

        //De Serialization

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),actualDataMap.get("completed"));












    }


}

