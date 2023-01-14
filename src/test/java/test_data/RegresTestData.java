package test_data;

import java.util.HashMap;
import java.util.Map;

public class RegresTestData {


    public Map<String,String > regresTestData(String name,String job){

        Map<String ,String> regresTestData = new HashMap<>();
        regresTestData.put("name",name);
        regresTestData.put("job",job);


        return regresTestData;
    }



}
