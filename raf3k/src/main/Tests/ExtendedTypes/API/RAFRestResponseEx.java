package ExtendedTypes.API;


import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Response;
import org.raf3k.apitesting.basetypes.QueryString;
import org.raf3k.apitesting.basetypes.RAFRestResponse;

public class RAFRestResponseEx extends RAFRestResponse {
    public RAFRestResponseEx(QueryString query, APIResponse resp) {
        super(query, resp);
    }

    public RAFRestResponseEx(RAFRestResponse rafRestResponse) {
        super();
        response = rafRestResponse.response;
        sAlias = rafRestResponse.sAlias;
        sPath = rafRestResponse.sPath;
        queryStringBase = rafRestResponse.queryStringBase;
    }
}
