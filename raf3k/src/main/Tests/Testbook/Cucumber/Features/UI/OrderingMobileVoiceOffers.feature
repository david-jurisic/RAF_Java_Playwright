@HTInfonova @UI @DigitalMobile @OrderingMobileVoiceOffers @Offers
Feature: OrderingMobileVoiceOffers
  As a tester, I want to test the functionality of the ordering Mobile Voice (Digital Mobile) Offers on the HT Infonova.

  @TestCaseCode:TC0003 @Author:Bernardo_Kopjar @UI @Xray:TES-8
  Scenario Outline: Ordering Mobile Voice Offers
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
    When I create subaccount with "<Name>", "<Email>" and "Digital XS" offer
    And I select corresponding "subaccount" in "Subaccount" table
    Then I should see "Overview" page
    When I select "Offers" option in "Customer Menu" list on left part of the page
    Then I should see "Offers" page
    When I add offer on same account with "Digital S" offer
    And I add offer on same account with "Digital M" offer
    And I add offer on same account with "Digital L" offer

    Examples:
      | Name        | Email           |
      | HT Infonova | roxoft@mail.com |