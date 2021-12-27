import base.UI.BaseUtil;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        plugin = {"pretty",
                    "json:target/cucumber_json_reports/report.json"},
        glue = {"hook.UI","glue.UI"})

public class RunAeviAdminTests extends BaseUtil {

    @AfterClass
    public static void generateReport() {
        File reportOutputDirectory = new File("target/cucumber_full_html_reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber_json_reports/report.json");

        String buildNumber = "1";
        String projectName = "AeviAdminTest";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);

        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Branch", "release/1.0");
        configuration.setSortingMethod(SortingMethod.NATURAL);
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
        configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
        configuration.setQualifier("sample", "Chrome 80, mobile");
        configuration.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
