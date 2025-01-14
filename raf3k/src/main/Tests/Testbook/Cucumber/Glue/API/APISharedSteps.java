package Testbook.Cucumber.Glue.API;

import ExtendedTypes.API.QueryStringEx;
import ExtendedTypes.API.RAFRestResponseEx;
import Maps.API.SpotifyAPIMap;
import Maps.API.SpotifyJsonMap;
import Settings.GlobalParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.raf3k.apitesting.basetypes.QueryString;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.SharedVariables;

import java.io.IOException;
import java.util.*;

import static Hooks.Hooks.savedValues;
import static java.util.Map.entry;

public class APISharedSteps {
    static RAFRestResponseEx rafRestResponseEx;
    String sValueToEncode = SharedVariables.configuration.getProperty("spotifyclientId") + ":" + SharedVariables.configuration.getProperty("spotifyclientSecret");
    String basicAuth = "Basic " + Base64.getEncoder().encodeToString(sValueToEncode.getBytes());
    String refreshToken = SharedVariables.configuration.getProperty("refreshToken");
    String accessToken;

    private Map<String, String> addSavedValues(Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String value;
            String key = entry.getKey();
            if (entry.getValue().matches("^<.*>$")) {
                String entryValue = entry.getValue().substring(1, entry.getValue().length() - 1);
                if (savedValues.containsKey(entryValue)) {
                    value = savedValues.get(entryValue).toString();
                } else {
                    value = entry.getValue();
                }
            } else {
                value = entry.getValue();
            }
            map.put(key, value);
        }

