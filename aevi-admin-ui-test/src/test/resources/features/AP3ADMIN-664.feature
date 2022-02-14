@AeviAdmin @UI @AP3ADMIN-664 @AP3ADMIN
Feature: AP3ADMIN-664-Reloading buttons don't work

  @TestCaseCode:TC001 @TC001 @Author:Drazen-Kozic @UI @AP3ADMIN-664
  Scenario: SiteId Reloading buttons doesn't work
    Given I have opened the "SMCAdmin" page
    Then I should see the "AEVI Pay Admin | Login" page
    And I should see the "Save Changes" button which is enabled
    When I click on the "Save Changes" button
    Then I should see the "AEVI Pay Admin | Organization Units" page
    And I should see the "New Record" button which is enabled
    When I click on the "New Record" button
    Then I should see the "Choose an option" button dropdown menu
    When I click on the "Add Site" button in the button dropdown menu
    Then I should see the "AEVI Pay Admin | Site" page
    And I should see the "Site ID" textbox which is enabled and empty
    And I should see the "Site ID Reload" button which is enabled
    And I should see the "Parent Unit" dropdown text menu which is enabled and has "" value selected
    When I click on the "Parent Unit" dropdown menu
    Then I should see the "Parent Unit" textbox which is enabled and empty
    When I enter "ROME Organization 1001" string into "Parent Unit" textbox
    Then the "Parent Unit" dropdown list should contain "ROME Organization 1001 SITE_GROUP ROSITE01 (1001)" string
    When I click on the "ROME Organization 1001 SITE_GROUP ROSITE01 (1001)" dropdown menu item
    Then I should see the "Parent Unit" dropdown text menu which is enabled and has "ROME Organization 1001 SITE_GROUP ROSITE01 (1001)" value selected
    When I click on the "Site ID Reload" button
    Then the "Site ID" textbox should starts with "ROSIT" string
    When I logout from "SMCAdmin" page
    Then I should see the "AEVI Pay Admin | Login" page

  @TestCaseCode:TC002 @TC002 @Author:Drazen-Kozic @UI @AP3ADMIN-664
  Scenario: TerminalID Reloading buttons doesn't work
    Given I have opened the "SMCAdmin" page
    Then I should see the "AEVI Pay Admin | Login" page
    And I should see the "Save Changes" button which is enabled
    When I click on the "Save Changes" button
    Then I should see the "AEVI Pay Admin | Organization Units" page
    And I should see the "Terminals" button in the sidebar menu
    When I click on the "Terminals" button in the sidebar menu
    Then I should see the "AEVI Pay Admin | Terminals" page
    And I should see the "New Record" button which is enabled
    When I click on the "New Record" button
    Then I should see the "AEVI Pay Admin | Terminal" page
    And I should see the "Terminal ID" textbox which is enabled and empty
    And I should see the "Terminal ID Reload" button which is enabled
    And I should see the "Organization Unit" dropdown text menu which is enabled and has "" value selected
    And I should see the "Terminal Profile" dropdown menu which is enabled and has "Select" value selected
    When I click on the "Organization Unit" dropdown menu
    Then I should see the "Organization Unit" textbox which is enabled and empty
    When I enter "ROME Organization 1002" string into "Organization Unit" textbox
    Then the "Organization Unit" dropdown list should contain "ROME Organization 1002 SITE ROSITE02 (1002)" string
    When I click on the "ROME Organization 1002 SITE ROSITE02 (1002)" dropdown menu item
    Then I should see the "Organization Unit" dropdown text menu which is enabled and has "ROME Organization 1002 SITE ROSITE02 (1002)" value selected
    When I select "ROME TerminalProfile 1002 OPT (1002)" string from the "Terminal Profile" dropdown menu
    Then I should see the "Terminal Profile" dropdown menu which is enabled and has "ROME TerminalProfile 1002 OPT (1002)" value selected
    When I click on the "Terminal ID Reload" button
    Then the "Terminal ID" textbox should starts with "ROSITE02" string
    When I logout from "SMCAdmin" page
    Then I should see the "AEVI Pay Admin | Login" page