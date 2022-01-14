@API @AEVI @AP3ADMIN @AP3ADMIN-781
Feature: AP3ADMIN-781-Place identification prevents site creation

  Background:
    * url baseUrl
    * configure ssl = true
    * def createSiteBody = '{"siteId":"ROSITE13","name":"test","parentUnitSiteId":"ROSITE01","placeIdentification":{"street":"test","houseNumber":"test","city":"test","postalCode":"test","region":"test","country":"840","telephone":"test","email":"test@test.com"},"siteOperator":{"id":"test","name":"test","placeIdentification":{"street":"test","houseNumber":"test","city":"test","postalCode":"test","region":"test","country":"840","telephone":"test","email":"test@test.com"}},"defaultLanguage":"en","timezone":null,"promptForDebit":"CREDIT_DEBIT_IN_CANDIDATE_LIST"}'
    * def updateSiteBody = '{"siteId":"ROSITE13","name":"ROSITE13","parentUnitSiteId":"ROOTSITE","placeIdentification":{},"siteOperator":{"id":"ppaas","name":"ppaas","placeIdentification":{}}}'

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/sites' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/sites'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request createSiteBody
    And method POST
    Then status 201
    And match response.siteId == 'ROSITE13'

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/sites/ROSITE13' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/sites/ROSITE13'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And request updateSiteBody
    And method PUT
    Then status 400
    And match response.errorCode == 'WRONG_PARAMS'

  Scenario: Check status code and response body for '/onboarding-api/v1/dataGroups/ITALY/sites/ROSITE13' call
    Given path '/onboarding-api/v1/dataGroups/ITALY/sites/ROSITE13'
    When header Content-Type = 'application/json'
    And header Authorization = 'Bearer ' + token
    And method DELETE
    Then status 200