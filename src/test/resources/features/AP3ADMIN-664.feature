@AeviAdmin @UI @AP3ADMIN-664 @AP3ADMIN
Feature: AP3ADMIN-664-Reloading buttons don't work

  @TestCaseCode:TC001 @TC001 @Author:Drazen-Kozic @UI @AP3ADMIN-664
  Scenario: SiteId Reloading buttons doesn't work
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
    And I should see the "New Record" button which is enabled
    When I click on the "New Record" button
    Then I should see the "Choose an option" button dropdown menu
    And I Should see "Add Site" which is enabled
    When I click on the "Add Site" button in the button dropdown menu
    Then I should see the "AEVI Pay Admin| Site" page
    And I should see the "Site ID" textbox which is enabled and empty
    And I should see the "Site ID Reload" button which is enabled
    And I should see the "Parent Unit" dropdown menu which is enabled and has "" value selected
    When I click on the "Parent Unit" dropdown menu
    Then I should see the "Parent Unit" textbox which is enabled and empty
    When I enter "ROME" string into "Parent Unit" textbox
    Then the "Parent Unit" textbox should contain "ROME Organization 1001 SITE_GROUP ROSITE01 (1001)" string
    When I click on the "Site Id Reload" button
    Then the "Name" textbox should contain "ROSITED" string





