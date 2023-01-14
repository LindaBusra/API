package Token;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;



//To get token, we use post requirement. We will send username and password.

public class RegresToken {

// @Test -->for run this method, test annotation

    public String takeToken(){

        //set url
        String url="https://reqres.in/api/login";       //base url:https://reqres.in

        //create request data
        HashMap<String, String> requestBody = new HashMap<>();

        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");
        //I will send this request body and get token.

//      System.out.println("requestBody: " + requestBody);


        //to send request and get the response
        Response response=given()
                .contentType(ContentType.JSON)
                .body(requestBody)  //.auth().basic("admin","password")
                .when().post(url);

//      response.prettyPrint();

        JsonPath json =  response.jsonPath();  //I can reach token with JsonPath
        String token = json.getString("token");
//        System.out.println("token: " + token);

        return token;

//bu metodu farkli bir class'tan cagirdiimga bana sadece token degerini dondur diyorum.
        //authorization islemlerinde kullancii adi ve password vermek yerine bu token'i kullanacagim


    }
}
