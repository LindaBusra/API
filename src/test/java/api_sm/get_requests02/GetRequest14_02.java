package api_sm.get_requests02;


import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetRequest14_02 extends HerOkuAppBaseUrl {

        /*
https://restful-booker.herokuapp.com/booking/47
{
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
1) JsonPhat
2) De-Serialization
*/


   @Test
   public void test22(){

    //set url
   spec.pathParams("first", "booking", "second", 47);

   //set expected data
       HerOkuAppTestData obj = new HerOkuAppTestData();

       HashMap<String, Object> expectedData = obj.setUpTestData();

       System.out.println("My expectedData : " + expectedData);

//My expectedData : {firstname=Ali, additionalneeds=Breakfast, bookingdates={checkin=2022-02-01, checkout=2022-02-11}, totalprice=500, depositpaid=true, lastname=Can}



  //send request and get response
    Response response = given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();



  //do assertion

       //1.way: De-Serialization
       //response is in Jason format. I have to convert it to java format to be able to do assertion.

       HashMap<String, Object> actualData = response.as(HashMap.class); //I convert json format to java hashmap format.
       System.out.println("actualData : " + actualData);

       assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
       assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
       assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
       assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
       assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

       assertEquals(  ((Map)expectedData.get("bookingdates")).get("checkin"),  ((Map) actualData.get("bookingdates")).get("checkin"));

       assertEquals(   ((Map) expectedData.get("bookingdates")).get("checkout"),   ((Map) actualData.get("bookingdates")).get("checkout") );



//----------------------------------------------------------------------------------------------------------------------


       //2.nd way - with JsonPath

       JsonPath json = response.jsonPath();  //I assigned response data to json path
       assertEquals(expectedData.get("firstname"), json.getString("firstname"));
       assertEquals(expectedData.get("lastname"), json.getString("lastname"));
       assertEquals(expectedData.get("depositpaid"), json.getBoolean("depositpaid"));
       assertEquals(expectedData.get("totalprice"), json.getInt("totalprice"));
       assertEquals(expectedData.get("additionalneeds"), json.getString("additionalneeds"));

       assertEquals(  ((Map)expectedData.get("bookingdates")).get("checkin"), json.getString("bookingdates.checkin"));

       assertEquals(   ((Map) expectedData.get("bookingdates")).get("checkout"), json.getString("bookingdates.checkout") );

// Java emin olmak için cast yapmamızı istiyor,zaten bir defa get yaptın ikinci geti yapamazsın diyor.
// biz gelecek verinin map oldugunu soyluyoruz.  ve bir daha get yaptıgımda tekrardan mapden veri geleceginin
// garantisini  vermiş oluyorum aslında. Bu yüzden de .get derken cast yapmak gerekiyor.








   }



}
