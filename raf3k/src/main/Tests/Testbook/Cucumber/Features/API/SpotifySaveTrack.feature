@SpotifySaveTrack @API @Spotify @Spotify
Feature: Spotify Save Track
  As a tester, I want to test functionality of the Save Track endpoint on Spotify API.

  @TestCaseCode:SFTC004 @SFTC004 @Author:Antonio_Andrasek @API @SaveTrack
  Scenario: Save Track
    Given I send authorization request to Spotify
    When I send a GET request
      | EndPoint      | search        |
      | Authorization | <accessToken> |
      | q             | Orion         |
      | type          | track         |
      | market        | HR            |
      | limit         | 10            |
      | offset        | 0             |
    Then the response code should be 200
    And the "track artist name" field should not be empty
    And the "track name" field should not be empty
    And the "track id" field should not be empty
    And save field "track id" value as "ids"
    When I send a PUT request
      | EndPoint      | save tracks   |
      | Authorization | <accessToken> |
      | ids           | <ids>         |
    Then the response code should be 200
    When I send a GET request
      | EndPoint      | get saved tracks |
      | Authorization | <accessToken>    |
      | market        | HR               |
      | limit         | 5                |
      | offset        | 0                |
    Then the response code should be 200
    And the "get artist name" field should be "Metallica"
    And the "get track name" field should be "Orion"
    When I send a DELETE request
      | EndPoint      | remove saved tracks |
      | Authorization | <accessToken>       |
      | ids           | <ids>               |
    Then the response code should be 200
    When I send a GET request
      | EndPoint      | get saved tracks |
      | Authorization | <accessToken>    |
      | market        | HR               |
      | limit         | 5                |
      | offset        | 0                |
    Then the response code should be 200
    And the "get artist name" field should not be "Metallica"
    And the "get track name" field should not be "Orion"