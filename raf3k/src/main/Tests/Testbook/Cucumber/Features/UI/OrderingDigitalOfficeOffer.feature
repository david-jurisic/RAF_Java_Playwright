@HTInfonova @UI @OrderingDigitalOfficeOffer @Offer
Feature: OrderingDigitalOfficeOffer
  As a tester, I want to test the functionality of the Ordering Digital Office Offer on the HT Infonova.

  @Author:Bernardo_Kopjar @UI
  Scenario Outline: Ordering Fixed <Offer> Offer
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
    When I enter "<StreetName>" string into "Street name" textbox
    And I enter "<Number>" string into "Number" textbox
    And I enter "<Suffix>" string into "Suffix" textbox
    And I enter "<Zip>" string into "Zip" textbox
    And I enter "<City>" string into "City" textbox
    And I enter "<Place>" string into "Place" textbox
    Then I should see "Validate Address" button that is "enabled"
    When I click on "Validate Address" button
    Then I should see "Addresses" list in "Matching Addresses" popup that is "enabled"
    And I should see "Cancel" link in "Matching Addresses" popup that is "enabled"
    When I select corresponding address option in Addresses list in Matching Addresses popup
    Then I should see "Service Address" label that contains corresponding string
    When I click on "Next" button
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
    When I open up "Fixed Voice Service [FIXED_VOICE_SERVICE]" section
    Then I should see "Short Number" textbox that is "enabled" and "empty"
    And I should see "Get Number" button that is "enabled"
    When I click on "Get Number" button
    Then I should see "Numbers" table in "Select Number" popup that is "enabled" and "not empty"
    And I should see "Cancel" link in "Select Number" popup that is "enabled"
    When I select first option in Phone Number table in Select Number popup
    Then I should see "Phone Number" label that contains corresponding string
    When I enter "200" string into "Short Number" textbox
    Then I should see "Next" button that is "enabled"
    When I click on "Next" button
    Then I should see "Customer Details" page
    When I enter "<Name>" string into "Displayed Name" textbox
    Then I should see "Customer Header" label that contains corresponding string
    And I should see "Next" button that is "enabled"
    When I click on "Next" button
    Then I should see "Payment Details" page
    When I click on "Validate Address" button
    And I select "Service Address" item in "Validate Address" list
    Then I should see "Billing Address" label that contains corresponding string
    When I select "No" in "EDI bill media eligible" dropdown menu
    Then I should see "Region" textbox that is "enabled" and "not empty"
    When I enter "<Email>" string into "E-Mail" textbox
    And I enter "222" string into "Frame Contract ID" textbox
    Then I should see "Next" button that is "enabled"
    When I click on "Next" button
    Then I should see "Shipping Details" page
    When I click on "Validate Address" button
    And I select "Service Address" item in "Validate Address" list
    Then I should see "Shipping Address" label that contains corresponding string
    And I should see "Next" button that is "enabled"
    When I click on "Next" button
    Then I should see "Summary" page for <Offer> offer
    When I select "Terms and Conditions" checkbox
    Then I should see "Create Customer" button that is "enabled"
    When I click on "Create Customer" button
    Then I should see "Subaccounts" page
    When I select corresponding "subaccount" in "Subaccount" table
    Then I should see "Overview" page
    When I select "Offers" option in "Customer Menu" list on left part of the page
    Then I should see "Offers" page
    And I should see "Offers" list on left part of the page that is "enabled" and "not empty"
    When I select 'Orders' option in "Offers" list
    Then I should see "Orders and Service Requests" page
    When I select "<Offer>" type in "Orders" table
    Then I should see "Process Details" table that is "enabled" and "not empty"
    When I select corresponding "Provisioning Order ID" in "Process Details" table
    Then I should see "Provisioning Order" label that contains corresponding string
    And I should see "Provisioning" list that is "enabled" and "not empty"
    When I select "History" option in "Provisioning" list
    Then I should see "Order History" table that is "enabled" and "not empty"
    And I should see "Show Request Details" button for "Create" action in "Order History" table
    When I click on "Show Request Details" button for "Create" action in "Order History" table
    Then I should see "Provisioning Request Details" label that contains "httpStatusCode: 200" string
    And I should see "Provisioning Request Details" label that contains "responseState: SUCCESS" string
    And I should see "Close" button that is "enabled"
    Then I click on "Close" button

    @TestCaseCode:TC0014 @DigitalOfficeXS @Xray:TES-4
    Examples: Digital Office XS
      | StreetName       | Number | Suffix | Zip   | City        | Place       | Name        | Email           | Offer             |
      | Južna Obala VII. | 4      | N/A    | 10000 | Grad Zagreb | Grad Zagreb | HT Infonova | roxoft@mail.com | Digital Office XS |

    @TestCaseCode:TC0015 @DigitalOfficeS @Xray:TES-17
    Examples: Digital Office S
      | StreetName       | Number | Suffix | Zip   | City        | Place       | Name        | Email           | Offer            |
      | Južna Obala VII. | 4      | N/A    | 10000 | Grad Zagreb | Grad Zagreb | HT Infonova | roxoft@mail.com | Digital Office S |

    @TestCaseCode:TC0016 @DigitalOfficeM @Xray:TES-19
    Examples: Digital Office M
      | StreetName       | Number | Suffix | Zip   | City        | Place       | Name        | Email           | Offer            |
      | Južna Obala VII. | 4      | N/A    | 10000 | Grad Zagreb | Grad Zagreb | HT Infonova | roxoft@mail.com | Digital Office M |

    @TestCaseCode:TC0017 @DigitalOfficeL @Xray:TES-37
    Examples: Digital Office L
      | StreetName       | Number | Suffix | Zip   | City        | Place       | Name        | Email           | Offer            |
      | Južna Obala VII. | 4      | N/A    | 10000 | Grad Zagreb | Grad Zagreb | HT Infonova | roxoft@mail.com | Digital Office L |