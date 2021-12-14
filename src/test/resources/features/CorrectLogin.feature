@AeviAdminActivity @UI @Activity @AeviAdmin
Feature: Correct Login

  @TestCaseCode:TC0001 @TC0001 @Author:Drazen_Kozic @UI @CorrectLogin
  Scenario: Login to SMCAdmin webpage
    Given I have opened the "SMCAdmin" page
    Then I should see the "Chrome Warning Screen" page
    And I should see the "Advanced" button
    When I click on the "Advanced" button
    Then I should see the "Proceed to" button
    When I click on the "Proceed to" button
    Then I should see the "Login to Data Group" page
    And I should see the "Save Changes" button
    When I click on the "Save Changes" button
    Then I should see the "Organization Units" page
    And I should see the "User Settings" button
    When I click on the "User Settings" button
    Then I should see the "Log Out" button
    When I click on the "Log Out" button
    Then I should see the "Login to Data Group" page
