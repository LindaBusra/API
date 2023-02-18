package api_myPractice.get_requests02;


import base_urls.RegresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest07 extends RegresInBaseUrl {


/*

https://reqres.in/api/users Create URL request.
Verify data which has id 5, in the body
1) With Matcher CLASS
2) Verify with JsonPath.


with Postmann -->GET https://reqres.in/api/users/5


    "data": {
        "id": 5,
        "email": "charles.morris@reqres.in",
        "first_name": "Charles",
        "last_name": "Morris",
        "avatar": "https://reqres.in/img/faces/5-image.jpg"
    }


*/

    @Test
    public void test07(){

    //set url
    spec.pathParams("first", "users");

    //set expected data


    //send request and get respons
    Response response = given().spec(spec).when().get("/{first}");
    response.prettyPrint();

    //do assertion
        response.then().assertThat().body("data[4].email", equalTo("charles.morris@reqres.in")
                , "data[4].first_name" , equalTo("Charles")
                , "data[4].last_name", equalTo("Morris")        );


        // Json Path

        JsonPath json= response.jsonPath();

        assertEquals( "charles.morris@reqres.in", json.getString("data[4].email") );
        assertEquals( "Charles", json.getString("data[4].first_name") );
        assertEquals( "Morris", json.getString("data[4].last_name") );

        //print email, name, lastname on the console
        System.out.println(json.getList("data.email"));
        System.out.println(json.getList("data.first_name"));
        System.out.println(json.getList("data.last_name"));
    }

}
