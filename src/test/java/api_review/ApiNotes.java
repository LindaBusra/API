package api_review;

public class ApiNotes {

    /*
    API stands for Application Programming Interfaces. API don't have any User Interfaces. Our application is not visible.
    What will application will do? Just we imagine this.
    The tasks are executed in a short time.

    Why do we use API?  It used to communicate with other applications.
    When you buy a product from *amazon. amazon application needs to communicate with visa card application.

    Amazon is an application and visa card is an application, they should communicate with each other.
    Amazon will send card holder name, expression date, security code. Visa card will check these information and visa card sending money to Amazon.
    Amazon sending a request to Visa. If amazon does not any API, it can not send request to visa card.
    If Visa card has not API it can not take request form Amazon.

    Amazon----->API-----------request----------->API--------> Visa
    Amazon<-----API<----------response-----------API<-------- Visa
    If visas API can understand request it send it to Visa. If it can not understand the request, it will reject the request
    if everything is ok, visa send response... is Amazons API, can understand response, response sends to Amazon

    By API request is understood and passed to Visa.
    APIs getting request and sending response.  API is like waitress in a restaurant.

    API is created by API developers.

    Fullstack means you know everything about automation. In every application there three main things:
    1-User Interface-->We learn Selenium for it.
    2-Database -->With Selenium vi make database testing, we use SQL knowledge in database testing.
    3-API --> We will do API testing in Selenium

    Amazon API can send request in specific formats. for example in json format. but visas API does not accept json format
    So they can not communicate with each other. API should accept multiple data format. (json format is similar with maps)
    Most of the time we use xml or json format in API for the request and for response. json is more command. (xml format is similar with html format)

    We will use Rest Assured library. and this library is able to use multiple format.

    When you create an API you need some endpoints.

    API---------------------->Database
    API can insert some data to database
    API can do update operations in database
    API can delete something from database.
    API can read/ get data from database.

    We should give an url for these operations. all url are called end point.
    Why do we need multiple url for insert operations? Because sometimes we will insert student in to database, sometimes parents...
    For different insert operations we need different endpoint. (same for the others update...)

    !!! As an automation tester you will check every endpoints, it is working as expected or not.
    You will create endpoints for every scenario. if I insert students, if I inserts parents... If you miss any scenario, the API fails...

    You will insert parent in to database. You can create some scenarios according to requirement documents.
    1.put firstname, lastname, address
    2-don't put firstname
    3-don't put address
    4-send empty data.

    As an API automation tester you will check all endpoints if they are working as expected.

    How will we understand expected conditions?   https://petstore.swagger.io/  --> What is the expected behaviour for given endpoint



    HTTP REQUESTS METHODS:
    1-GET Method: It is for reading data from database  (Read -->R in CRUD)
            To be able to use GET method we need just "endpoint" (url). If you have valid endpoint you can get data from API.
    2-POST Method: It is for creating new data in database  (Create -->C in CRUD)
            To be able to send POST request we need endpoint + Request Body
    3-PUT Method: It is for fully update in database (Update -->U in CRUD)
            To be able to send PUT request we need endpoint + Fully Request Body
    4-PATCH Method: It is for partial update in database (Update -->U in CRUD)  (if you want to update just the address, just single part)
            To be able to send PATCH request we need endpoint + Partial Request Body
    5-DELETE Method: It is for deleting data from database (Delete -->D in CRUD)
            To be able to send DELETE a record in database we need endpoint.  (We don't need request body)



    Base URL: petstore.swagger.io/v2  --> endpoint Base URL: petstore.swagger.io/v2/pet/{petId}/uploadImage
    (Petstore url =https://petstore.swagger.io/#/)

    for example  POST:   /pet/{petId}/uploadImage -->we create data with these credentials.
    So we need to create scenarios with /pet/{petId}/uploadImage or /pet/{petId} or /pet/uploadImage ...
    For this endpoint id is required, so without id I can not create a data in database.

   In swagger document response is always in json format.

   HTTP Status Codes:
   1xx: It means the request is received and the process is continuing.
   2xx: It means the action was successfully received, understood and accepted. (API working as expected)
   3xx: It means further action must be taken to complete the request. Check your request, you maybe mist some part in your code.
   4xx: It means the request contains incorrect syntax or can not be fulfilled.
   5xx: It means server is down. Try a couple of times for you will be sure it is 500. Ask the others: I have 500 error, does anybody have same error?


   The API testing what dou test first?  first step is checking HTTP status code.
   Note:In API Testing must be tested first. If the status code is 2xx you can test the details.

   POSTMAN: It is an application to test endpoints of an API manually. Postman is for manual API testing
   Postman is an User Interface. It is used to test APIs manually.

   REST ASSURED LIBRARY: It is a library in Java, it is used to test endpoints in automation.
   For automation I used Rest Assured Library.

   For automation testing first step is manual testing.

   In Postman I send a request (https://petstore.swagger.io/v2/store/inventory) the Petstore API with GET, it send me a response.
   In swagger document, that endpoint needs GET method. It is for reading data. When we see 200, we can start to check response body.
   If any details is different (in postman response and in swagger) it means it will fail manually. But you can confirm it is problem to have different for example string.

   What do you need to get request method? To be able to use GET method we need just endpoint (url)

   TIME:
   You are functional automation tester. There is performance tester, they check speed. Time part will be used in performance testing.
   Time:357 ms -->It is execution time. That value is used in performance testing.

   SIZE: Size of data in response body.
   We can also save response but we don't use it much.

   Header Part:
   Every time API connection must be keep-alive
   Access-Control-Allow-Methods:Which methods this API using? GET, POST, DELETE, PUT (It means developer did not create Patch Method)

   Encoding means, the messages, the request which you send to API, and the response which is coming from API are encoded to make it more secure.

   Cookies:When you go to into an application/webside, application get your information, your IP address, your username.
   And application asks you if you want,  I can save your information for next time/next visit for make it faster. If you accept cookies, application will create a
   small area in your local computer/laptop. And it will put your information in to this area. Another application will use your memory if you let to use.
   In the next time when you visit the same webside, it will go to the area which is created in memory, It take all information from there, and
   it will be faster. If you accept the cookies when you visit a webside in first time, the next time it will be faster. Make sure the webside is trustable.

   Postman-->No cookies yet--> The API does not created cookies yet.

   POST: (https://petstore.swagger.io/v2/pet) to create a data, you need data. So we need body.   POST-->raw-->json-->body (copy body from swagger)
   I created a new path/data in database. most of the time API create id automatically for every new data.

   In Postman we have  to part for body. 1-Request body     2-Response body (id is related with API, the others details must be match between request and response,
   and you can se that in documentation, which one must me match, which one must not be match)

   Parameter content type:
   When we read data we use just json.
   When we create new data we can use json and xml format.

   Access-Control-Allow-Headers: Content-Type, api_key, Authorization -->It means Content type is important for access, api_key means password

   Authorization: Security level
   Authorization -->1-Base authorization: username and password-->with these you can use API
                    2-Role based authorization: For example if developer dont declare you prinsible you can not use API
                    3-

   PUT-->copy request body from swagger.   And copy request url (https://petstore.swagger.io/v2/pet)
   paste them in to Postman. Body-->raw-->json

   In petstore-->pet--->delete-->it will api_ket. Without it we can not delete anything, it is for to save database.



   We use Gherkin Language to create test cases. It is making test cases more understandable. (To automate something we should create test cases) Gherkin Language has 4 keywords.: Given, When, Then, And
   Given: It used for pre-requisites.  (Given url)
   When: It used for action (after when I am doing an action)
   Then: It is used for output (result of the action)
   And:  It is used for multiply Given, When, Then

   Test Case:
   Given
        https://restful-booker.herokuapp.com/booking/3
   When
        User send GET Request to the URL
   Then
        HTTP Status Code should be 200
   And
        Content type should application/json
   And
        Status line should be like HTTP/1.1 200 OK


    In automation we have 3 steps: 1-Create test cases 2-Doing manuel test (Postman)    3-Type automation script


    //--------------------------------------------------------------------------------------------------------------------------------

    https://dummy.restapiexample.com/  -->example for delete


    syntax for json data: it is similar with maps. We have curly braces and  key-value par.
{
    "userId": 2,
    "id": 23,
    "title": "et itaque necessitatibus maxime molestiae qui quas velit",
    "completed": false
}

    Different between json format and map.  In map we use equals sign (=) between key and value, in json format we use (:) semikolon


    www.jsonpath.heroku.app -->Goessner example--> below body -->if you write $ and clcik GO--->all body data is in right window

    $.store -->It gives  store body
    $.store.book -->it gives book body   (we use just key name)
    $.store.book[0] -->it gives first json in book body
    $.store.book[-1] -->it gives last json in book body
    $.store.book[-2] -->it gives second last json in book body
    $.store.book[0].author -->it gives author in first json in book body (by getting some details from json, we can do some assortions)
    $.store.book[*].author -->it gives all author in json in book


    If you want to see multiple elements:
    $.store.book[0,3,1]
    $.store.book[0,-1]


    //dependencies:


    ENUM: Enums are storages for constant values.  For sample state names, for days...

    public enum ContentType(){

    }


    Create a new project-->select maven--<next-->give name-->finish

    Junit is a framework. Everything is ready to test functionality. It has almost everything which a tester needs.
    TestNG is a framework, and it has les features, but it is very simple. Because of it is simple, it is common on the market.
    It is not complicated so it gives les error.

    RestAssured library is the most comman library in the market. It uses for automation testing. given() is from RestAssured library for example

    Post-put-delete methods need authentication

    Framework-Structure, package for:
    test_data
    base_url -->parent
    get
    post
    put
    patch
    delete
    report-->error messages, screen shots
    utils-->for reusable methods like takeScreenShot()


    Postman: POST-->https://jsonplaceholder.typicode.com/todos
    Body-->raw,  text:JSON
                {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false
               }
    Authorization -->Type-->Basic authorization--> it means basic credentials like username and password
    username:admin
    password:1234
    When wi click send button, it will be send the request with username and password.
    API first check username and password. If they are correct, API allow as to create a new data.
    Click Send...    (Status:201 Created)
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": 201
}


pojo:plain old java object


    //https://www.jsonschema2pojo.org/   -->to create pojo class
    //Package:pojo,      Class name:BookingPojo,   Source Type:Json,    Annotation style:None,
    //Select:Include getters and setters, Include constructors, Include toString
    paste your body here -->Preview


    The biggest challenge in API Testing is data types.
    1)Java uses Objects and Primitives as data type, API uses XML, Json, etc
    Java and API are using different data types, but they should communicate with each other.
    Communication is impossible with different data types.

    We have 2 options:
    1-Convert Json to Java Object-->De-Serialization
    2-Convert Java Object to Json-->Serialization

    For Serialization and De-serialization we have 2 options
    a) GSON-->Google created it
    b) Object Mapper -->More popular
     */
}
