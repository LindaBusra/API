package homework;

import base_urls.RegresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Homework6 extends RegresInBaseUrl {

    /*

    //Homework6:

   /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void Homework6() {

        //Set the url
        spec.pathParam("first", "unknown");

        //Set the expected data


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data",hasSize(6));


        //Do Assertion
        //1)Status code is 200

        assertEquals(200,response.statusCode());



        //2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        List<String> allPantoneValues = jsonPath.getList("data.pantone_value");
        System.out.println("allPantoneValues" + allPantoneValues);

        Collections.sort(allPantoneValues);
        System.out.println(allPantoneValues);  //if we want to print with alphabetical orders


        //3)Print all ids greater than 3 on the console

        List<Integer> idsGreaterThanThree = jsonPath.getList("data.findAll{it.id>3}.id");  //Groovy
        System.out.println("idsGreaterThan190Groovy = " + idsGreaterThanThree);

        //Assert that there are 3 ids greater than 3
        assertEquals(3, idsGreaterThanThree.size());


        //4)Print all names whose ids are less than 3 on the console
        List<Integer> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names);

        //Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2, names.size());



    }
}



