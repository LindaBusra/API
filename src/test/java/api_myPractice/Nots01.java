package api_myPractice;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Notlar {



    // 1 : Postman, manuel API testleri için kullanılır
// 2 : Otomasyon testleri için de Rest Assured Library kullanılır
// 3 : Otomasyon testlerimizi yaparken aşağıdaki adımları izliyoruz
//   a) Gereksinimleri anlama
//   b) Test Case yazma
//     * Test Case yazımımda "Gherkin" dili kullanılır
//       Bizler yazılım diline hakim olsakta karşımızdaki kişiler hakim olmayabilir
//       Ama Gherkin diliyle yazılan testleri anlamakta zorluk çekmezler
//       Gherkin dilinde kullandığımız keywordler
//         - Given : Ön koşullar
//         - When : Yapılacak aksiyonlar (get, put, post, patch, delete)
//         - Then : Istek yaptıktan sonra (request gönderdikten sonra) doğrulama
//         - And : Çoklu işlemlerde kullanılır
//   c) Test kodlarımızı yazmaya başlarız
//      * Set the URL
//      * Set the Expected Data (beklenen datanın oluşturulması post, put, patch)
//      * Type code to request (talep göndermek için kod yazımı)
//      * Do Assertion (doğrulama yapma)
    /*
Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
 */


    @Test
    public void get01() {

        //i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/101";
        //ii) Set the expected data (beklenen data'nin olusturulmasi, Post, Put, Patch ile)
        //Bizden post, put, ya da patch istenmedigi icin bu case'de kullanmayacagiz.
        //iii) Type code to send request (talep gondermek icin kod yazimi)

        Response respons=given().when().get(url);
        respons.prettyPrint();
        // iv)Do Assertion (dogrulama yaparak
        respons.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
        //status Code konsola yazdıralım
        System.out.println("Status Code: " +respons.getStatusCode());

        // Content Type konsola yazdıralım
        System.out.println("Content Type: " +respons.getContentType());

        //Status line konsolşa yazdıralım
        System.out.println("Status Line: " +respons.getStatusLine());

        //Header konsola yazdıralım
        System.out.println("Header: " +respons.getHeaders());
        //Time konsola yazdıralım

        System.out.println("Time : " +respons.getTime());


    }
}