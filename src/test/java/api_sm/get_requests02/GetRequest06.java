package api_sm.get_requests02;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetRequest06 extends HerOkuAppBaseUrl {

    /*
To the url https://restful-booker.herokuapp.com/booking/256884
When I send GET request with accept type "application/json"
of the response
status code 200
and the content type is "application/json"
and the firstname is "Nick"
and the totalprice is 100
and test that the checkin date is "2020-11-11"



    //GET https://restful-booker.herokuapp.com/booking/256884  -->POSTMANN

    {
        "firstname": "Nick",
            "lastname": "Smith",
            "totalprice": 100,
            "depositpaid": true,
            "bookingdates": {
        "checkin": "2020-11-11",
                "checkout": "2020-12-12"
    },
        "additionalneeds": "Breakfast"
    }
      */

    @Test
    public void test06(){

        //set url
        spec.pathParams("first", "booking", "second", 256884);

        //set expected data


        //send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //do assertion
        response.then().assertThat().statusCode(200).contentType("application/json").
        body("firstname", equalTo("Nick"), "totalprice", equalTo(100),
                "bookingdates.checkin", equalTo("2020-11-11"));


        //or for body part, we can use Matchers
        response
                .then()
                .assertThat()
                .body("firstname", Matchers.equalTo("Nick")
                        , "totalprice",Matchers.equalTo(100)
                        , "bookingdates.checkin",Matchers.equalTo("2020-11-11")
                );
    }

}
