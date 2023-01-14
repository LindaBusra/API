package Utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

    public static void main(String[] args) {
        System.out.println(generateToken());
    }

    public static String generateToken(){
        String url = "https://restful-booker.herokuapp.com/auth";
        Map<String, String> postBody = new HashMap<>();
        postBody.put("username","admin");
        postBody.put("password","password123");

        Response response = given().contentType(ContentType.JSON).body(postBody).when().post(url);
        response.prettyPrint();

        JsonPath token = response.jsonPath();
        return token.getString("token");


        //or
//        return response.jsonPath().getString("token");
    }
}