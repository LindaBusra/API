package api_myPractice.Guru;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class getResponseBody {



@Test
    public void getResponseBody(){


    given().queryParam("CUSTOMER_ID","68195")
            .queryParam("PASSWORD","1234!")
            .queryParam("Account_No","1")
            .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log()
            .body();

    /*
    Now notice that the URL used is long and less readable, if you look closely, you will notice that 3 query parameters are being used which are

Customer_ID
Password
Account_No

Ressurs:https://www.guru99.com/rest-assured.html
     */

    }
}
