

package Questions;

        import base_urls.DummyApiBaseUrl;
        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import org.junit.Test;

        import static io.restassured.RestAssured.given;
        import static org.hamcrest.CoreMatchers.hasItem;
        import static org.hamcrest.CoreMatchers.hasItems;
        import static org.hamcrest.Matchers.hasSize;

public class Question6 extends DummyApiBaseUrl {

      /*
        Given
            http://dummy.restapiexample.com/api/v1/employees
        When
          I send a GET request to the Url
       And
           Accept type is "application/json"
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           There should be 24 employees
       And
           "Garrett Winters" should be one of the employee name
       And
           43, 61, and 23 should be among the ages
     */


    @Test
    public void get03() {

        //Set the url
        spec.pathParams("first", "api", "second", "v1", "third", "employees");

        //Set the expected Data
        //Get request and get response
        Response response = given().spec(spec).accept("application/json").when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        //Validation
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("data.id", hasSize(24),
                        "data.employee_name", hasItem("Garrett Winters"),
                        "data.employee_age", hasItems(23, 43, 61));


    }


}