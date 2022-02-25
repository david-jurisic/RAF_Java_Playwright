package org.raf3k.apitesting.basetypes;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.raf3k.apitesting.APIReferences;
import org.raf3k.shared.ControlObject;
import org.raf3k.shared.logging.Success;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.*;

public class QueryString extends ControlObject {
    private String sQueryString;
    public static RAFRestResponse response = null;

    public QueryString(String _sQueryString) {
        String sControl = this.getClass().toString();
        this.sControlType = sControl.substring(sControl.lastIndexOf((".")) + 1);
        this.sAlias = String.format("({0})", sQueryString);
        this.sQueryString = _sQueryString;
        if (this.sControlType.toLowerCase().substring(this.sControlType.length() - 2).contains("ex"))
            this.sPath = APIReferences.get_Hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());
        else
            this.sPath = APIReferences.get_Hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    public QueryString() {
    }

    public String generateTableFromDict(String sNameHeader, String sValueHeader, List<Object> dDictionary) {
        if (dDictionary == null) return "";
        //var trs = dDictionary.stream().map());
        //var tableContents = String.Concat(trs);
        //return String.format("<table><tr><th>{0}</th><th>{1}</th></tr>\" + 2 + \"</table>", sNameHeader,sValueHeader,tableContents);
        return "";
    }

    public boolean isDictionary(Object o) {
        if (o == null) return false;
        return o instanceof Dictionary<?, ?>;
        /**return o is IDictionary &&
         o.GetType().IsGenericType &&
         o.GetType().GetGenericTypeDefinition().IsAssignableFrom(typeof(Dictionary<,>));*/
    }

    /**
     * public static Dictionary<string, TValue> ToDictionary<TValue>(object obj)
     * {
     * var json = JsonConvert.SerializeObject(obj);
     * var dictionary = JsonConvert.DeserializeObject<Dictionary<string, TValue>>(json);
     * return dictionary;
     * }
     */

    public Success get(String sUrlParameters, Map<String, String> headers) {
        response = null;
        Response rest = null;
        RequestSpecification req = RestAssured.given();
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (headers != null) {
                //sMessageAddon += "<h3> Request headers:</h3> <br>" + GenerateTableFromDict("Header", "Value", headers);
            }

            suc.sMessageAddon = sMessageAddon;

            String sPath = APIReferences.currentPageContext;

            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            if (headers != null)
                req.headers(headers);

            rest = req.when().get(sPath);

            RAFRestResponse resp = new RAFRestResponse(this, rest);
            response = resp;
            return suc.finish(null);

        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    public Success post(String sUrlParameters, String sBody, Map<String, String> headers) {
        response = null;
        Response rest = null;
        RequestSpecification req = RestAssured.given();
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (sBody != null)
                if (!sBody.isEmpty())
                    sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (headers != null) {
                //sMessageAddon += "<h3> Request headers:</h3> <br>" + GenerateTableFromDict("Header", "Value", headers);
            }

            suc.sMessageAddon = sMessageAddon;

            String sPath = APIReferences.currentPageContext;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            if (headers != null)
                req.headers(headers);

            if (sBody != null)
                req.body(sBody);

            rest = req.when().post(sPath);
            RAFRestResponse resp = new RAFRestResponse(this, rest);
            response = resp;
            return suc.finish(null);
        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    public Success put(String sUrlParameters, String sBody, Map<String, String> headers) {
        response = null;
        Response rest = null;
        RequestSpecification req = RestAssured.given();
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (sBody != null)
                if (!sBody.isEmpty())
                    sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (headers != null) {
                //sMessageAddon += "<h3> Request headers:</h3> <br>" + GenerateTableFromDict("Header", "Value", headers);
            }

            suc.sMessageAddon = sMessageAddon;

            String sPath = APIReferences.currentPageContext;
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sPath = sPath + sUrlParameters;

            if (headers != null)
                req.headers(headers);

            if (sBody != null)
                req.body(sBody);

            rest = req.when().put(sPath);
            RAFRestResponse resp = new RAFRestResponse(this, rest);
            response = resp;
            return suc.finish(null);
        } catch (Exception ex) {
            RAFRestResponse resp = new RAFRestResponse(ex);
            return suc.finish(ex);
        }
    }

    public Success delete(String sUrlParameters, Map<String, String> headers) {
        response = null;
        Response rest = null;
        RequestSpecification req = RestAssured.given();
        Success suc = new Success(this);
        try {
            String sMessageAddon = "";
            if (sUrlParameters != null)
                if (!sUrlParameters.isEmpty())
                    sMessageAddon += "<h3>URL Parameters:</h3> <br><p>" + sUrlParameters + "</p><br>";

            if (headers != null) {
                //sMessageAddon += "<h3> Request headers:</h3> <br>" + GenerateTableFromDict("Header", "Value", headers);
            }

            suc.sMessageAddon = sMessageAddon;

            String sPath = APIReferences.currentPageContext;
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
}
