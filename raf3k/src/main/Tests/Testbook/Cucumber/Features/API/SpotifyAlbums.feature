@SpotifyAlbums @API @Spotify @Get_Albums
Feature: Spotify Albums
  As a tester, I want to test the Spotify Albums API calls.

  @TestCaseCode:SFTC001 @SFTC001 @Author:Antonio_Andrasek @API @Get_Albums
  Scenario: Get Albums
    Given I send authorization request to Spotify
    When I send a GET request
      | EndPoint      | Get Nuvole Album |
    Then the response status message should be "Unauthorized"
    Then the response code should be 401
    When I send a GET request
      | EndPoint      | Get Nuvole Album |
      | Authorization | accessToken      |
    Then the response code should be 200
    And the "album id" field should not be empty
    And the "available markets" array is "not empty"
    And the "available markets" array contains "HR" item
    And the "available markets" array does not contain "HR1234" item