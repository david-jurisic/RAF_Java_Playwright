@AeviAdmin @UI @AP3ADMIN-760 @AP3ADMIN
Feature: AP3ADMIN-760-Host Terminal ID is already used for this Acquirer issue on saving terminal with same ID

  @TestCaseCode:TC005 @TC005 @Author:Drazen-Kozic @UI @AP3ADMIN-760
  Scenario: SiteID already exists after saving a previously created.
    Given I have opened the "SMCAdmin" page
    Then I should see the "AEVI Pay Admin | Login" page
    And I should see the "Save Changes" button which is enabled
    When I click on the "Save Changes" button
    Then I should see the "AEVI Pay Admin | Organization Units" page
    And I should see the "Advanced Configs" button in the sidebar menu
    When I click on the "Advanced Configs" button in the sidebar menu
    Then I should see the "Acquirers/Service Providers" button in the sidebar menu
    When I click on the "Acquirers/Service Providers" button in the sidebar menu
    Then I should see the "AEVI Pay Admin| Acquirers/Service Providers" page
    And I should see the "Acquirers/Service Providers" table
    And I should see "ROME Acquirer 1001" string in row "1" of the "Name" column in "Acquirers/Service Providers" table
    When I click on the row "1" in "Acquirers/Service Providers" table
    Then I should see the "Acquirers/Service Provider" edit page
    And the "Name" textbox should contain "ROME Acquirer 1001" string
    And I should see the "Host TID Type" dropdown menu which is enabled and has "Default ID" value selected
    And I should see the "Host Device ID Type" dropdown menu which is enabled and has "Select" value selected
    And I should see the "OK" button which is enabled
    When I select "Edit ID" string from the "Host TID Type" dropdown menu
    Then I should see the "Host TID Type" dropdown menu which is enabled and has "Edit ID" value selected
    When I select "Edit ID" string from the "Host Device ID Type" dropdown menu
    Then I should see the "Host Device ID Type" dropdown menu which is enabled and has "Edit ID" value selected
    When I click on the "OK" button
    Then I should see the "AEVI Pay Admin| Acquirers/Service Providers" page
    And I should see the "Success! Acquirer/Service Provider was updated successfully." success message
    When I click on the "Organizations & Terminals" button in the sidebar menu
    Then I should see the "Terminals" button in the sidebar menu
    When I click on the "Terminals" button in the sidebar menu
    Then I should see the "AEVI Pay Admin | Terminals" page
    And I should see the "Terminals" table
    And I should see "ROME Terminal IPT 1" string in row "1" of the "Name" column in "Terminals" table
    When I click on the row "1" in "Terminals" table
    Then I should see the "Terminal" edit page
    And the "Name" textbox should contain "ROME Terminal IPT 1" string
    And the "ROME Acquirer 1001 Terminal ID" textbox should contain "4444444443" string
    And I should see the "ROME Acquirer 1001 Device ID" textbox which is enabled and empty
    And I should see the "Save" button which is enabled
    When I click on the "Save" button
    Then I should see the "Success! Terminal was updated successfully." success message




