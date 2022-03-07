import org.junit.jupiter.api.Test;
import org.raf3k.apitesting.basetypes.APITestCase;
import org.raf3k.apitesting.basetypes.RAFRestResponse;

public class ApiTestRunner extends APITestCase {
    public ApiTestRunner() {
        super("Api test case","TC005","Filip Borić");
    }
    @Test
    void First() {
        Map.initializeAPI();
        newStep(1, "Send request to 'https://swapi.dev/api/' endpoint.");
        success(Map.API.Get.get("/1", null));
        RAFRestResponse resp = Map.API.Get.response;
        success(resp.verifyResponseCode(200));
        success(resp.verifyValue("id", 1, true));
    }
}
