package delete_requests;


import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteRequest01 extends DummyApiBaseUrl {


    @Test
    public void DeleteRequest01() {

        // url
        spec.pathParams("parametre1","delete" , "parametre2","2");


        // expected data
        DummyTestData obj = new DummyTestData();
        JSONObject expectedData = obj.setUpDeleteExpectedData();


        //request
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .auth().basic("admin" , "admin321")
                .when()   // I will delete, I dont need to use it.
                .delete("/{parametre1}/{parametre2}");
        response.prettyPrint();


        //Assertion



        //==========================================Matcher Class ile================================================

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body(  "status" , equalTo(expectedData.getString("status")),
                        "data"    , equalTo(expectedData.getString("data")),
                        "message" , equalTo(expectedData.getString("message"))
                );




        //======================================= Json Path ile ======================================================

        JsonPath json= response.jsonPath();

        Assert.assertEquals(expectedData.getString("status") , json.getString("status"));
        Assert.assertEquals(expectedData.getString("data") , json.getString("data"));
        Assert.assertEquals(expectedData.getString("message") , json.getString("message"));



        //======================================= De-Serilization ile ================================================


        HashMap<String, Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData.getString("status") , actualData.get("status"));
        Assert.assertEquals(expectedData.getString("data") , actualData.get("data"));
        Assert.assertEquals(expectedData.getString("message") , actualData.get("message"));

        System.out.println(" Successfully! Record has been deleted ");




    }


}