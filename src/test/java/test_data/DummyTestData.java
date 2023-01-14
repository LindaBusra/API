package test_data;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyTestData {
    public Map<String, Object> dummyDatesSetUp(String employee_name, Integer employee_salary,Integer employee_age,String profile_image){

        Map<String, Object> dummyDatesMap = new HashMap<>();
        dummyDatesMap.put("employee_name", employee_name);
        dummyDatesMap.put("employee_salary", employee_salary);
        dummyDatesMap.put("employee_age", employee_age);
        dummyDatesMap.put("profile_image", profile_image);


        return dummyDatesMap;

    }

    public Map<String,Object> expectedDataSetUp(String status, Map<String,Object> data, String message){
        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("status",status);
        expectedDataMap.put("data",data);
        expectedDataMap.put("message",message);

        return  expectedDataMap;
    }

    public String expectedDataInString(String status,String employee_name,int employee_salary,int employee_age,String profile_image,String message){
        String expectedData=  "{\n" +
                "    \"status\": \""+status+"\",\n" +
                "    \"data\": {\n" +
                "        \"employee_name\": \""+employee_name+"\",\n" +
                "        \"employee_salary\": "+employee_salary+",\n" +
                "        \"employee_age\": "+employee_age+",\n" +
                "        \"profile_image\": \""+profile_image+"\"\n" +
                "    },\n" +
                "    \"message\": \""+message+"\"\n" +
                "}";

        return expectedData;

    }


    public HashMap<String, Object> setUpTestData() {


        // 40, 21 ve 19 yaşlarında çalışanlar olup olmadıgıni kontrol et

        List<Integer> yaslar=new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);


        /*
         "id": 10,
        "employee_name": "Sonya Frost",
        "employee_salary": 103600,
        "employee_age": 23,
        "profile_image": ""
         */

        HashMap<String, Object> onuncu=new HashMap<>();
        onuncu.put("id" , 10);
        onuncu.put("employee_name" , "Sonya Frost");
        onuncu.put("employee_salary" , 103600);
        onuncu.put("employee_age" , 23);
        onuncu.put("profile_image" , "");


       /* Status kodun 200 olduğunu,
                14. Çalışan isminin "Haley Kennedy" olduğunu,
                Çalışan sayısının 24 olduğunu,
                Sondan 3. çalışanın maaşının 675000 olduğunu
        40,21 ve 19 yaslarında çalışanlar olup olmadığını
        */

        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("statusCode" , 200 );
        expectedData.put("ondorduncucalisan" , "Haley Kennedy" );
        expectedData.put("calisansayisi" , 24 );
        expectedData.put("sondanucuncucalisaninmaasi" , 675000 );
        expectedData.put("arananyaslar" , yaslar );
        expectedData.put("onuncucalisan" , onuncu );


        return expectedData;
    }



    //for PostRequest02

    //{
    //    "name":"Ali Can",
    //    "salary":"2000",
    //    "age":"40"
    //    }



    public HashMap<String , Object> setUpRequestBody() {            //Request Body

        HashMap<String , Object> requestBody=new HashMap<>();
        requestBody.put("name","Ali Can");
        requestBody.put("salary","2000");
        requestBody.put("age","40");

        return requestBody;
    }



    /*
    {
    "status": "success",
    "data": {
    "id":…
},
    "message": "Successfully! Record has been added."
}
     */


    public HashMap<String, Object> setUpExpectedData(){             //response body

        HashMap<String , Object> expectedData=new HashMap<>();

        expectedData.put("statusCode", 200);
        expectedData.put("status", "success");
        expectedData.put("message", "Successfully! Record has been added.");

        return expectedData;
    }



    public HashMap<String, Object> setUpTestData02(){


        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("highestSalary", 725000);
        expectedData.put("lowestAge", 19);
        expectedData.put("secondHighestSalary", 675000);

    return expectedData;


    }



    public HashMap<String, Object> setUpTestData11() {


        HashMap<String, Object> expectedData=new HashMap<>();

        expectedData.put("statusCode", 200);
        expectedData.put("enYuksekMaas", 725000);
        expectedData.put("enKucukYas", 19);
        expectedData.put("ikinciYuksekMaas", 675000);

        return expectedData;

    }

//-------------------------------------------------------------------------------------------------------

    public HashMap<String, Object> setUpPostRequestBody () {

        HashMap<String, Object> requestBody=new HashMap<>();
        requestBody.put("name" , "Tedy");
        requestBody.put("salary" , "52000");
        requestBody.put("age" , "35" );
        requestBody.put("profile_image" , "no");

        return requestBody;
    }



    public HashMap<String, Object> setUpPostExpectedData () {

        HashMap<String, Object> bodyData=new HashMap<>();
        bodyData.put("name" , "Tedy");
        bodyData.put("salary" , "52000");
        bodyData.put("age" , "35");
        bodyData.put("profile_image" , "no");

        HashMap<String, Object> requestBody=new HashMap<>();
        requestBody.put("statusCode" , 200);
        requestBody.put("status" , "success");
        requestBody.put("message" , "Successfully! Record has been added." );
        requestBody.put("data" , bodyData );

        return requestBody;
    }


//-------------------------------------------------------------------------------------------------------


    public JSONObject setUpDeleteExpectedData () {

        JSONObject expectedData=new JSONObject();
        expectedData.put("status" , "success");
        expectedData.put("data" , "2");
        expectedData.put("message" , "Successfully! Record has been deleted");

        return expectedData;


    }



    public HashMap<String, Object> setUpTestData2() {

        HashMap<String, Object> onbirinci=new HashMap<>();
        onbirinci.put("id" , 11);
        onbirinci.put("employee_name" , "Jena Gaines");
        onbirinci.put("employee_salary" , 90560);
        onbirinci.put("employee_age" , 30);
        onbirinci.put("profile_image" , "");

        List<Integer> yaslar=new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("statuscode" , 200);
        expectedData.put("besincicalisaninismi" , "Airi Satou");
        expectedData.put("calisansayisi" , 24);
        expectedData.put("sondanikincicalisanmaas" , 106450);
        expectedData.put("onbirincicalisan" , onbirinci);
        expectedData.put("yas" , yaslar);

        return expectedData;
    }

}