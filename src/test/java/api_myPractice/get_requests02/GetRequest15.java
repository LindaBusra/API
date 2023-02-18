package api_myPractice.get_requests02;


import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetRequest15 extends DummyApiBaseUrl {

        /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
14. Çalışan isminin "Haley Kennedy" olduğunu,
Çalışan sayısının 24 olduğunu,
Sondan 3. çalışanın maaşının 675000 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
{
        "id": 10,
        "employee_name": "Sonya Frost",
        "employee_salary": 103600,
        "employee_age": 23,
        "profile_image": ""
 }
  olduğunu test edin.
*/

    @Test
    public void test23(){

        // URL oluşturacagım

        //    api/v1/employees kısmını burada eklemiş oldum.
        spec.pathParams("first",  "employees");

        // Expected Data oluşturacağım.

        DummyTestData expectedObje=new DummyTestData();

        HashMap<String, Object> expectedData= expectedObje.setUpTestData();

        System.out.println(expectedData);


        // Request ve Response Oluşturulacak

        Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");

        response.prettyPrint();


        // DOGRULAMA YAPACAGIZ. FARKLI YOLLARLA YAPABİLİRİZ
        // 1.YOL: De-Serialization

        HashMap<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("statusCode"), response.getStatusCode());


        Assert.assertEquals(expectedData.get("ondorduncucalisan"),
                ((Map)((List)actualData.get("data")).get(13)).get("employee_name") );



        Assert.assertEquals(expectedData.get("calisansayisi"),
                ((List<?>) actualData.get("data")).size() );


        //Sondan 3. çalışanın maaşının 675000 olduğunu
        //1. Yol
        Assert.assertEquals(expectedData.get("sondanucuncucalisaninmaasi"),
                ((Map)((List)actualData.get("data")).get(((List)actualData.get("data")).size()-3)).get("employee_salary"));

        //2. Yol

        int dataSize = ((List<?>) actualData.get("data")).size();

        Assert.assertEquals(expectedData.get("sondanucuncucalisaninmaasi"),
                ((Map)((List<?>) actualData.get("data")).get(dataSize-3)).get("employee_salary"));

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını

        //1. Yol
        List<Integer> actualYasListesi = new ArrayList<>();

        for(int i =0; i<dataSize; i++){
            actualYasListesi.add(((Integer) ((Map)((List<?>) actualData.get("data")).get(i)).get("employee_age")));
        }
        Assert.assertTrue(actualYasListesi.containsAll((Collection<?>) expectedData.get("arananyaslar")));

        //2. Yol
        List<Integer> employee_age = new ArrayList<>();
        for(int i=0 ; i < ((List)actualData.get("data")).size() ; i++){
            employee_age.add((Integer) ((Map)((List)actualData.get("data")).get(i)).get("employee_age"));
        }









        // body map'in icinde --> map icinde List(suan ki soru icin bunun ismi=data) var
// Bu List'i de her bir index'e gitmek icin kullaniyorum Orn=((List)actualDataMap.get("data")).get(13))
// her bir index'te bir Map oldugu icin (key-value iliskisi) .get("key") yazip value degere ulasiyorum


    }


}