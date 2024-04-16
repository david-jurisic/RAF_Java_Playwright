@SpotifyAlbums @API @Spotify @Get_Albums
Feature: Spotify Albums
  As a tester, I want to test the Spotify Albums API calls.

  @TestCaseCode:SFTC001 @SFTC001 @Author:Antonio_Andrasek @API @Get_Albums
  Scenario: Get Albums
    Given I send authorization request to Spotify
    When I send a GET request
      | EndPoint      | Get Nuvole Album |
      | Authorization | accessToken      |
    Then the response code should be 200
    And the "album id" field should not be empty