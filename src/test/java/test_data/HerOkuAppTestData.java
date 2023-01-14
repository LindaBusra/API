package test_data;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {


    public Map<String, String> bookingdatesMapSetUp(String checkin, String checkout) {


        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", checkin);
        bookingdatesMap.put("checkout", checkout);

        return bookingdatesMap;

    }

    public Map<String, Object> expectedDataSetUp(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates, String additionalneeds) {


        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);
        expectedData.put("additionalneeds", additionalneeds);

        return expectedData;

    }

    //without additionalneeeds
    public Map<String, Object> expectedDataSetUp(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates) {


        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);

        return expectedData;

    }



    public String expectedDataInString(String firstname,String lastname,int totalprice,boolean depositpaid,String checkin,String checkout,String additionalneeds){
        String expectedData=  "{\n" +
                "    \"firstname\": \""+firstname+"\",\n" +
                "    \"lastname\": \""+lastname+"\",\n" +
                "    \"totalprice\": "+totalprice+" ,\n" +
                "    \"depositpaid\": "+depositpaid+" ,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \""+checkin+"\",\n" +
                "        \"checkout\": \""+checkout+"\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \""+additionalneeds+"\"\n" +
                "}";

        return expectedData;



    }



    public HashMap<String, Object> setUpTestData () {

        HashMap<String, Object> bookingDates= new HashMap(); //  Nested Map
        bookingDates.put("checkin","2013-02-23");
        bookingDates.put("checkout","2014-10-23");

        HashMap<String, Object> expectedData= new HashMap(); // Hoved Map Ana Map'imi oluşturdum, ana mapimin içerisindeki

        expectedData.put("firstname" , "Sally");
        expectedData.put("lastname" , "Brown");
        expectedData.put("totalprice" , 111);
        expectedData.put("depositpaid" , true);
        expectedData.put("additionalneeds" , "Breakfast");
        expectedData.put("bookingdates" , bookingDates);   //key: "bookingdates" , value :bookingDates ( nested map)

        return expectedData;
    }


    public JSONObject setUpTestAndRequestData(){

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-02-01" );
        bookingdates.put("checkout", "2022-02-11" );

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("firstname", "Ali" );
        expectedRequest.put("lastname", "Can" );
        expectedRequest.put("totalprice", 500 );
        expectedRequest.put("depositpaid", true );
        expectedRequest.put("bookingdates", bookingdates );

        return expectedRequest;
    }



}

