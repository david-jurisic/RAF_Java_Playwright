@API @AEVI @AP3ADMIN @AP3ADMIN-706
Feature: AP3ADMIN-706-500 error message for SITE - POST when using in the request body a promptForDebit value, which is not in the enum list

  Background:
    * url baseUrl
    * configure ssl = true
    * def requestBody = '{"siteId":"Q0000706","name":"Q0000706Name","parentUnitSiteId":"USANY001","placeIdentification":{"street":"Laurinova","houseNumber":"1","city":"Prague","postalCode":"12800","region":"Prague","country":203,"telephone":"88888888","email":"888@888.com"},"siteOperator":{"id":"SO0123456789","name":"TestSiteOperator1","placeIdentification":{"street":"Laurinova","houseNumber":"4","city":"Prague","postalCode":"12800","region":"siteOperatorPrague","country":203,"telephone":"9876541235","email":"999@999.com"}},"defaultLanguage":"en","timezone":"Europe/Prague","promptForDebit":"PROMPT"}'

  Scenario: 500 error message for SITE - POST when using in the request body a promptForDebit value, which is not in the enum list

    Given path 'onboarding-api/v1/dataGroups/ITALY/sites'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request requestBody
    And method POST
    Then status 400
    And match response.errorCode == 'WRONG_PARAMS'