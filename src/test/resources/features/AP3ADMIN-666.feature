@AeviAdmin @UI
Feature: AP3ADMIN-666-SiteID already exists after saving a previously created.

  @TestCaseCode:TC0001 @AP3ADMIN-666 @Author:Drazen_Kozic @UI
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
    And I should see the "Form Configs" hyperlink in the sidebar menu
    When I click on the "Form Configs" hyperlink in the sidebar menu
    Then I should see the "Data Groups" hyperlink in the sidebar menu
    When I click on the "Data Groups" hyperlink in the sidebar menu
    Then I should see the "Data Groups" page
    And I should see the "New Record" button
    When I click on the "New Record" button
    Then I should see the "Data Groups Add" page
    And I should see the "Name" textbox which is enabled and empty
    And I should see the "Status" checkbox which is enabled and "not checked"
    And I should see the "Check Site ID/Terminal ID" checkbox which is enabled and "not checked"
    When I enter "test1" string into "Name" textbox
    Then the "Name" textbox should contain "test1" string
    When I click on the "Status" checkbox
    Then I should see the "Status" checkbox which is enabled and "checked"


