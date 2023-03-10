package api_myPractice.get_requests02;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest11_bearerToken {

    String endPoint= "http://www.gmibank.com/api/tp-customers";
    String bearerToken ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXJhdHRhbmMiLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTY0NjUwNTU1NH0.ySbwAfpdtEWsWMucCoyBB5ND9Cu1jyD5rwNLSqF6GQu0XfM0LwCg6PerxCkbJCVFJ7CslucH5VOWEenRK2HQ7w";
    // I can not reach GMI Bank without this token.

    @Test
    public void test(){

        Response response = given().header("Authorization", "Bearer " + bearerToken)
                .when().get(endPoint).then().extract().response();


        response.prettyPrint();

    }





}