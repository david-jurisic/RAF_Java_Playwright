@AeviAdmin @UI @AP3ADMIN-666 @AP3ADMIN
Feature: AP3ADMIN-666-SiteID already exists after saving a previously created.

  @TestCaseCode:TC001 @TC001 @Author:Drazen-Kozic @UI @AP3ADMIN-666
  Scenario: SiteID already exists after saving a previously created.
    Given I have opened the "SMCAdmin" page
    Then I should see the "Login to Data Group" page
    And I should see the "Save Changes" button which is enabled
    When I click on the "Save Changes" button
    Then I should see the "Organization Units" page
    And I should see the "Form Configs" button in the sidebar menu
    When I click on the "Form Configs" button in the sidebar menu
    Then I should see the "Data Groups" button in the sidebar menu
    When I click on the "Data Groups" button in the sidebar menu
    Then I should see the "Data Groups" page
    And I should see the "New Record" button which is enabled
    And I should see the "Name" textbox which is enabled and empty
    And I should see the "Search" button which is enabled
    When I click on the "New Record" button
    Then I should see the "Data Groups Add" page
    And I should see the "Name" textbox which is enabled and empty
    And I should see the "Status" checkbox which is enabled and "not checked"
    And I should see the "Check Site ID/Terminal ID" checkbox which is enabled and "not checked"
    And I should see the "Parameters" tab which is enabled
    And I should see the "OK" button which is enabled
    When I enter "test1" string into "Name" textbox
    Then the "Name" textbox should contain "test1" string
    When I click on the "Status" checkbox
    Then I should see the "Status" checkbox which is enabled and "checked"
    When I click on the "Check Site ID/Terminal ID" checkbox
    Then I should see the "Check Site ID/Terminal ID" checkbox which is enabled and "checked"
    When I click on the "Parameters" tab
    Then I should see the "TID Generator Template" dropdown menu which is enabled and has "Select" value selected
    When I select "Based on Site ID" string from the "TID Generator Template" dropdown menu
    Then I should see the "TID Generator Template" dropdown menu which is enabled and has "Based on Site ID" value selected
    When I click on the "OK" button
    Then I should see the "Data Groups" page
    And I should see the "Success! Data Group was created successfully." success message
    And I should see the "Close Message" button which is enabled
    When I click on the "Close Message" button
    Then I should see the "Name" textbox which is enabled and empty
    When I enter "test1" string into "Name" textbox
    Then the "Name" textbox should contain "test1" string
    When I click on the "Search" button
    Then I should see the "Data Groups" page
    When I logout from "SMCAdmin" page
    Then I should see the "Login to Data Group" page



