@SpotifyArtist @API @Artist @Spotify
Feature: Spotify Artist
  As a tester, I want to test functionality of the Artist endpoint on Spotify API.

  @TestCaseCode:SFTC002 @SFTC002 @Author:Antonio_Andrasek @API @Artist
  Scenario: Follow Artist PUT
    Given I send authorization request to Spotify
    When I send a GET request
      | EndPoint      | search        |
      | Authorization | <accessToken> |
      | q             | rammstein     |
      | type          | artist        |
      | market        | HR            |
      | limit         | 10            |
      | offset        | 0             |
    Then the response code should be 200
    And the "search artists id" field should not be empty
    And save field "search artists id" value as "ids"
    When I send a PUT request
      | EndPoint | artist follow |
      | type     | artist        |
      | ids      | <ids>         |
    Then the response code should be 401
    And the "error message" field should not be empty
    When I send a PUT request
      | EndPoint      | artist follow |
      | Authorization | <accessToken> |
      | type          | artist        |
      | ids           | <ids>         |
    Then the response code should be 204
    When I send a GET request
      | Key           | Value         |
      | EndPoint      | artist follow |
      | Authorization | <accessToken> |
      | type          | artist        |
    Then the response code should be 200
    And the "search artists id" field should not be empty
    And the "search artists name" field should not be empty
    When I send a DELETE request
      | EndPoint      | artist follow |
      | Authorization | accessToken   |
      | type          | artist        |
      | ids           | <ids>         |
    Then the response code should be 204
