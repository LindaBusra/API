package get_requests;


import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
      /*
        Given
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
        https://gorest.co.in/public/v1/users/2226
            Response body should be like
        {
    "meta": null,
    "data": {

        "id": 13,
        "name": "Rahul Jha",
        "email": "jha_rahul@beahan.co",
        "gender": "male",
        "status": "active"
    }
}
    */



    @Test
    public void get13(){
        //1.Step sewt the url
        spec.pathParams("first","users","second",13);


        //2.Step  set the expected data
        GoRestDataPojo goRestDataPojo=new GoRestDataPojo("Rahul Jha","jha_rahul@beahan.co","male","active");
        GoRestPojo expectedData =new GoRestPojo(null,goRestDataPojo);
        System.out.println("expectedData: " + expectedData);

        //3.Step send the get request and get the response

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step :Do Assertion
        GoRestPojo  actualData = response.as(GoRestPojo.class);
        System.out.println("actualData :" + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());








    }
}
