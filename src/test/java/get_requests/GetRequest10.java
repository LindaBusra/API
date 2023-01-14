package get_requests;


import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends HerOkuAppBaseUrl {
    /*
https://restful-booker.herokuapp.com/booking/47
       {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-02-01",
               "checkout": "2022-02-11"
          }
       }
1) JsonPhat
2) De-Serialization
*/

    @Test
    public void test21(){

        // URL oluşturup Dinamik Yapı ile gidiyorum.
        spec.pathParams("first", "booking" , "second" , 40);

        // Expected Data oluşturuyorum, Dinamik yapı ile.
        HerOkuAppTestData expectedObje=new HerOkuAppTestData(); //HerOkuAppTestData classındaki Map Methoduna ulaşmak
        //  için obje oluşturdum, obje aracılıgı ile ulaşacagım.
        HashMap<String,Object> expectedTestDataMap= expectedObje.setUpTestData();//Mapimi diğer classdaki Merthodu Assign ettim.

        System.out.println("Test Data içinde bulunan Expected Data: " + expectedTestDataMap); // Mapimindeki verileri görüyorum.

        // {firstname=Ali,
        // bookingdates={
        //               checkin=2022-02-01,
        //               checkout=2022-02-11},
        // totalprice=500,
        // depositpaid=true,
        // lastname=Can}


        // Request ve Responce işlemlerini halleedecem

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Assert - Doğrulama

        // 1. Yol --> De-Serialization !!Önemli!!
        // datalarım java formatında response'ın bana döndügü bilgi json formatında, bu yüzden ikisni kıyaslayamıyorum
        // Dogrulama yapmak için / json formatını java formatına dönüştürmek için de-serialization yapıyoruz.

        HashMap<String,Object> actualData=response.as(HashMap.class);
        // Json formatındaki DAtayı javaya/HashMap formatına dönüştürür.
        System.out.println("Actual Data: " + actualData);

        Assert.assertEquals(expectedTestDataMap.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), actualData.get("depositpaid"));


        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin") ,
                ((Map)actualData.get("bookingdates")).get("checkin"));

        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkout") ,
                ((Map)actualData.get("bookingdates")).get("checkout"));


        // 2. Yol --> JSon Path İle

        JsonPath json = response.jsonPath(); // response verimi json pathe atadım.
        Assert.assertEquals(expectedTestDataMap.get("firstname"), json.getString("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), json.getString("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), json.getInt("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), json.getBoolean("depositpaid"));
        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkin"),
                json.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkout"),
                json.getString("bookingdates.checkout"));

        // Java emin olmak için cast yapmamızı istiyor,zaten bir defa get yaptın ikinci geti yapamazsın diyor
        // biz gelecek verinin map oldugunu ve bir daha get yaptıgımda takrardan mapden veri geleceginin garantisini
        // vermiş oluyorum aslında. Bu yüzden de .get derken cast yapmak gerekiyor.


    }


}
