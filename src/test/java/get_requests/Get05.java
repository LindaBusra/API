package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {



    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User sends get request to the URL
        Then
            Status code is 200
      And
         Among the data there should be someone whose firstname is "Carlos" and lastname is "Parchment"
        */

    //postmann-->GET -->https://restful-booker.herokuapp.com/booking/24337-->send
    //postmann-->GET -->https://restful-booker.herokuapp.com/booking
    //Query Parameters-->firstname:Carlos,  lastname:Parchment  (see url, it is changing with parameters whic we are looking. ?  ? )
    //GET-->https://restful-booker.herokuapp.com/booking?firstname=Bogdan Ionut&lastname=Dogariu   -->-->It gives bookingid:24337


    @Test
    public void get05() {

        //Set the url
        spec.pathParam("first", "booking").
                queryParams("firstname","Carlovvs","lastname","Parchment"); //I am going to make filter
                //queryParams("lastname","Dear");  -->we can use also just one filter


        //Set the expected data

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        //Status code is 200
        assertEquals(200,response.statusCode());

        //Among the data there should be someone whose firstname is "Carlos" and lastname is "Parchment"
        assertTrue(response.asString().contains("bookingid"));    //output is 61610

    }
}





