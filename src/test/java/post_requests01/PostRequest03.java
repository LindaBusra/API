package post_requests01;


//import base_urls.MedunnaBaseUrl;
//
//import org.junit.Test;
//import pojos.Registrant;
//
//
//
//public class PostRequest03 extends MedunnaBaseUrl {
//
//    // Send the post request to the url https://medunna.com/api/register
//
//    /*
//    create a new user for Medunna project
//    status code should be 200
//            {
//            "activated":true,
//            "authorities":[
//            "string"
//            ],
//            "createdBy"::string,
//            "createdDate":"2022-01-03T19:25:02.0752",
//            "email":"string"
//            "firstName":"string",
//            "id":0,
//            "imageUrl":"string",
//            "langKey":"string",
//            "lastModifiedBy":"string",
//            "login":"string",
//            "password":"string",
//            "ssn":"string"
//            }
//     */
//
//    @Test
//    public void post04() {
//        //Set the base url
//        spec.pathParams("bir", "api", "iki", "register");
//
//        //Set the expected Data
//        Registrant registrant = new Registrant();
//        Faker faker = new Faker();
//        registrant.setFirstName(faker.name().firstName());
//        registrant.setLastName(faker.name().lastName());
//        registrant.setPassword(faker.internet().password(8, 25, true, true));
//        registrant.setEmail(registrant.getFirstName() + registrant.getLastName() + "@gmail.com");
//        registrant.setLogin(registrant.getFirstName() + registrant.getLastName());
//        registrant.setSsn(faker.idNumber().ssnValid());
//
//        String fileName = "C:/Users/gokha/IdeaProjects/devapi/test_data/RegistrantData.txt";
//
//        saveRegistrantData(fileName, registrant);
//
//
//    }
//
//
//}