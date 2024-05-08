package org.raf3k.apitesting.basetypes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import org.raf3k.apitesting.APIReferences;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.ControlObject;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryString extends ControlObject {
    private String sQueryString;
    public RAFRestResponse response = null;

    private Playwright playwright;
    private APIRequestContext apiRequestContext;

    private RequestOptions requestOptions;

    public void createPlaywright() {
        playwright = Playwright.create();
    }

    public void setApiRequestContext(Map<String, String> headers) {
        apiRequestContext = playwright.request()
                .newContext(new APIRequest.NewContextOptions().setBaseURL(APIReferences.currentPageContext)
                        .setExtraHTTPHeaders(headers));
    }

    public void setApiRequestOptions(Map<String, Object> body,contentType contentType) {
        requestOptions = RequestOptions.create();
        var formData = FormData.create();
        if (body != null) {
            for (var item : body.entrySet()) {
                    formData.set(item.getKey(), item.getValue().toString());
            }

            if (contentType == QueryString.contentType.json) {
                requestOptions.setData(body);
            } else {
                requestOptions.setForm(formData);

            }
        }
    }

    public void playwrightTeardown() {
        apiRequestContext.dispose();
        playwright.close();
    }

    public QueryString(String _sQueryString) {
        String sControl = this.getClass().toString();
        this.sControlType = sControl.substring(sControl.lastIndexOf((".")) + 1);
        this.sAlias = MessageFormat.format("({0})", _sQueryString);
        this.sQueryString = _sQueryString;
        this.sPath = getFullPath();
    }

    public QueryString() {
        String sControl = this.getClass().toString();
        this.sAlias = "";
        this.sQueryString = "";
        this.sControlType = sControl.substring(sControl.lastIndexOf((".")) + 1);
        this.sPath = getFullPath();
    }

    /**
     * Method generates html string for a table
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
     * Method generates html string for a table from a map which contains object
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
    public Success GET(String sUrlParameters, Map<String, String> headers) {
        APIResponse rest = null;
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null && !sUrlParameters.isEmpty())
                sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (headers != null)
                sMessageAddon += "<h3> Request headers:</h3> <br>" + generateTableFromMap("Header", "Value", headers);

            suc.sMessageAddon = sMessageAddon;

            if (this.sAlias.isEmpty()) {
                throw new RuntimeException("Query String not declared correctly");
            }

            String sPath = APIReferences.currentPageContext + sQueryString;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            createPlaywright();
            setApiRequestContext(headers);
            rest = apiRequestContext.get(sPath);
            response = new RAFRestResponse(this, rest);

            return suc.finish(null);

        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    public Success POST(String sUrlParameters, Map<String, Object> body, Map<String, String> headers, contentType contentType) {
        APIResponse rest = null;
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

            if (this.sAlias.isEmpty()) {
                throw new RuntimeException("Query String not declared correctly");
            }

            String sPath = APIReferences.currentPageContext + sQueryString;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            createPlaywright();
            setApiRequestContext(headers);
            setApiRequestOptions(body,contentType);

            rest = apiRequestContext.post(sPath, requestOptions);
            response = new RAFRestResponse(this, rest);

            return suc.finish(null);

        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    /**
     * Method sends given data to update a resource.
     *
     * @param sUrlParameters Url parameters to be set.
     * @param body           Body to be set
     * @param headers        Headers to be set.
     * @return Success object.
     */
    public Success PUT(String sUrlParameters, Map<String, Object> body, Map<String, String> headers, contentType contentType) {
        APIResponse rest = null;
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

            if (this.sAlias.isEmpty()) {
                throw new RuntimeException("Query String not declared correctly");
            }

            String sPath = APIReferences.currentPageContext + sQueryString;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            createPlaywright();
            setApiRequestContext(headers);
            setApiRequestOptions(body,contentType);
            rest = apiRequestContext.put(sPath, requestOptions);
            response = new RAFRestResponse(this, rest);

            return suc.finish(null);

        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    //    /**
//     * Method deletes data from a resource.
//     *
//     * @param sUrlParameters Url parameters to be set.
//     * @param headers        Headers to be set.
//     * @param body           Body to be set.
//     * @param contentType    contentType to be set.
//     * @return Success object.
//     */
    public Success DELETE(String sUrlParameters, Map<String, Object> body, Map<String, String> headers, contentType contentType) {
        APIResponse rest = null;
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

            if (this.sAlias.isEmpty()) {
                throw new RuntimeException("Query String not declared correctly");
            }

            String sPath = APIReferences.currentPageContext + sQueryString;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            createPlaywright();
            setApiRequestContext(headers);
            setApiRequestOptions(body,contentType);
            rest = apiRequestContext.delete(sPath, requestOptions);
            response = new RAFRestResponse(this, rest);

            return suc.finish(null);

        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    /**
     * Method sends PATCH request to endpoint
     *
     * @param sUrlParameters Url parameters to be set.
     * @param headers        Headers to be set.
     * @param body           Body to be set.
     * @param contentType    contentType to be set.
     * @return Success object.
     */
    public Success PATCH(String sUrlParameters, Map<String, Object> body, Map<String, String> headers, contentType contentType) {
        APIResponse rest = null;
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

            if (this.sAlias.isEmpty()) {
                throw new RuntimeException("Query String not declared correctly");
            }

            String sPath = APIReferences.currentPageContext + sQueryString;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            createPlaywright();
            setApiRequestContext(headers);
            setApiRequestOptions(body,contentType);
            rest = apiRequestContext.patch(sPath, requestOptions);
            response = new RAFRestResponse(this, rest);

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

    private String getFullPath() {
        StackTraceElement mapClass = Thread.currentThread().getStackTrace()[4];
        String mapFolder = UIReferences.hlpr().cleanupPath(mapClass.getClassName());

        if (!mapClass.getMethodName().equals("<clinit>")) {
            mapFolder = mapFolder + "." + mapClass.getMethodName();
        }

        return mapFolder;
    }
}
