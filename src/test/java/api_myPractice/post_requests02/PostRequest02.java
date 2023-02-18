package api_myPractice.post_requests02;


import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class PostRequest02 extends DummyApiBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ali Can",
    "salary":"2000",
    "age":"40",
}
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,
{
    "status": "success",
    "data": {
    "id":…
},
    "message": "Successfully! Record has been added."
}
olduğunu test edin
 */

    @Test
    public void test02() {

        //set url
        spec.pathParam("first", "create");

        //set expected data
        DummyTestData obj = new DummyTestData();
        HashMap<String,Object> requestBody = obj.setUpRequestBody();
        HashMap<String,Object> expectedData = obj.setUpExpectedData();


        //sent request and get response
        Response response = given().contentType(ContentType.JSON).spec(spec).body(expectedData).when().post("/{first}");

        //do assertion

        //de-serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        //output => actualData :{data={message=Successfully! Record has been added., statusCode=200, status=success, id=7769}, message=Successfully! Record has been added., status=success}

        Assert.assertEquals(expectedData.get("statusCode"), response.getStatusCode());

        Assert.assertEquals(expectedData.get("status"), actualData.get("status"));
        Assert.assertEquals(expectedData.get("message"), actualData.get("message"));


        //Json path
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"), response.getStatusCode());

        Assert.assertEquals(expectedData.get("status"), json.getString("status"));
        Assert.assertEquals(expectedData.get("message"), json.getString("message"));













    }
}