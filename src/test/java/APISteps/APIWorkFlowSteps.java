package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;
import utils.APIPayloadConstant;

import static io.restassured.RestAssured.given;

public class APIWorkFlowSteps {
    RequestSpecification request;
    Response response;

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


}
