package api_sm.get_requests03;

import base_urls.DummyApiBaseUrl;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Get03 extends DummyApiBaseUrl {

         /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve employees sayisinin 24
    ve employee'lerden birinin "Garrett Winters"
    ve gelen yaslar icinde 43, 61, ve 23 degerlerinden birinin oldugunu test edin
     */


    @Test
    public  void test(){

    //set url
    spec.pathParam("first", "employees");


    //set expected data


    //send request and get response
    Response response = given().spec(spec).when().get("/{first}");
    response.prettyPrint();

    //do assertion
        response.then().assertThat().statusCode(200).contentType("application/json").
                body("data.id", hasSize(24),
                        "data.employee_name", hasItem("Garrett Winters"),
                        "data.employee_age", hasItems(43,61,23));
















    }












}
