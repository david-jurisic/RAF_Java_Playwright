import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        strict = false,
        plugin = {"pretty",
                    "json:target/cucumber_json_reports/report.json",
                    "html:target/cucumber_html_reports/"},
        glue = {"hook.UI","glue.UI"},
        monochrome = false)
public class RunAeviAdminTests {
}
