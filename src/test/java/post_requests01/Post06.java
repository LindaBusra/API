package post_requests01;

import Utils.JsonUtils;
import base_urls.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPost;
import pojos.Customer;
import pojos.States;

import java.util.ArrayList;

import static Utils.AuthenticationGMIBank.generateToken;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post06 extends GMIBankBaseUrl {

    /*
         Given
            https://www.gmibank.com/api/tp-countries
            {
              "name": "USA",
              "states": [
                {
                  "id": 12,
                  "name": "California",
                  "tpcountry": null
                }
              ]
            }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                    "id": 171555,
                                    "name": "USA",
                                    "states": [
                                        {
                                            "id": 12,
                                            "name": "California",
                                            "tpcountry": null
                                        }
                                    ]
                                  }
     */


    @Test
    public void post06(){

      //set the url
      spec.pathParams("first", "tp-countries") ;  //.queryParam("apikey", "password") -->in Authorization-->key, value :token using


      //set the expected data
        States states = new States(12, "California", null); //States is a class here

        ArrayList<States> statesList = new ArrayList<>();    //States-->data type here
        statesList.add(states);

        CountryPost expectedData = new CountryPost("USA", statesList);
        System.out.println("expectedData : " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON)    //.header("token", "tokenValue")  -->-->token using
                .headers("Authorization","Bearer "+generateToken())
                .body(expectedData)
                .when().post("/{first}");

        //Do assertion
        CountryPost actualData = JsonUtils.convertJsonToJavaObject(response.asString(),CountryPost.class);
        System.out.println("actualData: " + actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(states.getId(), actualData.getStates().get(0).getId());   //get first one, and get id from first one
        assertEquals(states.getName(), actualData.getStates().get(0).getName());
        assertEquals(states.getTpcountry(), actualData.getStates().get(0).getTpcountry());






    }
}
