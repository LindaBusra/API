package Token;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;



public class RegresRequest {

    @Test
    public void test(){
        RegresToken obj = new RegresToken();

        System.out.println(obj.takeToken());



    }

}
