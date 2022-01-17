@API @AEVI @AP3ADMIN @AP3ADMIN-615
Feature: Update database with records for a tests

  Background:
    * url baseUrl
    * configure ssl = true
    * def createTerminalBody = '{"terminalId":"ROSITE02ZC","status":"ACTIVE","name":"TD00000055Name12","siteId":"ROSITE13","terminalProfileCode":"NYTPOPT103","contracts":[],"serialNumber":null,"acquirerConfigurations":[],"dynamicProperties":{"hw_type":"T","CHECKBOX_FIELD":"1","TEXT_FIELD":"012345678901234567890123456789012345678901"}}'

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/terminals' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/terminals'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request createTerminalBody
    And method POST
    Then status 201
    And match response.siteId == 'ROSITE02ZC'