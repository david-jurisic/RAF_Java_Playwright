@HTInfonova @UI @Offer @Ordering
Feature: MobileOffer2OptionsWithRemove
  As a tester, I want to test the functionality of the Mobile offer with 2 options on the HT Infonova.

  @Author:Bernardo_Kopjar @UI
  Scenario Outline: Mobile <Offer> Offer With 2 Options
    Given I navigate to "HT Infonova" page
    Then I should see "Login" page
    When I enter corresponding string into "Username" textbox
    And I enter corresponding string into "Password" textbox
    And I click on "Log In" button
    Then I should see "Main" page
    When I click on "Menu" button
    And I select "Customers" item in "Menu" list
    Then I should see "Customers" page
    When I enter corresponding string into "Search Customers" textbox
    And I click on "Search" button
    And I select corresponding "customer" in "Search for Customers" table
    Then I should see "Overview" page
    When I select "Account" option in "Customer Menu" list on left part of the page
    Then I should see "Profile" page
    And I should see "Account" list on left part of the page that is "enabled" and "not empty"
    When I select 'Subaccounts' option in "Account" list
    Then I should see "Subaccounts" page
    When I click on "Add ..." button
    And I select "New Account" item in "Add ..." list
    Then I should see "Address Check" page
    When I click on "Next" button
    Then I should see "Ok" button that is "enabled"
    When I click on "Ok" button
    Then I should see "Offer Selection" page
    When I click on Add To Cart button in Offers table for <Offer> offer
    Then I should see "Shopping Cart" button that is "enabled"
    And I should see "Recurring Gross Charge" label for "<Offer>" offer that contains corresponding string
    And I should see "Recurring Net Charge" label for "<Offer>" offer that contains corresponding string
    When I click on "Shopping Cart" button
    Then I should see "Shopping Cart Content" list that contains "<Offer>" string and is "enabled"
    And I should see "Offers Counter" label in "Shopping Cart" section that contains "1" string
    When I click on "Next" button
    Then I should see "Offer Configuration" page for <Offer> offer
    And I should see "Digital Minimal Contract Duration 24 Months" option in "Selected Features" table
    When I remove "Digital Minimal Contract Duration 24 Months" option in "Selected Features" table
    Then I should see "Feature Filter" list on left part of the page that is "enabled"
    When I select "<FeatureCategory1>" option in "Feature Filter" list on left part of the page
    And I select "<Option1>" option in "Feature Group" list on left part of the page
    Then I should see "<Option1>" option in "Selected Features" table
    When I select "<FeatureCategory2>" option in "Feature Filter" list on left part of the page
    And I select "<Option2>" option in "Feature Group" list on left part of the page
    Then I should see "<Option2>" option in "Selected Features" table
    And I should see "Recurring Gross Charge" label for "<Option2>" option that contains corresponding string
    And I should see "Recurring Net Charge" label for "<Option2>" option that contains corresponding string
    When I open up "Mobile Service [MOBILE_SERVICE]" section
    Then I should see "Get Number" button that is "enabled"
    When I click on "Get Number" button
    Then I should see "Numbers" table in "Select Number" popup that is "enabled" and "not empty"
    And I should see "Cancel" link in "Select Number" popup that is "enabled"
    When I select first option in Phone Number table in Select Number popup
    Then I should see "Phone Number" label that contains corresponding string
    And I should see "Next" button that is "enabled"
    When I click on "Next" button
    Then I should see "Customer Details" page
    When I enter "<Name>" string into "Displayed Name" textbox
    Then I should see "Customer Header" label that contains corresponding string
    And I should see "Next" button that is "enabled"
    When I click on "Next" button
    Then I should see "Payment Details" page
    When I click on "Validate Address" button
    And I select "Contact" item in "Validate Address" list
    Then I should see "Billing Address" label that contains corresponding string
    When I select "No" in "EDI bill media eligible" dropdown menu
    Then I should see "Region" textbox that is "enabled" and "not empty"
    When I enter "<Email>" string into "E-Mail" textbox
    And I enter "222" string into "Frame Contract ID" textbox
    Then I should see "Next" button that is "enabled"
    When I click on "Next" button
    Then I should see "Summary" page for <Offer> offer
    When I select "Terms and Conditions" checkbox
    Then I should see "Create Customer" button that is "enabled"
    When I click on "Create Customer" button
    Then I should see "Subaccounts" page

    @TestCaseCode:TC0023 @Xray:TES-99
    Examples: DL UO12 DL PP1 Sales Discount 12 Months
      | Name        | Email           | Offer     | FeatureCategory1 | FeatureCategory2            | Option1                                     | Option2                         |
      | HT Infonova | roxoft@mail.com | Digital L | Contract         | Contract Sales Discounts 12 | Digital Minimal Contract Duration 12 Months | DL PP1 Sales Discount 12 Months |

    @TestCaseCode:TC0021 @Xray:TES-99
    Examples: DS UO12 Simo Discount 6 Months
      | Name        | Email           | Offer     | FeatureCategory1 | FeatureCategory2   | Option1                                     | Option2                            |
      | HT Infonova | roxoft@mail.com | Digital S | Contract         | Contract Discounts | Digital Minimal Contract Duration 12 Months | DS SIMO Discount Duration 6 Months |