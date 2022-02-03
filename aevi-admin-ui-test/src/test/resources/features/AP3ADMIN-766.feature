@AeviAdmin @UI @AP3ADMIN-766 @AP3ADMIN
Feature: AP3ADMIN-766-[REST Onboarding API] [Contracts] UI Contract Code

    @TestCaseCode:TC005 @TC005 @Author:Drazen-Kozic @UI @AP3ADMIN-766
    Scenario: [REST Onboarding API] [Contracts] UI Contract Code
        Given I have opened the "SMCAdmin" page
        Then I should see the "AEVI Pay Admin | Login" page
        And I should see the "Save Changes" button which is enabled
        When I click on the "Save Changes" button
        Then I should see the "AEVI Pay Admin | Organization Units" page
        And I should see the "Contracts" button in the sidebar menu
        When I click on the "Contracts" button in the sidebar menu
        Then I should see the "AEVI Pay Admin | Contracts" page
        And I should see the "New Record" button which is enabled
        When I click on the "New Record" button
        Then I should see the "Choose an option" button dropdown menu
        When I click on the "Add Physical Contract" button in the button dropdown menu
        Then I should see the "AEVI Pay Admin | Contract" page
        And I should see the "Contract Number" textbox which is enabled and empty
        And I should see the "Contract Code" textbox which is enabled and empty
        And I should see the "Acquirer/Service Provider" dropdown menu which is enabled and has "Select" value selected
        And I should see the "Site" dropdown text menu which is enabled and has "" value selected
        And I should see the "Valid From" textbox which is enabled and has today's date value selected
        And I should see the "OK" button which is enabled
        And I should see the "Save" button which is enabled
        And I should see the "Parameters" tab which is enabled
        When I click on the "Parameters" tab
        Then I should see the "Applications Profile" dropdown menu which is enabled and has "Select" value selected
        And I should see the "Merchant Category Code" textbox which is enabled and empty
        And I should see the "Basic Parameters" tab which is enabled
        When I select "ROME ApplicationProfileList (1001)" string from the "Applications Profile" dropdown menu
        Then I should see the "Applications Profile" dropdown menu which is enabled and has "ROME ApplicationProfileList (1001)" value selected
        When I enter "1234" string into "Merchant Category Code" textbox
        Then the "Merchant Category Code" textbox should contain "1234" string
        When I click on the "Basic Parameters" tab
        Then I should see the "AEVI Pay Admin | Contract" page
        When I enter "testContract1" string into "Contract Number" textbox
        Then the "Contract Number" textbox should contain "testContract1" string
        When I select "ROME Acquirer 10 with kernel (1010)" string from the "Acquirer/Service Provider" dropdown menu
        Then I should see the "Acquirer/Service Provider" dropdown menu which is enabled and has "ROME Acquirer 10 with kernel (1010)" value selected
        When I click on the "Site" dropdown menu
        Then I should see the "Site" textbox which is enabled and empty
        When I enter "ROME Organization 1002 SITE" string into "Site" textbox
        Then the "Site" dropdown list should contain "ROME Organization 1002 SITE ROSITE02 (1002)" string
        When I click on the "ROME Organization 1002 SITE ROSITE02 (1002)" dropdown menu item
        Then I should see the "Site" dropdown text menu which is enabled and has "ROME Organization 1002 SITE ROSITE02 (1002)" value selected
        When I enter "test#!" string into "Contract Code" textbox
        Then the "Contract Code" textbox should contain "test#!" string
        When I click on the "Save" button
        Then I should see the "Contract Code" textbox "Wrong format for Contract Code (only numbers or letters allowed)." validation error
        When I delete the string from "Contract Code" textbox
        Then the "Contract Code" textbox should be empty
        When I enter "test ing" string into "Contract Code" textbox
        Then the "Contract Code" textbox should contain "test ing" string
        When I click on the "Save" button
        Then I should see the "Contract Code" textbox "Wrong format for Contract Code (only numbers or letters allowed)." validation error
        When I delete the string from "Contract Code" textbox
        Then the "Contract Code" textbox should be empty
        When I enter "testING123" string into "Contract Code" textbox
        Then the "Contract Code" textbox should contain "testING123" string
        When I click on the "OK" button
        Then I should see the "AEVI Pay Admin | Contracts" page
        And I should see the "Success! Contract was created successfully" success message
        And I should see the "Contracts" table
        And I should see "testContract1" string in row "1" of the "Contract Number" column in "Contracts" table
        When I click on the row "1" in "Contracts" table
        Then I should see the "AEVI Pay Admin| Contract" edit page
        And the "Contract Number" textbox should contain "testContract1" string
        And the "Contract Code" textbox should contain "testING123" string
        When I click on the "OK" button
        Then I should see the "AEVI Pay Admin | Contracts" page
        And I should see the "Contracts" table
        And I should see "testContract1" string in row "1" of the "Contract Number" column in "Contracts" table
        When I click on checkbox in row "1" in "Contracts" table
        Then I should see the "Delete" button which is enabled
        When I click on the "Delete" button
        Then I should see the "Delete | Contract" modal
        And I should see the "Delete" modal button which is enabled
        When I click on the "Delete" modal button
        Then I should see the "Success! Contract testContract1 has been successfully removed." success message
        And I should see the "AEVI Pay Admin | Contracts" page
        When I logout from "SMCAdmin" page
        Then I should see the "AEVI Pay Admin | Login" page
