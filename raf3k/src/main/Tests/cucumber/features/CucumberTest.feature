@KaleLogin @UI @Login @Kale
Feature: Kale Login
  As a developer I want to test the functionality of the Login page on the Kale app.

  @Test @TestCaseCode:CCTC001 @CCTC001 @Author:Drazen_Kozic @UI @CorrectLogin
  Scenario: Correct Login
    Given I navigate to the "Login" page
    Then I should see the "Login" page
    And I should see the "Email" textbox which is enabled and empty
    And I should see the "Password" textbox which is enabled and empty
    And I should see the "Remember me" checkbox which is enabled and "not checked"
    And I should see the "Sign in" button which is enabled
    When I enter "admin@roxoft.hr" string into "Email" textbox
    Then the "Email" textbox should contain "admin@roxoft.hr" string
    When I enter "RoxoftKale123" string into "Password" textbox
    Then the "Password" textbox should contain "RoxoftKale123" string
    When I click on the "Remember me" checkbox
    Then the "Remember me" checkbox should be checked
    When I click on the "Sign in" button
    Then I should see the "Home" page
    And I should see the "Sign out" button which is enabled
    When I click on the "Sign out" button
    Then I should see the "Login" page

  @Test @TestCaseCode:CCTC002 @CCTC002 @Author:Ivan_Cukovic @UI @IncorrectLogin
  Scenario: Incorrect Login
    Given I navigate to the "Login" page
    Then I should see the "Login" page
    And I should see the "Email" textbox which is enabled and empty
    And I should see the "Password" textbox which is enabled and empty
    And I should see the "Remember me" checkbox which is enabled and "not checked"
    And I should see the "Sign in" button which is enabled
    When I click on the "Sign in" button
    Then I should see the "Email" label message saying "The Email field is required."
    And I should see the "Password" label message saying "The Password field is required."
    When I enter "mymail@mail.com" string into "Email" textbox
    Then the "Email" textbox should contain "mymail@mail.com" string
    When I enter "ThreeTwoOne" string into "Password" textbox
    Then the "Password" textbox should contain "ThreeTwoOne" string
    When I click on the "Sign in" button
    Then I should see the "Invalid login" label message saying "Invalid login attempt."
    And the "Email" textbox should contain "mymail@mail.com" string
    And the "Password" textbox should be empty
    When I delete string in "Email" textbox
    Then the "Email" textbox should be empty
    When I enter "<adminEmail>" string into "Email" textbox
    Then the "Email" textbox should contain "<adminEmail>" string
    When I enter "OneTwoThree" string into "Password" textbox
    Then the "Password" textbox should contain "OneTwoThree" string
    When I click on the "Sign in" button
    Then I should see the "Invalid login" label message saying "Invalid login attempt."
    And the "Email" textbox should contain "<adminEmail>" string
    When I delete string in "Email" textbox
    Then the "Email" textbox should be empty
    When I enter "mymail.com" string into "Email" textbox
    Then the "Email" textbox should contain "mymail.com" string
    When I enter "<adminPassword>" string into "Password" textbox
    Then the "Password" textbox should contain "<adminPassword>" string
    And I should see the "Email" label message saying "The Email field is not a valid e-mail address."