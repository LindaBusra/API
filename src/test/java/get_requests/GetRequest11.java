package get_requests;



import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends DummyApiBaseUrl {

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
        spec.pathParams("bir", "api", "iki", "v1", "uc", "employees");

        // Expected Data oluşturacağım.

        DummyTestData expectedObje=new DummyTestData();

        HashMap<String, Object> expectedTestDataMap= expectedObje.setUpTestData();

        System.out.println(expectedTestDataMap);


        // Request ve Response Oluşturulacak

        Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{bir}/{iki}/{uc}/");

        response.prettyPrint();


        // DOGRULAMA YAPACAGIZ. FARKLI YOLLARLA YAPABİLİRİZ
        // 1.YOL: De-Serialization

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(expectedTestDataMap.get("statusCode"), response.getStatusCode());


        Assert.assertEquals(expectedTestDataMap.get("ondorduncucalisan"),
                ((Map)((List)actualDataMap.get("data")).get(13)).get("employee_name") );



        Assert.assertEquals(expectedTestDataMap.get("calisansayisi"),
                ((List<?>) actualDataMap.get("data")).size() );


        //Sondan 3. çalışanın maaşının 675000 olduğunu
        //1. Yol
        Assert.assertEquals(expectedTestDataMap.get("sondanucuncucalisaninmaasi"),
                ((Map)((List)actualDataMap.get("data")).get(((List)actualDataMap.get("data")).size()-3)).get("employee_salary"));

        //2. Yol

        int dataSize = ((List<?>) actualDataMap.get("data")).size();

        Assert.assertEquals(expectedTestDataMap.get("sondanucuncucalisaninmaasi"),
                ((Map)((List<?>) actualDataMap.get("data")).get(dataSize-3)).get("employee_salary"));

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını

        //1. Yol
        List<Integer> actualYasListesi = new ArrayList<>();

        for(int i =0; i<dataSize; i++){
            actualYasListesi.add(((Integer) ((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_age")));
        }
        Assert.assertTrue(actualYasListesi.containsAll((Collection<?>) expectedTestDataMap.get("arananyaslar")));

        //2. Yol
        List<Integer> employee_age = new ArrayList<>();
        for(int i=0 ; i < ((List)actualDataMap.get("data")).size() ; i++){
            employee_age.add((Integer) ((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age"));
        }









        // body map'in icinde --> map icinde List(suan ki soru icin bunun ismi=data) var
// Bu List'i de her bir index'e gitmek icin kullaniyorum Orn=((List)actualDataMap.get("data")).get(13))
// her bir index'te bir Map oldugu icin (key-value iliskisi) .get("key") yazip value degere ulasiyorum


    }


}