package APIPackage;

import io.restassured.RestAssured;

public class HardCodedExamples {

    //one thing to remember
    //base URI - base URL
    //end then using when keyword, we will send the end point

    String baseURI =  RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //we need to perform CRUD operations
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQwMTAwNTAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDA1MzI1MCwidXNlcklkIjoiNDcyNSJ9.ik_EFBEA8U1YAmYsdyNeHh74bx4opw0PCOZz4PWupu";

    public void getOneEmployee(){

    }

}
