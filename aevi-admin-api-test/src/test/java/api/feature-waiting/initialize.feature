@API @AEVI @AP3ADMIN @AP3ADMIN-615
Feature: Update database with records for a tests

  Background:
    * url baseUrl
    * configure ssl = true
    * def createSiteBody = {"siteId":<siteId">,"name":"test","parentUnitSiteId":"ROSITE01","placeIdentification":{"street":"test","houseNumber":"test","city":"test","postalCode":"test","region":"test","country":"840","telephone":"test","email":"test@test.com"},"siteOperator":{"id":"test","name":"test","placeIdentification":{"street":"test","houseNumber":"test","city":"test","postalCode":"test","region":"test","country":"840","telephone":"test","email":"test@test.com"}},"defaultLanguage":"en","timezone":null,"promptForDebit":"CREDIT_DEBIT_IN_CANDIDATE_LIST"}
    * def createTerminalBody = {"terminalId":<"terminalId>","status":"ACTIVE","name":"TD00000055Name12","siteId":"ROSITE13","terminalProfileCode":"NYTPOPT103","contracts":[],"serialNumber":null,"acquirerConfigurations":[],"dynamicProperties":{"hw_type":"T","CHECKBOX_FIELD":"1","TEXT_FIELD":"012345678901234567890123456789012345678901"}}
    * def random_string =
    """
    function(s) {
        var text = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        for (var i = 0; i < s; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));
        return text;
    }
    """
    * def randomIdEnd = random_string(2)
    * def siteId = 'ROSITE' + randomIdEnd
    * def terminalId = 'ROSITE02' + randomIdEnd
    * set createSiteBody.siteId = siteId
    * set createTerminalBody.terminalId = terminalId
    * set createTerminalBody.siteId = siteId

  Scenario: Create and activate site
    #Create site
    Given  path '/onboarding-api/v1/dataGroups/ITALY/sites'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request createSiteBody
    And method POST
    Then status 201
    And match response.siteId == siteId

    #Activate site
    Given  path '/onboarding-api/v1/dataGroups/ITALY/sites/' + siteId + '/activate'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request ''
    And method PATCH
    Then status 200
    And match response.siteId == siteId

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/terminals' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/terminals'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request createTerminalBody
    And method POST
    Then status 201
    And match response.siteId == 'ROSITE02ZC'