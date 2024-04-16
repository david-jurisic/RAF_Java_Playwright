package Shared;

import ExtendedTypes.API.QueryStringEx;
import ExtendedTypes.API.RAFRestResponseEx;
import Maps.UI.XrayMap;
import Settings.GlobalParameters;
import org.raf3k.apitesting.basetypes.QueryString;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class XrayAPICalls {
    static String RestAPIUrl = SharedVariables.configuration.getProperty("restApiUrl");
    static String ProjectKey = SharedVariables.configuration.getProperty("projectKey");
    //static String BearerToken = SharedVariables.configuration.getProperty("BearerToken");
    //static String TestCycleKey = SharedVariables.configuration.getProperty("testCycleKey");

    static RAFRestResponseEx rafRestResponseEx;

    public static String getBearerToken() {
        XrayMap.initialize();
        String bearerToken="";

        Map<String, String> headers = new HashMap<>();
        Map<String, Object> body = Map.ofEntries(
                entry("client_id", XrayShared.Authorization.ClientId),
                entry("client_secret", XrayShared.Authorization.ClientSecret));

//        QueryStringEx queryStringEx = XrayMap.authenticate();
//        GlobalParameters.testCaseBase.success(queryStringEx.POST("", body, headers, QueryString.contentType.json));
//
//        rafRestResponseEx = new RAFRestResponseEx(queryStringEx.response);
//        bearerToken = rafRestResponseEx.response.getBody().asString();

        return bearerToken;
    }

    public static void GET(String endpoint) {
        String BearerToken = getBearerToken();

        try {
            if (RestAPIUrl != null && RestAPIUrl.equals("")) return;
            if (BearerToken != null && BearerToken.equals("")) return;

            var url = SharedVariables.configuration.getProperty("restApiUrl") + endpoint;
            var httpsUrl = new URL(url);

            HttpsURLConnection httpRequest = (HttpsURLConnection) httpsUrl.openConnection();
            httpRequest.setRequestMethod("GET");
            httpRequest.setRequestProperty("Content-Type", "application/json");
            httpRequest.setRequestProperty("Authorization", "Bearer " + BearerToken);
            httpRequest.setDoOutput(true);
            httpRequest.setDoInput(true);
            httpRequest.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpRequest.getInputStream()));
            var response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            br.close();

            System.out.println(response);
            httpRequest.disconnect();
        } catch (IOException e) {
            DebugLog.add(e);
            throw new RuntimeException(e);
        }
    }

    public static void POSTTestResults(String endpoint, String testCaseKey, Boolean bPass) {
        String BearerToken = getBearerToken();

        try {
            if (RestAPIUrl != null && RestAPIUrl.equals("")) return;
            if (BearerToken != null && BearerToken.equals("")) return;
            if (ProjectKey != null && ProjectKey.equals("")) return;
            //if (TestCycleKey != null && TestCycleKey.equals("")) return;
            if (endpoint != null && endpoint.equals("")) return;
            if (testCaseKey != null && testCaseKey.equals("")) return;

            var url = SharedVariables.configuration.getProperty("restApiUrl") + endpoint;
            var httpsUrl = new URL(url);

            HttpsURLConnection httpRequest = (HttpsURLConnection) httpsUrl.openConnection();
            httpRequest.setRequestMethod("POST");
            httpRequest.setRequestProperty("Content-Type", "application/json");
            httpRequest.setRequestProperty("Authorization", "Bearer " + BearerToken);
            httpRequest.setDoOutput(true);
            httpRequest.setDoInput(true);

            var sStatus = bPass ? "PASS" : "FAIL";
            if (sStatus == null || sStatus.equals("")) return;

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            LocalDateTime now = LocalDateTime.now();
            String body = "{\"projectKey\":\"" + ProjectKey + "\",\"testCaseKey\":\"" + testCaseKey + "\", \"statusName\": \"" + sStatus + "\", \"testScriptResults\": [{\"statusName\": \"" + sStatus + "\", \"actualEndDate\": \"" + dtf.format(now) + "\"}]}";
            var jsonContent = body.replace("\"", "");
            byte[] byteArray = body.getBytes(StandardCharsets.UTF_8);
            httpRequest.addRequestProperty("Content-Length", String.valueOf(byteArray.length));
            httpRequest.connect();
            httpRequest.getOutputStream().write(byteArray);

            long length = 0;
            try {
                length = httpRequest.getContentLengthLong();
            } catch (Exception e) {
                length = httpRequest.getContentLength();
            }
            httpRequest.disconnect();

        } catch (IOException e) {
            DebugLog.add(e);
            throw new RuntimeException(e);
        }
    }

    public static void POSTTestRunResults(String endpoint, String testCaseKey, Boolean bPass, String htmlReportPath) {
        String BearerToken = getBearerToken();

        try {
            if (RestAPIUrl != null && RestAPIUrl.equals("")) return;
            if (BearerToken != null && BearerToken.equals("")) return;
            if (ProjectKey != null && ProjectKey.equals("")) return;
            if (endpoint != null && endpoint.equals("")) return;
            if (testCaseKey != null && testCaseKey.equals("")) return;

            var url = SharedVariables.configuration.getProperty("restApiUrl") + endpoint;
            var httpsUrl = new URL(url);

            HttpsURLConnection httpRequest = (HttpsURLConnection) httpsUrl.openConnection();
            httpRequest.setRequestMethod("POST");
            httpRequest.setRequestProperty("Content-Type", "application/json");
            httpRequest.setRequestProperty("Authorization", "Bearer " + BearerToken);
            httpRequest.setDoOutput(true);
            httpRequest.setDoInput(true);
            httpRequest.connect();

            var sStatus = bPass ? "PASS" : "FAIL";
            if (sStatus == null || sStatus.equals("")) return;

            var body = new XrayBody(ProjectKey, testCaseKey, sStatus, htmlReportPath);
            byte[] byteArray = body.toString().getBytes(StandardCharsets.UTF_8);
            OutputStream stream = httpRequest.getOutputStream();
            stream.write(byteArray);
            httpRequest.disconnect();

        } catch (IOException e) {
            DebugLog.add(e);
            throw new RuntimeException(e);
        }
    }

    static class XrayBody {
        public XrayBody(String projectKey, String testCaseKey, String statusName, String htmlReportPath) throws IOException {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            LocalDateTime now = LocalDateTime.now();
            String dateTimeNow = dtf.format(now);

            ArrayList<TestScriptResult> testScriptResults = new ArrayList<TestScriptResult>();
            TestScriptResult testScriptResult = new TestScriptResult(statusName, dateTimeNow, htmlReportPath);
            testScriptResults.add(testScriptResult);
        }
    }

    static class TestScriptResult {
        public TestScriptResult(String statusName, String actualEndDate, String htmlReportPath) throws IOException {
            var actualResult = Files.readAllLines(Path.of(htmlReportPath));
        }
    }
}
