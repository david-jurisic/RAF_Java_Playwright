@AeviAdmin @UI @AP3ADMIN-761 @AP3ADMIN
Feature: AP3ADMIN-761-Copy of terminal contains same API token in ECR integration

  @TestCaseCode:TC009 @TC009 @Author:Drazen-Kozic @UI @AP3ADMIN-761
  Scenario: Copy of terminal contains same API token in ECR integration
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
    And I should see the "Name" textbox which is enabled and empty
    And I should see the "Organization Unit" dropdown text menu which is enabled and has "" value selected
    And I should see the "Terminal Profile" dropdown menu which is enabled and has "Select" value selected
    And I should see the "ECR Integration" tab which is enabled
    And I should see the "OK" button which is enabled
    When I enter "testTerminal" string into "Name" textbox
    Then the "Name" textbox should contain "testTerminal" string
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
    When I click on the "ECR Integration" tab
    Then I should see the "Device API Token" textbox which is enabled and empty
    And I should see the "Device API Token Reload" button which is enabled
    When I click on the "Device API Token Reload" button
    Then the "Device API Token" textbox should have valid GUID string
    When I click on the "OK" button
    Then I should see the "AEVI Pay Admin | Terminals" page
    And I should see the "Terminals" table
    And I should see "testTerminal" string in row "1" of the "Name" column in "Terminals" table
    When I click on checkbox in row "1" in "Terminals" table
    Then I should see the "Copy" button which is enabled
    When I click on the "Copy" button
    Then I should see the "Copy | Terminal" modal
    And I should see the "Copy Modal Terminal ID" textbox which is enabled and empty
    And I should see the "Copy Modal Terminal ID Reload" button which is enabled
    And I should see the "Copy Modal Organization Unit" dropdown text menu which is enabled and has "ROME Organization 1002 SITE ROSITE02 (1002)" value selected
    And I should see the "Copy Modal Name" textbox which is enabled and empty
    And I should see the "Copy Modal" button which is enabled
    When I enter "testTerminal_copy" string into "Copy Modal Name" textbox
    Then the "Copy Modal Name" textbox should contain "testTerminal_copy" string
    When I click on the "Copy Modal Terminal ID Reload" button
    Then the "Copy Modal Terminal ID" textbox should starts with "ROSITE02" string
    When I click on the "Copy Modal" button
    Then I should see the "Terminal" edit page
    And I should see the "ECR Integration" tab which is enabled
    When I click on the "ECR Integration" tab
    Then the "Device API Token" textbox should contain unique GUID
    When I click on the "OK" button
    Then I should see the "AEVI Pay Admin | Terminals" page
    And I should see the "Terminals" table
    And I should see "testTerminal_copy" string in row "1" of the "Name" column in "Terminals" table
    When I click on checkbox in row "1" in "Terminals" table
    Then I should see the "Delete" button which is enabled
    When I click on the "Delete" button
    Then I should see the "Delete | Terminal" modal
    And I should see the "Delete" modal button which is enabled
    When I click on the "Delete" modal button
    Then I should see the "AEVI Pay Admin | Terminals" page
    And I should see the "Terminals" table
    And I should see "testTerminal" string in row "1" of the "Name" column in "Terminals" table
    When I click on checkbox in row "1" in "Terminals" table
    Then I should see the "Delete" button which is enabled
    When I click on the "Delete" button
    Then I should see the "Delete | Terminal" modal
    And I should see the "Delete" modal button which is enabled
    When I click on the "Delete" modal button
    When I logout from "SMCAdmin" page
    Then I should see the "AEVI Pay Admin | Login" page
