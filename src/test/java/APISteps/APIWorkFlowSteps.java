package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstant;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class APIWorkFlowSteps {
    RequestSpecification request;
    Response response;
    public static String employee_id;

    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
         request = given().
                header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type).
                header(APIConstants.Header_Key_Authorization, GenerateTokenSteps.token).
                body(APIPayloadConstant.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
         response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
         response.prettyPrint();
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer sholpan) {
        response.then().assertThat().statusCode(sholpan);
    }

    @Then("the response body contains key {string} and value {string}")
    public void the_response_body_contains_key_and_value(String key, String value) {
        //arguments for key and value is coming from our feature file, alright sholpan
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as global to be used for other request")
    public void the_employee_id_is_stored_as_global_to_be_used_for_other_request(String empId) {
        employee_id = response.jsonPath().getString(empId);
        System.out.println(employee_id);
    }



// ---------------------------------------------------------------------------------------------------------

    @Given("a request is prepared for getting a created employee")
    public void a_request_is_prepared_for_getting_a_created_employee() {
        request = given().
                header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type)
                .header(APIConstants.Header_Key_Authorization, GenerateTokenSteps.token).
                queryParam("employee_id", employee_id);
    }

    @When("a GET call is made to get this employee")
    public void a_get_call_is_made_to_get_this_employee() {
       response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for this emp is {int}")
    public void the_status_code_for_this_emp_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Then("the employee id {string} should match with global emp id")
    public void the_employee_id_should_match_with_global_emp_id(String string) {
        String temporaryEmpId = response.jsonPath().getString(string);
        Assert.assertEquals(temporaryEmpId, employee_id);
    }


}
