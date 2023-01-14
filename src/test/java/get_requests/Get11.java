package get_requests;


import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
      /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
             "Bhaaswar Achari", "Abhirath Kocchar", "Sher Dutta" are among the users
        And
            The female users are less than or equals to male users


     */


    @Test
    public void get11(){
        // 1, Step : set the url

        spec.pathParam("first","users");

        // 2. Step  set the expected data



        //3. Step send the request and get the response

        Response response=given().spec(spec).when().get("/{first}");
        //response.prettyPrint();


        //4.step : Do assertion

        //1. yol : body () methodu ile
        response.then().assertThat().statusCode(200).
                body("meta.pagination.limit",equalTo(10)
                        ,"meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id",hasSize(10),
                        "data.status",hasItem("active"),
                        "data.name",hasItems("Bhaaswar Achari", "Abhirath Kocchar", "Sher Dutta"));



        //The female users are less than or equal to male users.
        //I will compare number of female and male users.
        //1.st way:I will get all genders in a list then calculate the number of females then compare it with the list.

        JsonPath json=response.jsonPath();
        List<String> genders=json.getList("data.gender");

        int femaleNum=0;


        for (String w:genders) {
            if (w.equalsIgnoreCase("female")){
               femaleNum++;
            }
        }


        System.out.println("femaleNum:" + femaleNum );
        int maleNum= genders.size()-femaleNum;
        System.out.println("maleNum: " + maleNum);

        assertTrue(femaleNum <= maleNum);


        //I will get all females by using Groovy language then compare it with males.
        List<String> ListOfFemale = json.getList("data.findAll{it.gender=='female'}.gender");  //findAll doing filter
        List<String> ListOfMale = json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(ListOfFemale+" "+ ListOfMale);

        assertTrue(ListOfFemale.size() <= ListOfMale.size());




    }
}
