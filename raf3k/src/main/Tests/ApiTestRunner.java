import org.junit.jupiter.api.Test;
import static java.util.Map.entry;

import org.raf3k.apitesting.basetypes.QueryString;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.raf3k.testproject.extendedtypes.api.RAFRestResponseEx;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class ApiTestRunner extends UITestCase {
    public ApiTestRunner() {
        super("Api test case", "TC005", "Filip Borić");
    }

    @Test
    void First() throws InterruptedException {
        Map.initializeAPI();
        newStep(1, "Send request to 'https://swapi.dev/api/' endpoint.");
        success(Map.API.Get.get("/1", null));
        RAFRestResponseEx resp = new RAFRestResponseEx(Map.API.Get.response);
        success(resp.verifyResponseCode(200));
        success(resp.verifyValue("id", 1, true));
        success(resp.verifyContains("species.name", "bulba", true));
        success(resp.verifyContains("species.name", "woof", false));
        success(resp.verifyValue("abilities[0].is_hidden", false, true));
        success(resp.verifyValue("id", 2, false));
        success(resp.verifyEmpty("height", false));
        success(resp.verifyEmpty("sprites.back_female", true));
        success(resp.verifyArrayEmpty("past_types", true));
        success(resp.verifyArrayContains("abilities", java.util.Map.ofEntries(
                entry("ability", java.util.Map.ofEntries(
                        entry("name", "overgrow"),
                        entry("url", "https://pokeapi.co/api/v2/ability/65/")
                )),
                entry("is_hidden", false),
                entry("slot", 1)
        ), true));
        success(resp.verifyArrayContains("abilities", java.util.Map.ofEntries(
                entry("ability", java.util.Map.ofEntries(
                        entry("name", "overgrowe"),
                        entry("url", "https://pokeapi.co/api/v2/ability/65/")
                )),
                entry("is_hidden", true),
                entry("slot", 1)
        ), false));

        //Twitter auth
        Map.accessToken();
        success(Map.TwitterMap.RequestToken.page.navigate());
        success(Map.TwitterMap.RequestToken.lnkTempAccess.click());
        Map.corsAnyWhere();
        success(Map.TwitterMap.RequestAccess.page.verifyDisplayed());
        success(Map.TwitterMap.RequestAccess.btnRequest.click());
        Map.accessToken();
        success(Map.TwitterMap.RequestToken.page.navigate());
        success(Map.TwitterMap.RequestToken.page.verifyDisplayed());
        success(Map.TwitterMap.RequestToken.txtClientId.setText("ZWdsMGV0b0c0anl6ZVRHUFRTd2w6MTpjaQ", false, false, false));
        success(Map.TwitterMap.RequestToken.txtClientSecret.setText("Basic Wldkc01HVjBiMGMwYW5sNlpWUkhVRlJUZDJ3Nk1UcGphUTpHQjRhZnZPNHJxWW5wZjFsMUdPQXRHX214bDFCeTJqT2Z0d0syTlB1dHdkSVhrenl3NQ==", false, false, false));
        success(Map.TwitterMap.RequestToken.txtScope.setText("tweet.write tweet.read users.read follows.read follows.write offline.access like.write tweet.moderate.write mute.write", false, false, false));
        success(Map.TwitterMap.RequestToken.btnRequest.click());
        Map.twitterMapInit();
        TimeUnit.SECONDS.sleep(2);
        success(Map.TwitterMap.OAuth.btnLogin.click());
        success(Map.TwitterMap.OAuth.txtUsername.setText("DMilic5", false, false, true));
        success(Map.TwitterMap.OAuth.btnNext.click());
        if (Map.TwitterMap.OAuth.txtPhoneOrEmail.control() != null) {
            success(Map.TwitterMap.OAuth.txtUsername.setText("+385989759811", false, false, true));
            success(Map.TwitterMap.OAuth.btnNext.click());
        }
        success(Map.TwitterMap.OAuth.txtPassword.setText("mojalozinka755", false, false, true));
        success(Map.TwitterMap.OAuth.btnLogin.click());
        success(Map.TwitterMap.OAuth.btnAuthorize.click());
        Map.accessToken();
        success(Map.TwitterMap.RequestToken.btnRequest.click());
        TimeUnit.SECONDS.sleep(5);
        var accessToken = Map.TwitterMap.RequestToken.lblAccessToken.getControlText();

        Map.initializeAPITweeter();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        java.util.Map<String, Object> body = java.util.Map.ofEntries(
                entry("text", "tweet test from Java " + dtf.format(now))
        );
        java.util.Map<String, String> headers = java.util.Map.ofEntries(
                entry("Authorization", "Bearer " + accessToken)
        );
        success(Map.API.Tweet.post("", body, headers, QueryString.contentType.json));
        var tweetId = Map.API.Tweet.response.response.jsonPath().getJsonObject("data.id").toString();
        success(Map.API.Tweet.response.verifyResponseCode(201));
        success(Map.API.Tweet.delete("/" + tweetId, headers));
        success(Map.API.Tweet.response.verifyResponseCode(200));
    }
}
