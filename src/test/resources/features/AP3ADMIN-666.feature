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
    And I should see the "Name" textbox which is enabled and empty
    And I should see the "Search" button
    When I click on the "New Record" button
    Then I should see the "Data Groups Add" page
    And I should see the "Name" textbox which is enabled and empty
    And I should see the "Status" checkbox which is enabled and "not checked"
    And I should see the "Check Site ID/Terminal ID" checkbox which is enabled and "not checked"
    And I should see the "Parameters" tab
    And I should see the "OK" button
    When I enter "test1" string into "Name" textbox
    Then the "Name" textbox should contain "test1" string
    When I click on the "Status" checkbox
    Then I should see the "Status" checkbox which is enabled and "checked"
    When I click on the "Check Site ID/Terminal ID" checkbox
    Then I should see the "Check Site ID/Terminal ID" checkbox which is enabled and "checked"
    When I click on the "Parameters" tab
    Then I should see the "Parameters" tab
    And I should see the "TID Generator Template" dropdown menu which is enabled and has "Select" value selected
    When I select "Based on Site ID" string form the "TID Generator Template" dropdown menu
    And the "TID Generator Template" dropdown menu should contain "Based on Site ID" string
    When I click on the "OK" button
    Then I should see the "Data Groups" page
    And I should see the "Data Group was created successfully." success message
    And I should see the "Close Message" button
    When I click on the "Close Message" button
    Then I should see the "Name" textbox which is enabled and empty
    When I enter "test1" string into "Name" textbox
    Then the "Name" textbox should contain "test1" string
    When I click on the "Search" button
    Then I should see the "Data Groups" page



