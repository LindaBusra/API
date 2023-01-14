package api_sm.get_requests03;



import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Get08 extends DummyApiBaseUrl {

        /*
        http://dummy.restapiexample.com/api/v1/employees
       url ine bir istek gönderildiğinde
       Dönen response un
        Status kodunun 200,
        1)10’dan büyük tüm id’leri ekrana yazdırın ve
       10’dan büyük 14 id olduğunu,
        2)30’dan küçük tüm yaşları ekrana yazdırın ve
         bu yaşların içerisinde en büyük yaşın 23 olduğunu
        3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
         bunların içerisinde “Charde Marshall” olduğunu test edin
    */


    @Test
    public void test(){

    //set url
        spec.pathParam("first", "employees");


    //set expected data


    //sent request and get response
    Response response = given().spec(spec).when().get("/{first}");
    response.prettyPrint();

    //do assertion
    Assert.assertEquals(200, response.getStatusCode());

    JsonPath json = response.jsonPath();

    //1) Print all ids greater than 10 and test there are 14 ids greater than 10.
    List<Integer> idList = json.getList("data.findAll{it.id>10}.id");
        System.out.println(idList);

    Assert.assertEquals(14, idList.size());

    //2) Print all ages below 30 on the screen and test among these ages, the oldest age is 23.
    List<Integer> ages =   json.getList("data.findAll{it.employee_age<30}.employee_age");
    Collections.sort(ages);

    Assert.assertEquals((Integer)23, ages.get(ages.size()-1));


    //3) Print all employee names with a salary greater than 350000 on the screen and
    // test that they contain "Charde Marshall"
   List<String> employeeNames = json.getList("data.findAll{it.employee_salary>350000}.employee_name");

   Assert.assertTrue(employeeNames.contains("Charde Marshall"));



















    }
}