        return map;
    }

    private QueryStringEx findQueryStringByName(Map<String, String> data) {
        QueryStringEx queryString = new QueryStringEx();

        switch (data.get("EndPoint").toLowerCase()) {
            case "get nuvole album" -> queryString = SpotifyAPIMap.AlbumsAndSongs.albums("0cNyy0dzDCNM24MdXBw1E4");
            case "search" -> queryString = SpotifyAPIMap.API.search();
            case "artist follow" -> queryString = SpotifyAPIMap.API.artistFollow();
            case "get playlist" -> queryString = SpotifyAPIMap.Playlists.Playlist("1giHCPm2i1cgHnRVcgd6tX");
            case "add to playlist" -> queryString = SpotifyAPIMap.Playlists.AddPlaylistTracks("1giHCPm2i1cgHnRVcgd6tX");
            case "delete from playlist" ->
                    queryString = SpotifyAPIMap.Playlists.RemovePlaylistTracks("1giHCPm2i1cgHnRVcgd6tX");
            case "playlist_id" -> queryString = SpotifyAPIMap.Playlists.Playlist("37i9dQZEVXbMDoHDwVN2tF");
            case "save tracks" -> queryString = SpotifyAPIMap.Tracks.Tracks();
            case "get saved tracks" -> queryString = SpotifyAPIMap.Tracks.Tracks();
            case "remove saved tracks" -> queryString = SpotifyAPIMap.Tracks.Tracks();
            default -> queryString = null;
        }
        return queryString;
    }

    private String findJsonPath(String sPathName) {

        String sPath;
        switch (sPathName.toLowerCase()) {
            case "album id" -> sPath = SpotifyJsonMap.Album.id;
            case "available markets" -> sPath = SpotifyJsonMap.Album.availableMarkets;
            case "search artists id" -> sPath = SpotifyJsonMap.Artist.searchArtistId;
            case "error message" -> sPath = SpotifyJsonMap.Error.errorMessage;
            case "search artists name" -> sPath = SpotifyJsonMap.Artist.searchArtistName;
            case "description" -> sPath = SpotifyJsonMap.Playlist.description;
            case "snapshot id" -> sPath = SpotifyJsonMap.Playlist.snapshotId;
            case "name" -> sPath = SpotifyJsonMap.Playlist.mostPopularSong;
            case "track artist name" -> sPath = SpotifyJsonMap.Track.trackArtistName;
            case "get artist name" -> sPath = SpotifyJsonMap.Track.itemsTrackArtistName;
            case "track name" -> sPath = SpotifyJsonMap.Track.trackName;
            case "get track name" -> sPath = SpotifyJsonMap.Track.itemsTrackName;
            case "track id" -> sPath = SpotifyJsonMap.Track.trackId;
            default -> sPath = null;
        }
        ;
        sPath = replacePathChar(sPath);
        return sPath;
    }

    private String replacePathChar(String sPath) {
        sPath = "/" + sPath.replace('.', '/');
        sPath = sPath.replace('[', '/');
        sPath = sPath.replace("]", "");
        return sPath;
    }

    private Map<String, Object> findBodyByEndPoint(Map<String, String> data) {
        Map<String, Object> body = null;
        String jsonString = "";

        switch (data.get("EndPoint").toLowerCase()) {
            case "add to playlist":
                jsonString = "{\"uris\":[\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\"spotify:track:1301WleyT98MSxVHPZCA6M\",\"spotify:episode:512ojhOuo1ktJprKbVcKyQ\"]}";
                break;
            default:
                body = null;
                break;
        }
        try {
            // convert JSON string to Map
            if (!jsonString.isEmpty()) {
                body = new ObjectMapper().readValue(jsonString, Map.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    @Given("I send authorization request to Spotify")
    public void iSendAuthorizationRequestToSpotify() {

        SpotifyAPIMap.initialize();

        Map<String, Object> body = Map.ofEntries(entry("grant_type", "refresh_token"), entry("refresh_token", refreshToken));
        Map<String, String> headers = Map.ofEntries(entry("Authorization", basicAuth));

        QueryStringEx queryStringEx = SpotifyAPIMap.Token.request();
        GlobalParameters.testCaseBase.success(queryStringEx.POST("", body, headers, QueryString.contentType.xwwwformurlencoded));

        rafRestResponseEx = new RAFRestResponseEx(queryStringEx.response);
        accessToken= rafRestResponseEx.getValue("/access_token");
        SpotifyAPIMap.initializeRequests();
    }

    @When("I send a GET request")
    public void iSendAGETRequest(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        StringBuilder urlParameters = new StringBuilder();
        String userAgent = SharedVariables.configuration.getProperty("userAgent");
        QueryStringEx queryStringEx = null;
        Map<String, String> headers = new HashMap<>();

        data = addSavedValues(data);

        if (data.get("Authorization") != null) {
            headers.put("Authorization", "Bearer " + accessToken);
            data.remove("Authorization");
        }

        if (data.get("User-Agent") != null) {
            headers.put("User-Agent", userAgent);
            data.remove("User-Agent");
        }

        if (data.get("EndPoint") != null) {
            queryStringEx = findQueryStringByName(data);
        }

        //adds all parameters together to 'urlParameters'
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!entry.getKey().equals("EndPoint") && !entry.getKey().equals("Authorization") && !entry.getKey().equals("User-Agent") && !entry.getKey().equals("WaitTime")) {
                urlParameters.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        //removes '&' at the end and adds '?' to the beginning of the text if 'urlParameters' is not empty
        if (!urlParameters.toString().isEmpty())
            urlParameters = new StringBuilder("?" + urlParameters.substring(0, urlParameters.length() - 1));

        GlobalParameters.testCaseBase.success(queryStringEx.GET(urlParameters.toString(), headers));
        rafRestResponseEx = new RAFRestResponseEx(queryStringEx.response);
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest(DataTable testData) {
        Map<String, String> data = testData.asMap(String.class, String.class);

        String urlParameters = "";
        String userAgent = SharedVariables.configuration.getProperty("userAgent");
        QueryStringEx queryStringEx = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> body = null;

        data = addSavedValues(data);

        if (data.get("Authorization") != null) {
            headers.put("Authorization", "Bearer " + accessToken);
            data.remove("Authorization");
        }

        if (data.get("User-Agent") != null) {
            headers.put("User-Agent", userAgent);
            data.remove("User-Agent");
        }

        if (data.get("EndPoint") != null) {
            queryStringEx = findQueryStringByName(data);
            body = findBodyByEndPoint(data);
        }

        if (data.get("urlParameters") == null || data.get("urlParameters").equals("enabled")) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (!entry.getKey().equals("EndPoint") && !entry.getKey().equals("Authorization")) {
                    urlParameters += entry.getKey() + "=" + entry.getValue() + "&";
                }
            }
        }

        if (urlParameters != "")
            urlParameters = "?" + urlParameters.substring(0, urlParameters.length() - 1);

        if (data.get("contentType") == null || data.get("contentType").equals("json")) {
            GlobalParameters.testCaseBase.success(queryStringEx.POST(urlParameters, body, headers, QueryString.contentType.json));
        } else if (data.get("contentType").equals("xwwwurlencoded")) {
            GlobalParameters.testCaseBase.success(queryStringEx.POST(urlParameters, body, headers, QueryString.contentType.xwwwformurlencoded));
        }

        rafRestResponseEx = new RAFRestResponseEx(queryStringEx.response);
    }

    @When("I send a PUT request")
    public void iSendAPUTRequest(DataTable testData) {
        Map<String, String> data = testData.asMap(String.class, String.class);

        StringBuilder urlParameters = new StringBuilder();
        QueryStringEx queryStringEx = null;
        java.util.Map<String, String> headers = new HashMap<>();
        java.util.Map<String, Object> body = null;

        data = addSavedValues(data);

        if (data.get("Authorization") != null) {
            headers.put("Authorization", "Bearer " + accessToken);
        }

        if (data.get("EndPoint") != null) {
            queryStringEx = findQueryStringByName(data);
        }

        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!entry.getKey().equals("EndPoint") && !entry.getKey().equals("Authorization")) {
                urlParameters.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        if (!urlParameters.toString().equals(""))
            urlParameters = new StringBuilder("?" + urlParameters.substring(0, urlParameters.length() - 1));

        GlobalParameters.testCaseBase.success(queryStringEx.PUT(urlParameters.toString(), body, headers, QueryString.contentType.xwwwformurlencoded));

        rafRestResponseEx = new RAFRestResponseEx(queryStringEx.response);
    }

    @When("I send a DELETE request")
    public void iSendADELETERequest(DataTable testData) {
        Map<String, String> data = testData.asMap(String.class, String.class);

        String urlParameters = "";
        String userAgent = SharedVariables.configuration.getProperty("userAgent");
        QueryStringEx queryStringEx = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> body = null;

        data = addSavedValues(data);

        if (data.get("Authorization") != null) {
            headers.put("Authorization", "Bearer " + accessToken);
        }

        if (data.get("User-Agent") != null) {
            headers.put("User-Agent", userAgent);
        }

        if (data.get("EndPoint") != null) {
            queryStringEx = findQueryStringByName(data);
            body = findBodyByEndPoint(data);
        }

        if (data.get("urlParameters") == null || data.get("urlParameters").equals("enabled")) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (!entry.getKey().equals("EndPoint") && !entry.getKey().equals("Authorization")) {
                    urlParameters += entry.getKey() + "=" + entry.getValue() + "&";
                }
            }
        }

        if (urlParameters != "")
            urlParameters = "?" + urlParameters.substring(0, urlParameters.length() - 1);

        if (data.get("contentType") == null || data.get("contentType").equals("json")) {
            GlobalParameters.testCaseBase.success(queryStringEx.DELETE(urlParameters, body, headers, QueryString.contentType.json));
        } else if (data.get("contentType").equals("xwwwurlencoded")) {
            GlobalParameters.testCaseBase.success(queryStringEx.DELETE(urlParameters, body, headers, QueryString.contentType.xwwwformurlencoded));
        }

        rafRestResponseEx = new RAFRestResponseEx(queryStringEx.response);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int statusCode) {
        GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyResponseCode(statusCode));
    }

    @Then("the response status message should be {string}")
    public void theResponseStatusMessageShouldBe(String responseStatusMessage) {
        GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyResponseStatusMessage(responseStatusMessage));

    }
    @And("the {string} array is {string}")
    public void theArrayIs(String sField, String state) {
        if (state.equalsIgnoreCase("not empty"))
        {
            GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyArrayEmpty(findJsonPath(sField),false));
        }
        else{
            GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyArrayEmpty(findJsonPath(sField),true));
        }
    }

    @And("the {string} array contains {string} item")
    public void theArrayContainsItem(String sField, String itemName) {
        GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyArrayContains(findJsonPath(sField),itemName,true));
    }

    @And("the {string} array does not contain {string} item")
    public void theArrayDoesNotContainItem(String sField, String itemName) {
        GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyArrayContains(findJsonPath(sField),itemName,false));
    }

    @And("the {string} field should be {string}")
    public void theFieldShouldBe(String sField, String sValue) {
        GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyValue(findJsonPath(sField), sValue, true));
    }

    @And("the {string} field should contain {string}")
    public void theFieldShouldContain(String sField, String sValue) {
        GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyContains(findJsonPath(sField), sValue, true));
    }

    @And("the {string} field should not be {string}")
    public void theFieldShouldNotBe(String sField, String sValue) {
        GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyValue(findJsonPath(sField), sValue, false));
    }

    @And("the {string} field should not be empty")
    public void theFieldShouldNotBeEmpty(String sPath) {
        GlobalParameters.testCaseBase.success(rafRestResponseEx.verifyEmpty(findJsonPath(sPath), false));
    }

    @And("save field {string} value as {string}")
    public void saveFieldValueAs(String sPath, String sSaveAs) {
        GlobalParameters.testCaseBase.success(UIReferences.eval().evaluate(() ->
                savedValues.put(sSaveAs, rafRestResponseEx.getValue(findJsonPath(sPath))), this, ""));
    }

}