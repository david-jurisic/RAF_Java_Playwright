package org.raf3k.apitesting.basetypes;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.raf3k.apitesting.APIReferences;
import org.raf3k.shared.ControlObject;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.restassured.*;

public class QueryString extends ControlObject {
    private String sQueryString;
    public RAFRestResponse response = null;

    public QueryString(String _sQueryString) {
        String sControl = this.getClass().toString();
        this.sControlType = sControl.substring(sControl.lastIndexOf((".")) + 1);
        this.sAlias = MessageFormat.format("({0})", _sQueryString);
        this.sQueryString = _sQueryString;
        if (this.sControlType.toLowerCase().substring(this.sControlType.length() - 2).contains("ex"))
            this.sPath = APIReferences.get_Hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());
        else
            this.sPath = APIReferences.get_Hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    public QueryString() {
    }

    /**
     * Mathod generates html string for a table
     *
     * @param sNameHeader  name header
     * @param sValueHeader value header
     * @param map          map of headers
     * @return Html string of a table
     */
    public String generateTableFromMap(String sNameHeader, String sValueHeader, Map<String, String> map) {
        if (map == null) return "";
        var trs = map.entrySet()
                .stream()
                .map(x -> MessageFormat.format("<tr><td>{0}</td><td>{1}</td></tr>", x.getKey(), x.getValue()))
                .collect(Collectors.toList());

        var tableContents = String.join("", trs);

        return "<table><tr><th>" + sNameHeader + "</th><th>" + sValueHeader + "</th></tr>" + tableContents + "</table>";
    }

    /**
     * Mathod generates html string for a table from a map which contains object
     *
     * @param sNameHeader  name header
     * @param sValueHeader value header
     * @param map          map of headers
     * @return Html string of a table
     */
    public String generateTableFromMapWithObject(String sNameHeader, String sValueHeader, Map<String, Object> map) {
        if (map == null) return "";
        var trs = map.entrySet()
                .stream()
                .map(x -> MessageFormat.format("<tr><td>{0}</td><td>{1}</td></tr>", x.getKey(), x.getValue().toString()))
                .collect(Collectors.toList());

        var tableContents = String.join("", trs);

        return "<table><tr><th>" + sNameHeader + "</th><th>" + sValueHeader + "</th></tr>" + tableContents + "</table>";
    }

    /**
     * Method requests data from a specified resource.
     *
     * @param sUrlParameters Url parameters to be set.
     * @param headers        Headers to be set.
     * @return Success object.
     */
    public Success get(String sUrlParameters, Map<String, String> headers) {
        response = null;
        Response rest = null;
        RequestSpecification req = RestAssured.given();
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null && !sUrlParameters.isEmpty())
                sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (headers != null)
                sMessageAddon += "<h3> Request headers:</h3> <br>" + generateTableFromMap("Header", "Value", headers);

            suc.sMessageAddon = sMessageAddon;

            String sPath = APIReferences.currentPageContext + sQueryString;

            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            if (headers != null)
                req.headers(headers);

            rest = req.when().get(sPath);

            response = new RAFRestResponse(this, rest);
            return suc.finish(null);

        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    /**
     * Method sends given data to create a resource
     *
     * @param sUrlParameters Url parameters to be set.
     * @param body           Body to be set
     * @param headers        Headers to be set.
     * @return Success object.
     */
    public Success post(String sUrlParameters, Map<String, Object> body, Map<String, String> headers, contentType contentType) {
        response = null;
        Response rest = null;
        RequestSpecification req = RestAssured.given();
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null && !sUrlParameters.isEmpty())
                sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (body != null)
                sMessageAddon += "<h3> Message Body:</h3> <br><p>" + generateTableFromMapWithObject("Parameter Name", "Value", body) + "</p><br>";

            if (headers != null)
                sMessageAddon += "<h3> Request headers:</h3> <br>" + generateTableFromMap("Header", "Value", headers);


            suc.sMessageAddon = sMessageAddon;

            String sPath = APIReferences.currentPageContext + sQueryString;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            if (headers != null)
                req.headers(headers);

            switch (contentType) {
                case json:
                    req.contentType(ContentType.JSON);
                    break;
                case xwwwformurlencoded:
                    req.contentType("application/x-www-form-urlencoded");
                    break;
            }

            if (body != null && body.size() > 0) {
                switch (contentType) {
                    case xwwwformurlencoded:
                        req.formParams(body);
                        break;
                    default:
                        req.body(body);
                        break;
                }
            }

            rest = req.when().post(sPath);
            RAFRestResponse resp = new RAFRestResponse(this, rest);
            response = resp;
            return suc.finish(null);
        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    /**
     * Method sends given data to update a resource
     *
     * @param sUrlParameters Url parameters to be set.
     * @param body           Body to be set
     * @param headers        Headers to be set.
     * @return Success object.
     */
    public Success put(String sUrlParameters, Map<String, Object> body, Map<String, String> headers, contentType contentType) {
        response = null;
        Response rest = null;
        RequestSpecification req = RestAssured.given();
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null && !sUrlParameters.isEmpty())
                sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (body != null)
                sMessageAddon += "<h3> Message Body:</h3> <br><p>" + generateTableFromMapWithObject("Parameter Name", "Value", body) + "</p><br>";

            if (headers != null)
                sMessageAddon += "<h3> Request headers:</h3> <br>" + generateTableFromMap("Header", "Value", headers);

            suc.sMessageAddon = sMessageAddon;

            String sPath = APIReferences.currentPageContext + sQueryString;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            if (headers != null)
                req.headers(headers);

            switch (contentType) {
                case json:
                    req.contentType(ContentType.JSON);
                    break;
                case xwwwformurlencoded:
                    req.contentType("application/x-www-form-urlencoded");
                    break;
            }

            if (body != null && body.size() > 0) {
                switch (contentType) {
                    case xwwwformurlencoded:
                        req.formParams(body);
                        break;
                    default:
                        req.body(body);
                        break;
                }
            }

            rest = req.when().put(sPath);
            RAFRestResponse resp = new RAFRestResponse(this, rest);
            response = resp;
            return suc.finish(null);
        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    /**
     * Method deletes data from a resource.
     *
     * @param sUrlParameters Url parameters to be set.
     * @param headers        Headers to be set.
     * @return Success object.
     */
    public Success delete(String sUrlParameters, Map<String, String> headers) {
        response = null;
        Response rest = null;
        RequestSpecification req = RestAssured.given();
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null && !sUrlParameters.isEmpty())
                sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (headers != null)
                sMessageAddon += "<h3> Request headers:</h3> <br>" + generateTableFromMap("Header", "Value", headers);

            suc.sMessageAddon = sMessageAddon;

            String sPath = APIReferences.currentPageContext + sQueryString;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            if (headers != null)
                req.headers(headers);

            rest = req.when().delete(sPath);
            RAFRestResponse resp = new RAFRestResponse(this, rest);
            response = resp;
            return suc.finish(null);
        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    public enum contentType {
        json,
        xwwwformurlencoded
    }
}
