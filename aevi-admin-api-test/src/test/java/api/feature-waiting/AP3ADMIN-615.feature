@API @AEVI @AP3ADMIN @AP3ADMIN-615
Feature: AP3ADMIN-615-Terminal PUT Untreated error message if terminalProfileCode does not exist in the system

  Background:
    * url baseUrl
    * configure ssl = true
    * def createTerminalBody = '{"terminalId":"ROSITE02ZZ","status":"ACTIVE","name":"TD00000055Name12","siteId":"ROSITE02","terminalProfileCode":"NYTPOPT103","contracts":[],"serialNumber":null,"acquirerConfigurations":[],"dynamicProperties":{"hw_type":"T","CHECKBOX_FIELD":"1","TEXT_FIELD":"012345678901234567890123456789012345678901"}}'
    * def updateTerminalBody = '{"terminalId":"ROSITE02ZZ","status":"ACTIVE","name":"TD00000055Name12","siteId":"ROSITE02","terminalProfileCode":"CX01","contracts":[],"serialNumber":null,"acquirerConfigurations":[],"dynamicProperties":{"hw_type":"T","CHECKBOX_FIELD":"1","TEXT_FIELD":"012345678901234567890123456789012345678901"}}'

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/terminals' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/terminals'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request createTerminalBody
    And method POST
    Then status 201
    And match response.siteId == 'ROSITE02ZZ'

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/terminals/ROMEIPT1' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/terminals/ROMEIPT1'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request updateTerminalBody
    And method PUT
    Then status 400
    And match response.errors.cause == 'TerminalProfile with code 'CX01' belonging to DataGroup 'ITALY' not found.'

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/terminals/ROSITE02Z' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/terminals/ROSITE02Z'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And method DELETE
    Then status 200
    And match response == '1'


