package org.raf3k.apitesting.basetypes;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.logging.Success;

import static org.hamcrest.Matchers.*;

public class RAFRestResponse {
    public RequestSpecification Request;
    public static Response response;
    public Exception Ex;
    public String sAlias;
    public String sPath;

    public QueryString QueryStringBase;

    public RAFRestResponse(QueryString query, Response resp) {
        response = resp;
        sAlias = query.sAlias;
        sPath = query.sPath;
        QueryStringBase = query;
    }

    public RAFRestResponse(Exception ex) {
        Ex = new RuntimeException(String.format("Error getting response : <br> {0}", ex.getMessage()));
        DebugLog.add(Ex);
        DebugLog.add(ex);
    }

    /**
     * Method verifies response code
     *
     * @param responseCode Response code to be verified.
     * @return Success Object.
     */
    public Success verifyResponseCode(Integer responseCode) {
        return UIReferences.eval().evaluate(() ->
        {
            if (Ex != null)
                throw new RuntimeException(Ex);

            response.then().statusCode(responseCode);

        }, this, "");
    }

    /**
     * Method verifies response message.
     *
     * @param responseMessage Text to be verified.
     * @return Success object.
     */
    public Success verifyResponseMessage(String responseMessage) {
        return UIReferences.eval().evaluate(() ->
        {
            if (Ex != null)
                throw new RuntimeException(Ex);

            response.then().statusLine(responseMessage);

        }, this, "");
    }

    /**
     * Method verifies response code and message.
     *
     * @param responseCode    Response code to be verified.
     * @param responseMessage Response message to be verified.
     * @return Success object.
     */
    public Success verifyResponseCodeAndMessage(Integer responseCode, String responseMessage) {
        return UIReferences.eval().evaluate(() ->
        {
            if (Ex != null)
                throw new RuntimeException(Ex);

            response.then().statusCode(responseCode).statusLine(responseMessage);

        }, this, "");
    }

    /**
     * Method verifies if value exists in the response.
     *
     * @param sPath   Path to the field in the response.
     * @param sValue  Value to be verified.
     * @param bExists Value exists or not.
     * @return Success object.
     */
    public Success verifyValue(String sPath, Object sValue, Boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bExists) {
                response.then().body(sPath, Matchers.equalTo(sValue));
            } else {
                response.then().body(sPath, Matchers.not(Matchers.equalTo(sValue)));
            }

        }, this, "");
    }

    /**
     * Method verifies if json field is empty or not.
     *
     * @param sPath  Path to the field in the response.
     * @param bEmpty >Verifies if the json field is empty or not
     * @return Success object.
     */
    public Success verifyEmpty(String sPath, Boolean bEmpty) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bEmpty) {
                response.then().body(sPath, Matchers.nullValue());
            } else {
                response.then().body(sPath, Matchers.notNullValue());
            }

        }, this, "");
    }

    /**
     * Method verifies if a path contains a value.
     *
     * @param sPath   Path to the field in the response.
     * @param sValue  Value to be verified.
     * @param bExists value contains or not.
     * @return
     */
    public Success verifyContains(String sPath, Object sValue, Boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bExists) {
                response.then().body(sPath, Matchers.contains(sValue));
            } else {
                response.then().body(sPath, Matchers.not(Matchers.contains(sValue)));
            }
        }, this, "");
    }

    /**
     * Method verifies if an array exists and contains a value.
     *
     * @param sPath     Path to the field in the response.
     * @param sValue    Value to be verified.
     * @param bContains Should array contain the value or not
     * @return Success object.
     */
    public Success verifyArrayContains(String sPath, Object sValue, Boolean bContains) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bContains) {
                response.then().body(sPath, Matchers.hasItem(sValue));
            } else {
                response.then().body(sPath, Matchers.not(Matchers.hasItem(sValue)));
            }

        }, this, "");
    }

    /**
     * Method verifies if an array is empty or not.
     * @param sPath Path to the json field.
     * @param bEmpty Verifies if an array is empty or not.
     * @return
     */
    public Success verifyArrayEmpty(String sPath, Boolean bEmpty){
        return UIReferences.eval().evaluate(() ->
        {
            if (bEmpty) {
                response.then().body(sPath, Matchers.hasSize(lessThan(1)));
            } else {
                response.then().body(sPath, Matchers.hasSize(greaterThan(0)));
            }

        }, this, "");
    }


    /**
     * Method returns response content.
     *
     * @return Response content.
     */
    public String getResponseBody() {
        if (Ex != null)
            throw new RuntimeException(Ex);

        String sResponseContent = response.body().asString();

        return sResponseContent;
    }

    /**
     * Method returns response status code.
     *
     * @return Response status code.
     */
    public int getResponseCode() {
        if (Ex != null)
            throw new RuntimeException(Ex);

        int iResponseCode = response.statusCode();

        return iResponseCode;
    }
}
