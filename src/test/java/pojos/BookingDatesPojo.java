package pojos;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
//While converting Json data to Pojo class with this annotation, Json data that has no equivalent in Pojo class
//is not processed.

public class BookingDatesPojo {

//https://json2csharp.com/code-converters/json-to-pojo  --easy way to create pojo


    //1) We create private variables

/*
 "checkin": "2020-09-09",
 "checkout": "2020-09-21"
*/

    private String checkin;
    private String checkout;



    //2) We create constructors  -->rigt click, generate, constructors, select all and select none


    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {
    }



    //3) Getters and Setters  -->rigt click, generate, getter and setter


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }



    //4) toString() method


    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }


}