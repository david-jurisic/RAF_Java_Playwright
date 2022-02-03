@AeviAdmin @UI @AP3ADMIN-779 @AP3ADMIN
Feature: AP3ADMIN-779-Unable to create/edit Acquirer when 'apac' package is enabled

    @TestCaseCode:TC006 @TC006 @Author:Drazen-Kozic @UI @AP3ADMIN-779
    Scenario: Unable to create/edit Acquirer when 'apac' package is enabled
        Given I have opened the "SMCAdmin" page
        Then I should see the "AEVI Pay Admin | Login" page
        And I should see the "Save Changes" button which is enabled
        When I click on the "Save Changes" button
        Then I should see the "AEVI Pay Admin | Organization Units" page
        And I should see the "Advanced Configs" button in the sidebar menu
        When I click on the "Advanced Configs" button in the sidebar menu
        Then I should see the "Acquirers/Service Providers" button in the sidebar menu
        When I click on the "Acquirers/Service Providers" button in the sidebar menu
        Then I should see the "AEVI Pay Admin | Acquirers/Service Providers" page
        And I should see the "New Record" button which is enabled
        When I click on the "New Record" button in the button dropdown menu
        Then I should see the "Choose an option" button dropdown menu
        When I click on the "Add Physical Acquirer" button in the button dropdown menu
        Then I should see the "AEVI Pay Admin | Acquirers/Service Provider" page
        And I should see the "Host" dropdown menu which is enabled and has "" value selected
        And I should see the "Non-financial Message Timeout" textbox which is enabled and empty
        And I should see the "Authorization Timeout" textbox which is enabled and empty
        And I should see the "Batch Close Type" dropdown menu which is enabled and has "Select" value selected
        When I logout from "SMCAdmin" page
        Then I should see the "AEVI Pay Admin | Login" page
