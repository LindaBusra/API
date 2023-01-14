package pojos;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class JsonPlaceHolderPojo {
    /*
    1) Create private variables for all keys  //for security
    2) create constructors with all parameters and without any parameter
    3) create public getters and setters methods
    4) Create toString() method
     */

    /*  to ignore mismatch of different key value binaries
      We can solve @JsonIgnoreProperties(ignoreUnknown = true) by writing annotation at the beginning of our pojo class
      */


    //1.step
    private Integer userId;
    private String title;
    private  Boolean completed;



    //2.step

    public JsonPlaceHolderPojo() {

    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    //3) getters and setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    // 4) Create toString().


    @Override
    public String toString() {   //convert the data toString to readable format
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

    /*
    How do we handle different key-values in response?
    We use @JsonIgnoreProperties(ignoreUnknown = true) annotation at the top of the Pojo class.
    It comes from "org.codehaus.jackson.annotate.JsonIgnoreProperties"
     */