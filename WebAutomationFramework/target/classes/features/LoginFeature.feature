Feature: Login Functionality
	
	
  @Register
  Scenario: Register as a new user with valid input
    Given User is on Farnell Home Page
    When User clicks Register button and navigates to Registration page
    And User enters Username "pragatheesh66" and Password "FanPremHomeTest@123"
    And User clicks Register
    Then User should register successfully to the Farnell site
    
  @Check
  Scenario: Successful Login with Valid Credentials
    Given User is on Farnell Home Page
    When User clicks Login button and navigates to Login page
    And User enters Username "pragatheesh66" and Password "FanPremHomeTest@123"
    And User clicks LogIn
    Then User should login successfully to the Farnell site and navigate to account summary
    And Logout from application and log in again

  @Positive
  Scenario: Ability to Reset Password
    Given User is on Farnell Home Page
    When User clicks Login button and navigates to Login page
    And User forgot password and clicks forgot password link
    Then User should be allowed to request a reset password email

  @Positive
  Scenario: Validate the userID auto populates when the remember me button is checked
    Given User is on Farnell Home Page
    When User clicks Login button and navigates to Login page
    And User enters Username "pragatheesh66" and Password "FanPremHomeTest@123"
    And User "Check" remember me button and clicks log in button
    Then User should login successfully to the Farnell site and navigate to account summary
    When Logout from application and log in again
    Then Username "pragatheesh66" should be auto populated
    
  @Positive
  Scenario: Validate the userID is empty when the remember me button is unchecked
    Given User is on Farnell Home Page
    When User clicks Login button and navigates to Login page
    And User enters Username "pragatheesh66" and Password "FanPremHomeTest@123"
    And User "Uncheck" remember me button and clicks log in button
    Then User should login successfully to the Farnell site and navigate to account summary
    When Logout from application and log in again
    Then Username should be empty

  @Negative
  Scenario: UnSuccessful Login with empty Credentials
    Given User is on Farnell Home Page
    When User clicks Login button and navigates to Login page
    And User enters Username "pragatheesh66" and Password ""
    And User clicks LogIn
    Then User should get "pragatheesh66" error message for username and password fields

  @Negative
  Scenario Outline: UnSuccessful Login with incorrect Credentials
   	Given User is on Farnell Home Page
    When User clicks Login button and navigates to Login page
    And User enters Username "<UserName>" and Password "<Password>"
    And User clicks LogIn
    Then User should get "<ExpectedErrorMessage>" SignIn error message and "<ExpectedWarningMessage>" Warning Message
    Examples:
      |UserName|Password|ExpectedErrorMessage|ExpectedWarningMessage|
      |Pragatheesh66|abcd1234|There is a problem with the following|Either the username or password is incorrect. Please check and re-enter the information again.|
      |Pragatheesh|FarnellHomeTest@123|There is a problem with the following|Either the username or password is incorrect. Please check and re-enter the information again.|
      |Praga|abcd1234|There is a problem with the following|Either the username or password is incorrect. Please check and re-enter the information again.|
