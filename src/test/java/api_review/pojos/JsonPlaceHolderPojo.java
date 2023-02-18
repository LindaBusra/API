package api_review.pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//https://www.jsonschema2pojo.org/   -->to create pojo class

    /*
    {
    "userId" : 10,
    "title": "quis eius est sint explicobo",
    "completed": true
     */

@JsonIgnoreProperties(ignoreUnknown = true)  //Java will ignore the keys which does not exist inside the pojo.
public class JsonPlaceHolderPojo {

    //POJO in Java stands for Plain Old Java Object. It is an ordinary object, which is not bound by any special restriction.

    //Create private variables:
    private Integer userId;
    private String title;
    private Boolean completed;

    //Create Constructors


    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {
    }

    //Generate getters and setters

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

    //Generate toString()
    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
