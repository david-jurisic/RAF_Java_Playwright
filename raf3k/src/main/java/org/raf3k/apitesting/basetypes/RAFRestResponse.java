package org.raf3k.apitesting.basetypes;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.devtools.Message;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.logging.Success;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

public class RAFRestResponse {
    public RequestSpecification Request;
    public static Response Response;
    public Exception Ex;
    public String sAlias;
    public String sPath;

    public QueryString QueryStringBase;

    public RAFRestResponse(QueryString query, Response resp) {
        Response = resp;
        sAlias = query.sAlias;
        sPath = query.sPath;
        QueryStringBase = query;
    }

    public RAFRestResponse(Exception ex) {
        Ex = new RuntimeException(String.format("Error getting response : <br> {0}", ex.getMessage()));
        DebugLog.add(Ex);
        DebugLog.add(ex);
    }

    public RAFRestResponse() {

    }

    public Success verifyResponseCode(int responseCode) {
        return UIReferences.eval().evaluate(() ->
        {
            if (Ex != null)
                throw new RuntimeException(Ex);

            //try {
               Response.then().statusCode(responseCode);

               /*
            } catch (AssertionError ex) {
                throw new RuntimeException(MessageFormat.format("Failed. Response code is {0}, expected response code {1}"
                        , Response.statusCode(), responseCode));
            }
                */


        }, this, "");
    }

    public Success verifyResponseMessage(String responseMessage) {
        return UIReferences.eval().evaluate(() ->
        {
            if (Ex != null)
                throw new RuntimeException(Ex);

            String sResponseMessage = Response.statusLine();

            if (sResponseMessage.contains(responseMessage))
                throw new RuntimeException(String.format("Failed. Response message is: {0}", sResponseMessage));

        }, this, "");
    }

    public Success verifyResponseCodeAndMessage(int responseCode, String responseMessage) {
        return UIReferences.eval().evaluate(() ->
        {
            if (Ex != null)
                throw new RuntimeException(Ex);

            String sResponseMessage = Response.body().toString();
            int iResponseCode = Response.statusCode();

            if (iResponseCode != responseCode)
                throw new RuntimeException(String.format("Failed. Response code is: {0}", iResponseCode));

            if (sResponseMessage.contains(responseMessage))
                throw new RuntimeException(String.format("Failed. Response message is: {0}", sResponseMessage));

        }, this, "");
    }

    public String getResponseBody() {
        if (Ex != null)
            throw new RuntimeException(Ex);

        String sResponseContent = Response.body().asString();

        return sResponseContent;
    }

    public int getResponseCode() {
        if (Ex != null)
            throw new RuntimeException(Ex);

        int iResponseCode = Response.statusCode();

        return iResponseCode;
    }
}
