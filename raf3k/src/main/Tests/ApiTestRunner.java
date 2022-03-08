import org.junit.jupiter.api.Test;
import org.raf3k.apitesting.basetypes.APITestCase;

import static java.util.Map.entry;

import org.raf3k.testproject.extendedtypes.api.RAFRestResponseEx;

public class ApiTestRunner extends APITestCase {
    public ApiTestRunner() {
        super("Api test case", "TC005", "Filip Borić");
    }

    @Test
    void First() {
        Map.initializeAPI();
        newStep(1, "Send request to 'https://swapi.dev/api/' endpoint.");
        success(Map.API.Get.get("/1", null));
        RAFRestResponseEx resp = new RAFRestResponseEx(Map.API.Get.response);
        success(resp.verifyResponseCode(200));
        success(resp.verifyValue("id", 1, true));
        success(resp.verifyContains("species.name", "bulba", true));
        success(resp.verifyContains("species.name", "woof", false));
        success(resp.verifyValue("abilities[0].is_hidden", false, true));
        success(resp.verifyValue("id", 2, false));
        success(resp.verifyEmpty("height", false));
        success(resp.verifyEmpty("sprites.back_female", true));
        success(resp.verifyArrayEmpty("past_types", true));
        success(resp.verifyArrayContains("abilities", java.util.Map.ofEntries(
                entry("ability", java.util.Map.ofEntries(
                        entry("name", "overgrow"),
                        entry("url", "https://pokeapi.co/api/v2/ability/65/")
                )),
                entry("is_hidden", false),
                entry("slot", 1)
        ), true));
        success(resp.verifyArrayContains("abilities", java.util.Map.ofEntries(
                entry("ability", java.util.Map.ofEntries(
                        entry("name", "overgrowe"),
                        entry("url", "https://pokeapi.co/api/v2/ability/65/")
                )),
                entry("is_hidden", true),
                entry("slot", 1)
        ), false));
    }
}
