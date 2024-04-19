package org.raf3k.apitesting.basetypes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import org.junit.jupiter.api.Assertions;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.ControlObject;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.logging.Success;

import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RAFRestResponse extends ControlObject {
    public APIResponse response;
    public Exception Ex;

    public QueryString queryStringBase;

    public RAFRestResponse(QueryString query, APIResponse resp) {
        response = resp;
        this.sAlias = query.sAlias;
        this.sPath = query.sPath;
        queryStringBase = query;
    }

    public RAFRestResponse(Exception ex) {
        Ex = new RuntimeException(String.format("Error getting response : <br> {0}", ex.getMessage()));
        DebugLog.add(Ex);
        DebugLog.add(ex);
    }

    public RAFRestResponse() {
    }

    /**
     * Method verifies response code.
     *
     * @param responseCode Response code to be verified.
     * @return Success Object.
     */
    public Success verifyResponseCode(Integer responseCode) {
        Success success = new Success(this);
        String sMessageAddon = "";
        String jsonPrettyResponse = "";
        try {
            if (Ex != null)
                throw new RuntimeException(Ex);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.body());
            jsonPrettyResponse = jsonResponse.toPrettyString();

            Assertions.assertEquals(responseCode, response.status());

            return success.finish(null);
        } catch (Throwable ex) {
            sMessageAddon += "<h3>JSON Response:</h3> <br><p>" + jsonPrettyResponse + "</p><br>";
            success.sMessageAddon = sMessageAddon;
            return success.finish(new Exception(ex));
        }
    }

//    /**
//     * Method verifies response message.
//     *
//     * @param responseMessage Text to be verified.
//     * @return Success object.
//     */
//    public Success verifyResponseMessage(String responseMessage) {
//        return UIReferences.eval().evaluate(() ->
//        {
//            if (Ex != null)
//                throw new RuntimeException(Ex);
//
//            response.then().statusLine(responseMessage);
//
//        }, this, "");
//    }
//
//    /**
//     * Method verifies response code and message.
//     *
//     * @param responseCode    Response code to be verified.
//     * @param responseMessage Response message to be verified.
//     * @return Success object.
//     */
//    public Success verifyResponseCodeAndMessage(Integer responseCode, String responseMessage) {
//        return UIReferences.eval().evaluate(() ->
//        {
//            if (Ex != null)
//                throw new RuntimeException(Ex);
//
//            response.then().statusCode(responseCode).statusLine(responseMessage);
//
//        }, this, "");
//    }

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
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = null;

            try {
                jsonResponse = objectMapper.readTree(response.body());
                if (bExists) {
                    Assertions.assertEquals(sValue,jsonResponse.at(sPath).textValue());
                } else {
                    Assertions.assertNotEquals(sValue,jsonResponse.at(sPath));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }, this, "");
    }

    /**
     * Method verifies if json field is empty or not.
     *
     * @param sPath  Path to the field in the response.
     * @param bEmpty >Verifies if the json field is empty or not.
     * @return Success object.
     */
    public Success verifyEmpty(String sPath, Boolean bEmpty) {
        return UIReferences.eval().evaluate(() ->
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = null;

            try {
                jsonResponse = objectMapper.readTree(response.body());
                if (bEmpty) {
                    Assertions.assertTrue(jsonResponse.at(sPath).textValue().isEmpty());
                } else {
                    Assertions.assertFalse(jsonResponse.at(sPath).textValue().isEmpty());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
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
    public Success verifyContains(String sPath, String sValue, Boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = null;

            try {
                jsonResponse = objectMapper.readTree(response.body());
                if (bExists) {
                    Assertions.assertTrue(jsonResponse.at(sPath).textValue().contains(sValue));
                } else {
                    Assertions.assertFalse(jsonResponse.at(sPath).textValue().contains(sValue));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }, this, "");
    }

//    /**
//     * Method verifies if an array exists and contains a value.
//     *
//     * @param sPath     Path to the field in the response.
//     * @param sValue    Value to be verified.
//     * @param bContains Should array contain the value or not.
//     * @return Success object.
//     */
//    public Success verifyArrayContains(String sPath, Object sValue, Boolean bContains) {
//        return UIReferences.eval().evaluate(() ->
//        {
//            if (bContains) {
//                response.then().body(sPath, Matchers.hasItem(sValue));
//            } else {
//                response.then().body(sPath, Matchers.not(Matchers.hasItem(sValue)));
//            }
//
//        }, this, "");
//    }
//
//    /**
//     * Method verifies if an array is empty or not.
//     *
//     * @param sPath  Path to the json field.
//     * @param bEmpty Verifies if an array is empty or not.
//     * @return
//     */
//    public Success verifyArrayEmpty(String sPath, Boolean bEmpty) {
//        return UIReferences.eval().evaluate(() ->
//        {
//            if (bEmpty) {
//                response.then().body(sPath, Matchers.hasSize(lessThan(1)));
//            } else {
//                response.then().body(sPath, Matchers.hasSize(greaterThan(0)));
//            }
//
//        }, this, "");
//    }
//

    /**
     * Method returns response content.
     *
     * @return Response content.
     */
    public String getResponseBody() {
        if (Ex != null)
            throw new RuntimeException(Ex);

        String sResponseContent = response.body().toString();

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

        int iResponseCode = response.status();

        return iResponseCode;
    }
}
