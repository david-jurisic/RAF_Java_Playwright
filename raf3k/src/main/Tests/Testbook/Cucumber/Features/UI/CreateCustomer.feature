@HTInfonova @UI @CreateCustomer
Feature: CreateCustomer
  As a tester, I want to test the functionality of the Creating Customer on the HT Infonova.

  @TestCaseCode:TC0001 @Author:Bruno_Marincel @UI @Xray:TES-3
  Scenario Outline: Create Customer
    Given I navigate to "HT Infonova" page
    Then I should see "Login" page
    When I enter corresponding string into "Username" textbox
    And I enter corresponding string into "Password" textbox
    And I click on "Log In" button
    Then I should see "Main" page
    When I click on "Menu" button
    Then I select "Customers" item in "Menu" list
    When I click on "Create" button
    And I select "Customer" item in "Create Customer Menu" list
    Then I should see "New Customer" page
    When I enter "valid oib" string into "Business Number (OIB)" textbox in "Business Details" section
    And I select "Company (d.o.o., d.d., j.t.d., k.d.)" in "Business type" dropdown menu in "Business Details" section
    And I enter "Infonova" string into "Legal name (short)" textbox in "Business Details" section
    And I select "J Information and communications" in "Industry type" dropdown menu in "Business Details" section
    And I select "Active" in "Company Status" dropdown menu in "Business Details" section
    And I select "Public Tender" checkbox in "Business Details" section
    And I select "National" in "Residency" dropdown menu in "Business Details" section
    And I enter "Snake" string into "Last name" textbox in "Primary Responsible Person" section
    And I enter "Solid" string into "First name" textbox in "Primary Responsible Person" section
    And I select "Full authority" in "Authorisation of the responsible person" dropdown menu in "Primary Responsible Person" section
    And I enter "current date" string into "Responsible person's period of authority beginning date" textbox in "Primary Responsible Person" section
    And I enter "next year" string into "Responsible person's period of authority end date" textbox in "Primary Responsible Person" section
    And I enter "valid oib" string into "Personal OIB" textbox in "Personal identification document" section
    And I enter "2222" string into "Personal document ID" textbox in "Primary Responsible Person" section
    And I enter "current date" string into "Document issue date" textbox in "Personal identification document" section
    And I enter "next year" string into "Document expiration date" textbox in "Personal identification document" section
    And I enter "PU <City>" string into "Document issue institution" textbox in "Primary Responsible Person" section
    And I enter "<StreetName>" string into "Street name" textbox in "Legal Address" section
    And I enter "<Number>" string into "Number" textbox in "Legal Address" section
    And I enter "<Suffix>" string into "Suffix" textbox in "Legal Address" section
    And I enter "<Zip>" string into "Zip" textbox in "Legal Address" section
    And I enter "<City>" string into "City" textbox in "Legal Address" section
    And I enter "<Place>" string into "Place" textbox in "Legal Address" section
    Then I should see "Validate Address" button in "Legal Address" section that is "enabled"
    When I click on "Validate Address" button in "Legal Address" section
    Then I should see "Addresses" list in "Matching Addresses" popup that is "enabled"
    And I should see "Cancel" link in "Matching Addresses" popup that is "enabled"
    When I select corresponding address option in Addresses list in Matching Addresses popup
    Then I should see "Legal Address" label in "Legal Address" section that contains corresponding string
    And I should see "Edit Address" button in "Legal Address" section that is "enabled"
    And I should see "Existing Addresses" button in "Contact Address" section that is "enabled"
    When I click on "Existing Addresses" button in "Contact Address" section
    Then I should see "Existing Addresses" list in "Contact Address" section that is "not empty"
    When I select corresponding address option in Existing Addresses list in Contact Addresses section
    Then I should see "Contact Address" label in "Contact Address" section that contains corresponding string
    When I enter "<Mobile>" string into "Mobile" textbox in "Contact Details" section
    And I enter "<Email>" string into "Email" textbox in "Contact Details" section
    Then I should see "Check Credit" button in "Credit Scoring" section that is "enabled"
    When I click on "Check Credit" button in "Credit Scoring" section
    Then I should see "Decision" label that contains "Da" string in "Credit Check" popup
    And I should see "Reason" label that contains "Odobreno" string in "Credit Check" popup
    And I should see "Credit Score" label that contains "530" string in "Credit Check" popup
    And I should see "Cancel" link in "Credit Check" popup that is "enabled"
    And I should see "Ok" button in "Credit Check" popup that is "enabled"
    When I click on "Ok" button in "Credit Check" popup
    Then I should see "Create Customer" button that is "enabled"
    When I click on "Create Customer" button on "New Customer" page
    Then I should see "Overview" page

    Examples:
      | StreetName    | Number | Suffix | Zip   | City        | Place       | Mobile       | Email           |
      | I. Retkovec   | 20     | N/A    | 10000 | Grad Zagreb | Grad Zagreb | 385956874324 | roxoft@mail.com |