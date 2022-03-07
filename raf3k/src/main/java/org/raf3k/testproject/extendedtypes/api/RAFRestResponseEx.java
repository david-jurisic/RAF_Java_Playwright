package org.raf3k.testproject.extendedtypes.api;

import io.restassured.response.Response;
import org.raf3k.apitesting.basetypes.QueryString;
import org.raf3k.apitesting.basetypes.RAFRestResponse;

public class RAFRestResponseEx extends RAFRestResponse {
    public RAFRestResponseEx(QueryString query, Response resp) {
        super(query, resp);
    }

    public RAFRestResponseEx(RAFRestResponse rafRestResponse){
        super();
        response = rafRestResponse.response;
        sAlias = rafRestResponse.sAlias;
        sPath = rafRestResponse.sAlias;
        queryStringBase = rafRestResponse.queryStringBase;
    }
}
