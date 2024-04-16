@HTInfonova @UI @GoDigital @OrderingMobileDataOffers @Offers
Feature: OrderingMobileVoiceOffers
  As a tester, I want to test the functionality of the ordering Mobile Data (Go Digital) Offers on the HT Infonova.

  @TestCaseCode:TC0004 @Author:Bruno_Marincel @UI @Xray:TES-5
  Scenario Outline: Ordering Mobile Data Offers
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
    When I create subaccount with "<Name>", "<Email>" and "Go Digital S" offer
    And I select corresponding "subaccount" in "Subaccount" table
    Then I should see "Overview" page
    When I select "Offers" option in "Customer Menu" list on left part of the page
    Then I should see "Offers" page
    When I add offer on same account with "Go Digital M" offer
    And I add offer on same account with "Go Digital L" offer
    And I add offer on same account with "Go Digital 1 TB" offer

    Examples:
      | Name        | Email           |
      | HT Infonova | roxoft@mail.com |