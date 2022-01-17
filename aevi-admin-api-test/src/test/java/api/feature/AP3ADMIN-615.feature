@API @AEVI @AP3ADMIN @AP3ADMIN-615
Feature: AP3ADMIN-615-Terminal PUT Untreated error message if terminalProfileCode does not exist in the system

  Background:
    * url baseUrl
    * configure ssl = true
    * def updateTerminalBody = '{"terminalId":"ROSITE02ZC","status":"ACTIVE","name":"TD00000055Name12","siteId":"ROSITE02","terminalProfileCode":"CX01","contracts":[],"serialNumber":null,"acquirerConfigurations":[],"dynamicProperties":{"hw_type":"T","CHECKBOX_FIELD":"1","TEXT_FIELD":"012345678901234567890123456789012345678901"}}'

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/terminals/ROSITE02ZC' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/terminals/ROSITE02ZC'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request updateTerminalBody
    And method PUT
    Then status 400
    And match response.errorCode == 'WRONG_PARAMS'
    And match response.message == 'Terminal with terminalId ROSITE02ZC is not valid'
    And match response.errors[*][0] == ["Specified TerminalProfile with code \"CX01\" does not exist."]


