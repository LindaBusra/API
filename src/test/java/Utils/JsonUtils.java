package Utils;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;



public class JsonUtils {


    protected static ObjectMapper mapper;

    static {    //static block works before every process and these object will be created once and used every classes
        mapper = new ObjectMapper(); //if you write the initialization code inside the static block your object will be ready together with class.
    }

    //1.Method: This method will convert Json Data to Java Object (De-Serialization)
    //This method will accept two parameters and convert first parameter to second parameter data type
    //by using ObjectMapper class. with this method we are going to convert data. This is recommended way


    //This method will get json and will produce a java object.   (equalTo() method is also generic method baceuse it works with all data types)
    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){  /*Generic Method, second T is name of the type, generic method can work with every data type.
    <T>:data type     <T> T :It means every data type can be accepted.          Class<T> cls :  T is class type here-->Expected java object
    While I use that method convertJsonToJavaObject(), I will select my data type.  Class<T> cls can be HashMap, can be Set...
    With this method I am able to convert json to java object. */

        T javaResult=null;  //Why we don't use Object?  Object is general class, we need to use a specific class to return that method.  */

        try {
            javaResult=  mapper.readValue(json,cls);
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("Json could not be converted to Java Object" + e.getMessage());
        }

        return javaResult;
    }


    //2.Method: This method is used to convert Java Object to Json Data (Serialization)

    public static    String  convertJavaObjectToJson(Object obj){//Generic Method

        String jsonResult=null;     //This is local variable.Local variables can not be use without initialization. If it is instance variables, no need to initialization.
        try {
            jsonResult=  mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.err.println("Java Object could not be converted to Json" + e.getMessage());
        }

        return jsonResult;
    }



}