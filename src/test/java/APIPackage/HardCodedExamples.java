package APIPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HardCodedExamples {

    //one thing to remember
    //base URI - base URL
    //end then using when keyword, we will send the end point

    String baseURI =  RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //we need to perform CRUD operations
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQwODc5MTMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDEzMTExMywidXNlcklkIjoiNDcyNSJ9.usxFfksgB6QFw5pwTD8p14ydO3kEG0XxTZ3tzF0816g";
    static String employee_id;

    @Test
    public void getOneEmployee(){
        //prepare the request
        //to prepare the request, we use request specification
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", employee_id);

        //to hit the end point/ to make the request which will return response
        Response response = request.when().get("/getOneEmployee.php");

       // System.out.println(response.asString());
        response.prettyPrint();
        //verifying the status code
        response.then().assertThat().statusCode(200);

        //using jsonpath method, we are extracting the value from the response body
        String firstName = response.jsonPath().getString("employee.emp_firstname");
        System.out.println(firstName);
        //first way of assertion
        Assert.assertEquals(firstName, "sohel");
        //second way of assertion to verify the value in response body using hamcrest matchers
        response.then().assertThat().body("employee.emp_firstname", equalTo("sohel"));
    }

    @Test
    public void createEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json").
                body("{\n" +
                        "  \"emp_firstname\": \"sara\",\n" +
                        "  \"emp_lastname\": \"bou\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2011-01-12\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}");
        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint();
        //verfying the statuys code which is 201
        response.then().assertThat().statusCode(201);
        //getting the employee id from the response and use it as static one
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

}
