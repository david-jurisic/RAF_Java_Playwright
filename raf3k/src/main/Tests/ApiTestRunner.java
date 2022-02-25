import org.junit.jupiter.api.Test;
import org.raf3k.apitesting.basetypes.APITestCase;
import org.raf3k.apitesting.basetypes.HeadersConfig;
import org.raf3k.apitesting.basetypes.RAFRestResponse;

public class ApiTestRunner extends APITestCase {
    public ApiTestRunner() {
        super("Api test case","TC005","Filip Borić");
    }
    @Test
    void First() {
        Map.API.initialize();
        newStep(1, "Send request to 'https://api.agify.io/' endpoint.");
        success(Map.API.Get.put("?name=Tutuavfgaa&job=Developer",null, HeadersConfig.headers("Content-Type", "application/json")));
        RAFRestResponse resp = Map.API.Get.response;
        success(resp.verifyResponseCode(200));
    }
}
