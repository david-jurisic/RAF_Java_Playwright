@API @AEVI @AP3ADMIN @AP3ADMIN-781
Feature: AP3ADMIN-781-Place identification prevents site creation

  Background:
    * url baseUrl
    * configure ssl = true
    * def randomSiteIdEnd = karate.read('../function/random-string.js')(2)
    * def createSiteBody = {"siteId":<siteId>,"name":"Q0000002","defaultLanguage":"en","parentUnitSiteId":"USANY001","siteOperator":{"id":"1","name":"test1"}}
    * def updateSiteBody = {"siteId":<siteId>,"name":"Q0000003","defaultLanguage":"en","parentUnitSiteId":"USANY001","siteOperator":{"id":"2","name":"test2"}}
    * def siteId = 'USANY' + randomSiteIdEnd
    * set createSiteBody.siteId = siteId
    * set updateSiteBody.siteId = siteId

  Scenario: Place identification prevents site creation

    Given path '/onboarding-api/v1/dataGroups/USA/sites'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request createSiteBody
    And method POST
    Then status 201
    And match response.siteId == siteId
    And match response.name == 'Q0000002'
    And match response.siteOperator.id == '1'
    And match response.siteOperator.name == 'test1'

    Given path '/onboarding-api/v1/dataGroups/USA/sites/' + siteId
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request updateSiteBody
    And method PUT
    Then status 200

    Given path '/onboarding-api/v1/dataGroups/USA/sites/' + siteId
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request ''
    And method DELETE
    Then status 200
