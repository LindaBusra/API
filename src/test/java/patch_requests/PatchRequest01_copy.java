package patch_requests;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PatchRequest01_copy extends JsonPlaceHolderBaseUrl {


    @Test
    public void PatchRequest01() {

        // Patch işleminde iki ayrı yapıya ihtiyacım olacak bu yüzden iki ayrı expected test datası oluşturacagız.


        // URL-spec girişi
        spec.pathParams("parametre1","todos","parametre2","198");

        // Expected ve Request oluşturuyorum

        JsonPlaceHolderTestData testData = new JsonPlaceHolderTestData();
        JSONObject requestData=testData.setUpPatchRequestData();
        JSONObject expectedData=testData.setUpPatchExpectedData();

        // Gönderdigimiz ve bekledigimiz sonuç birbirinden farklı oldugu için iki tane data oluşturduk ve
        // buraya çagırdık. Patch işleminde sadece degiştirecegim datayı gönderiyorum bana o datanın degişmiş bir
        // şekli ile diger datalarla birlikte geliyor. PUT işleminden ayıran en önemli özellik !!!


        // Request gönder.

        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .auth().basic("admin" , "admin321")
                .body(requestData.toString())
                .when()
                .patch("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //=========================================Assertion Yapıyoruz================================================

        //========================================= Matcher Class ile ================================================

        response.then().assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body( "title"   , equalTo(expectedData.getString("title")),
                        "userId"  , equalTo(expectedData.getInt("userId")),
                        "completed" , equalTo(expectedData.getBoolean("completed")),
                        "id"   , equalTo(expectedData.getInt("id")) );


        //========================================= Json Path Class ile ==============================================

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedData.getInt("id") , json.getInt("id"));
        Assert.assertEquals(expectedData.getInt("userId") , json.getInt("userId"));
        Assert.assertEquals(expectedData.getBoolean("completed") , json.getBoolean("completed"));
        Assert.assertEquals(expectedData.getString("title") , json.getString("title"));


        //============================================================================================================

        //========================================= De - Serilization ile ==============================================

        HashMap<String, Object> actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);          // Actual Data oluşturuyorum.
        // Burada biz actual Datayı alırken .as kullanıyoruz. Bu ekledigimiz kütüphanelerden geliyor
        // Bunu mutlaka bir Map veya benzeri bir yapı ile oluşturmalıyım.
        // Bu yzden burada JSONObject kullanıp,actual dataları JSONObjecte atayamıyorum!!!

        Assert.assertEquals( 200 , response.getStatusCode() );
        Assert.assertEquals( expectedData.getInt("userId") , actualData.get("userId")    );
        Assert.assertEquals( expectedData.getInt("id") , actualData.get("id")    );
        Assert.assertEquals( expectedData.getString("title") , actualData.get("title")    );
        Assert.assertEquals( expectedData.getBoolean("completed") , actualData.get("completed")    );



        //============================================================================================================




    }

}