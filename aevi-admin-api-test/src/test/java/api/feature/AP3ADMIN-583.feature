@API @AEVI @AP3ADMIN @AP3ADMIN-583
Feature: AP3ADMIN-583-500 internal error when running Site via POST using just the required fields in the request body

  Background:
    * url baseUrl
    * configure ssl = true
    * def randomSiteIdEnd = karate.read('../function/random-string.js')(2)
    * def requestBody = {"siteId":<siteId>,"name":"Q0000001","defaultLanguage":"en","siteOperator":{"id":"1","name":"test1"}}
    * def siteId = 'USANY' + randomSiteIdEnd
    * set requestBody.siteId = siteId

  Scenario: Internal error when running Site via POST using just the required fields in the request body

    Given path '/onboarding-api/v1/dataGroups/USA/sites'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request requestBody
    And method POST
    Then status 201
    And match response.siteId == siteId
    And match response.name == 'Q0000001'
    And match response.siteOperator.id == '1'
    And match response.siteOperator.name == 'test1'

    Given path '/onboarding-api/v1/dataGroups/USA/sites/' + siteId
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request ''
    And method DELETE
    Then status 200
