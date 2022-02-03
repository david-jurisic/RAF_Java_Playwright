@AeviAdmin @UI @AP3ADMIN-665 @AP3ADMIN
Feature: AP3ADMIN-665-Unable to create a contract

  @TestCaseCode:TC004 @TC004 @Author:Drazen-Kozic @UI @AP3ADMIN-665
  Scenario: Unable to create a contract
    Given I have opened the "SMCAdmin" page
    Then I should see the "AEVI Pay Admin | Login" page
    And I should see the "Save Changes" button which is enabled
    When I click on the "Save Changes" button
    Then I should see the "AEVI Pay Admin | Organization Units" page
    And I should see the "Contracts" button in the sidebar menu
    When I click on the "Contracts" button in the sidebar menu
    Then I should see the "AEVI Pay Admin | Contracts" page
    And I should see the "New Record" button which is enabled
    When I click on the "New Record" button in the button dropdown menu
    Then I should see the "Choose an option" button dropdown menu
    When I click on the "Add Physical Contract" button in the button dropdown menu
    Then I should see the "AEVI Pay Admin | Contract" page
    And I should see the "Parameters" tab which is enabled
    When I click on the "Parameters" tab
    Then I should see the "Applications Profile" dropdown menu which is enabled and has "Select" value selected
    When I select "ROME ApplicationProfileList (1001)" string from the "Applications Profile" dropdown menu
    Then I should see the "Applications Profile" dropdown menu which is enabled and has "ROME ApplicationProfileList (1001)" value selected
    And I should see the "Accepted Application Profiles" table
    And I should see "ROME CardType BPE 1" string in row "1" of the "Card Type" column in "Accepted Application Profiles" table
    When I logout from "SMCAdmin" page
    Then I should see the "AEVI Pay Admin | Login" page
