@API @AEVI @AP3ADMIN @AP3ADMIN-597
Feature: AP3ADMIN-597-Field "region" from "siteOperator.placeIdentification" from SIte POST can not be verify in APADMIN

  Background:
    * url baseUrl
    * configure ssl = true
    * def randomSiteIdEnd = karate.read('../function/random-string.js')(2)
    * def requestBody = {"siteId":<siteId>,"name":"Q0000003","parentUnitSiteId":"USANY001","siteOperator":{"id":"SO0123456789","name":"TestSiteOperator1","placeIdentification":{"street":"Laurinova","houseNumber":"4","city":"Prague","postalCode":"12800","region":"0123456789012345678901234567890123456789","country":203,"telephone":"999999999","email":"999@999.com"}},"defaultLanguage":"en","timezone":"Europe\/Prague","promptForDebit":"CREDIT_DEBIT_IN_CANDIDATE_LIST"}
    * def siteId = 'USANY' + randomSiteIdEnd
    * set requestBody.siteId = siteId

  Scenario: Field "region" from "siteOperator.placeIdentification" from SIte POST can not be verify in APADMIN

    Given path '/onboarding-api/v1/dataGroups/USA/sites'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request requestBody
    And method POST
    Then status 201
    And match response.siteId == siteId
    And match response.name == 'Q0000003'
    And match response.siteOperator.placeIdentification.region == null
    And match response.siteOperator.placeIdentification.telephone == null
    And match response.siteOperator.placeIdentification.email == null

    Given path '/onboarding-api/v1/dataGroups/USA/sites/' + siteId
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request ''
    And method DELETE
    Then status 200
