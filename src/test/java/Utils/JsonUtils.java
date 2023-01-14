package Utils;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;



public class JsonUtils {


    protected static ObjectMapper mapper;

    static {    //static block works before every process and these object will be created once and used every classes
        mapper = new ObjectMapper();
    }

    //1.Method: This method will convert Json Data to Java Object (De-Serialization)
    //This method will accept two parameters and convert first parameter to second parameter data type
    //by using ObjectMapper class.

    //with this method we are going to convert data. This is recommended way

//kendisine gelen String datayi java objesine donusturur alttaki method
    public static    <T> T  convertJsonToJavaObject(String json, Class<T> cls){  //Generic Method, second T is name of the type
 // <T>:data type                           Class<T> cls :  T is class type here
        T javaResult=null;  //// Data type'i belli olmayan yapılarda T kullanılır.

        try {
            javaResult=  mapper.readValue(json,cls);
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("json file con not convert to java");
        }

        return javaResult;
    }


    //2.Method: This method will convert Java Object to Json Data (Serialization)

    public static    String  convertJavaObjectToJson(Object obj){//Generic Method

        String jsonResult=null;
        try {
            jsonResult=  mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }



}