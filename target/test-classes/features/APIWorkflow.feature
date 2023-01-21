Feature: API workflow test

  Background: for generating the token before every request
    #to generate the token for all the request, we kept it here in background
    Given a JWT is generated

  @api
  Scenario: API test case for creating the employee
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201







