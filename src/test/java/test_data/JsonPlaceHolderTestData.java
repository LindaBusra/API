package test_data;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
public class JsonPlaceHolderTestData {


    public Map<String, Object> expectedDataJPH(Integer userId, String title, Boolean completed ){


        Map<String,Object> expectedData=new HashMap<>();
        if(userId!=null){
            expectedData.put("userId",userId);
        }
        if (title!=null){
            expectedData.put("title",title);

        }
        if (completed!=null){
            expectedData.put("completed",completed);
        }
        return expectedData;

}


    public String expectedDataInString(Integer userId,String title,Boolean completed){
        String expectedData=   "{\n" +
                "   \"userId\": "+userId+"  ,\n" +
                "   \"title\": \""+title+"\",\n" +
                "   \"completed\": "+completed+"\n" +
                "  }";

        return expectedData;

    }




    public Map<String,Object> expectedDataWithAllKeys(Integer userId,String title,Boolean  completed){
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",userId);
        expectedData.put("title",title);
        expectedData.put("completed",completed);

        return expectedData;

    }



    public Map<String, Object> setUpTestData() {

        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("completed", false);
        expectedData.put("statusCode", 200);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("userId",1);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("Server", "cloudflare" );

        return expectedData;
    }


         /*
        {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
     */

    public JSONObject setUpPostData(){

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId",55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put("completed", false);
        expectedRequest.put("statusCode", 201);

        return  expectedRequest;
    }


    public JSONObject setUpTestAndRequestData() {

        JSONObject expectedRequest=new JSONObject();

        expectedRequest.put("statuscode", 201);
        expectedRequest.put("userId", 55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put("completed", false);


        return expectedRequest;
    }




    public JSONObject setUpPutData() {

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId" , 333);
        expectedRequest.put("title" , "Come Back Here!");
        expectedRequest.put("completed" , false);

        return expectedRequest;

    }


    //---------------------------------------------------------------------------------------------------------------

    public JSONObject setUpPatchRequestData() {

        JSONObject requestData=new JSONObject();
        requestData.put("title" , "I have to work with API");
        return requestData;
    }



    public JSONObject setUpPatchExpectedData() {

        JSONObject expectedData=new JSONObject();
        expectedData.put("title" , "I have to work with API");
        expectedData.put("userId" , 10);
        expectedData.put("completed" , true);
        expectedData.put("id" , 198);  // Patch işleminde datayı komple degiştirmiyoruz bu yüzden yeni bir
        // ID atamıyoruz,mevcut ID ile tekrardan geliyor.Bekledigim ID numarası bu..Bu ID no ile bana response yapacak.

        return expectedData;
    }



    public static Map<String, Object> expectedDatasetup() {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 10);
        expectedData.put("id", 198);
        expectedData.put("title", "Read the books");
        expectedData.put("completed", true);

        return expectedData;
    }

    public static Map<String, Object> expectedDataPut() {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 21);
        expectedData.put("title", "Wash the forks");
        expectedData.put("completed", false);

        return expectedData;
    }



//    public Map<String,Object> expectedDataWithAllKeys(Integer userId, String title, Boolean completed){
//        Map<String, Object> expectedData = new HashMap<>();
//        expectedData.put("userId",userId);
//        expectedData.put("title", title);
//        expectedData.put("completed",completed);
//
//        return expectedData;
//    }


    public Map<String,Object> expectedDataWithMissingKeys(Integer userId, String title, Boolean completed){
        Map<String, Object> expectedData = new HashMap<>();
        if(userId!=null){
            expectedData.put("userId",userId);
        }
        if(title!=null){
            expectedData.put("title",title);
        }
        if(completed!=null){
            expectedData.put("completed",completed);
        }

        return expectedData;
    }



}
