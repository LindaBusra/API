package api_sm.get_requests02;


import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest03 {

    /*

To the url https://restful-booker.herokuapp.com/booking/7
When I send the GET request
of the response
status code 200
and the content type is "application/json"
and the firstname is "ayse"
and the lastname is "mese"
and checkin date of 2020-04-10"
and test that the checkout date is 2020-5-10

    */

    @Test
    public void test03(){

        //set url
        String url = "https://restful-booker.herokuapp.com/booking/260678";

        //set expected data

        //send request and get response
        Response response = given().when().get(url);
        response.prettyPrint();

        //Do assertion
        response.then().assertThat().statusCode(200).contentType("application/json");

        response.then().assertThat().body("firstname", equalTo("ayse")
                , "lastname", equalTo("mese")
                , "bookingdates.checkin", equalTo("2020-04-10")
                , "bookingdates.checkout", equalTo("2020-05-10")
        );


/*first I added user in postmann with PUT

{
    "firstname": "ayse",
    "lastname": "mese",
    "totalprice": 10,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2020-04-10",
        "checkout": "2020-05-10"
    },
    "additionalneeds": "breakfast"


    response:
    {
    "bookingid": 260678,
    "booking": {
        "firstname": "ayse",
        "lastname": "mese",
        "totalprice": 10,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2020-04-10",
            "checkout": "2020-05-10"
        },
        "additionalneeds": "breakfast"
    }
}
}



*/
    }






}
