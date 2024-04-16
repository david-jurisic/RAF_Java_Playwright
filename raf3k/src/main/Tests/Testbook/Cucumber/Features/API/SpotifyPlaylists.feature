@SpotifyPlaylist @API @Playlists @Spotify
  Feature: Spotify Playlists
  As a tester, I want to test the Spotify Playlist API calls.

  @TestCaseCode:SFTC003 @SFTC003 @Author:Antonio_Andrasek @API @Add_And_Remove_Items_From_Playlist
  Scenario: Add And Remove Items From Playlist
    Given I send authorization request to Spotify
    When I send a GET request
      | EndPoint      | Get Playlist |
      | Authorization | accessToken  |
    Then the response code should be 200
    And the "Description" field should be "FOR ALL"
    When I send a POST request
      | EndPoint      | Add to Playlist |
      | Authorization | accessToken     |
    Then the response code should be 200
    And the "Snapshot Id" field should not be empty
    When I send a DELETE request
      | EndPoint      | Delete from Playlist |
      | Authorization | accessToken          |
    Then the response code should be 400
    And the "Error message" field should be "Missing tracks"
