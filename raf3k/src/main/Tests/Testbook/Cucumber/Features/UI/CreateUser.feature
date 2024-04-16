@HTInfonova @UI @CreateUser
Feature: CreateUser
  As a tester, I want to test the functionality of the Creating User on the HT Infonova.

  @TestCaseCode:TC0002 @Author:Bernardo_Kopjar @UI @Xray:TES-2
  Scenario Outline: Create User
    Given I navigate to "HT Infonova" page
    Then I should see "Login" page
    When I enter corresponding string into "Username" textbox
    And I enter corresponding string into "Password" textbox
    And I click on "Log In" button
    Then I should see "Main" page
    When I click on "Menu" button
    And I select "Customers" item in "Menu" list
    Then I should see "Customers" page
    When I enter corresponding string into "Search Customers" textbox
    And I click on "Search" button
    Then I should see "Search for Customers" table that is "enabled" and "not empty"
    When I select corresponding "customer" in "Search for Customers" table
    Then I should see "Overview" page
    When I select "Account" option in "Customer Menu" list on left part of the page
    Then I should see "Account" list on left part of the page that is "enabled" and "not empty"
    When I select "Users" option in "Account" list
    Then I should see "Users" page
    When I click on "Add User" button
    Then I should see New User popup
    When I enter "<LastName>" string into "Last name" textbox
    And I enter "<FirstName>" string into "First name" textbox
    And I enter "<Email>" string into "Email" textbox
    And I select "USER" in "User role" dropdown menu
    Then I should see "Save Changes" button that is "enabled"
    When I click on "Save Changes" button
    Then I should see "Users" page

    Examples:
      | LastName | FirstName | Email           |
      | Marković | Marko     | roxoft@mail.com |