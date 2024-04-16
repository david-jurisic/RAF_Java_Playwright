package Maps.UI;

import ExtendedTypes.API.QueryStringEx;
import org.raf3k.apitesting.APIReferences;

public class XrayMap {
    public static void initialize() {
        APIReferences.currentPageContext = "https://xray.cloud.getxray.app/";
    }

    public static String executionImport = "api/v2/import/execution";

    public static QueryStringEx authenticate() {
        return new QueryStringEx("api/v2/authenticate");
    }

    public static  QueryStringEx importExecution() {
        return new QueryStringEx("api/v2/import/execution");
    }

    public static QueryStringEx importFeature() {
        return new QueryStringEx("api/v1/import/feature?projectKey=TS&projectId=10003&source=TestProject");
    }
}
