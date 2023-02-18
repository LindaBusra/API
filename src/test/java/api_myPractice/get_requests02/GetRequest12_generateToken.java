package api_myPractice.get_requests02;


import Utils.Authentication;
import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class GetRequest12_generateToken extends Authentication {

    // Authentication Class'ın içerisindeki generatToken methodu kullanılacak.

    String endPoint = "http://www.gmibank.com/api/tp-customers";

    @Test
    public void test12(){

        Response response = given()
                .header("Authorization" , "Bearer " + generateToken())
                .when()
                .get(endPoint).then().extract().response();




    }





}