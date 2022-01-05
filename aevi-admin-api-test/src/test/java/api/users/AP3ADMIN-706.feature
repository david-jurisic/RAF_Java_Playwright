@API @AEVI @AP3ADMIN
Feature: ROMEIPT103 Test

  Background:
    * url baseUrl
    * configure ssl = true
    * def requestBody = '{"siteId":"Q0075579","name":"Q0075579Name","parentUnitSiteId":"0003","placeIdentification":{"street":"Laurinova","houseNumber":"1","city":"Prague","postalCode":"12800","region":"Prague","country":203,"telephone":"88888888","email":"888@888.com"},"siteOperator":{"id":"SO0123456789","name":"TestSiteOperator1","placeIdentification":{"street":"Laurinova","houseNumber":"4","city":"Prague","postalCode":"12800","region":"siteOperatorPrague","country":203,"telephone":"9876541235","email":"999@999.com"}},"defaultLanguage":"en","timezone":"Europe/Prague","promptForDebit":"PROMPT"}'

  Scenario: Check status code and response body for 'onboarding-api/v1/dataGroups/ITALY/sites' path
    Given path 'onboarding-api/v1/dataGroups/ITALY/sites'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request requestBody
    And method POST
    Then status 400
    And match response.errorCode == 'WRONG_PARAMS'