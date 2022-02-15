package RAF3kShared.Logging;

import RAF3kShared.SharedVariables;

import java.time.Duration;

public class LogConstructor {
    private static String defaultLogTemplate = "RAF3kShared.Logging.LogTemplate.html";
    private static String sLogPath;

    public static void GenerateLog(TestCaseBase TestCase) {
        String sLogType = SharedVariables.Configuration.GetEntryValue("LogExportType");
        switch (sLogType) {
            case "html":
                GenerateHTMLLog(TestCase);
                break;
            case "json":
                GenerateJsonLog(TestCase);
                break;
            default:
                GenerateHTMLLog(TestCase);
                break;
        }

        AttachLogFileToRun(sLogPath);
    }

    public static void GenerateHTMLLog(TestCaseBase TestCase) {
        String sRowColor = "success";
        String sLogData = "";
        for (Step step : TestCase.Steps) {
            if (step.bSuccess()) sRowColor = "success";
            else sRowColor = "danger";
            String sTableData = "<tbody class='labels'>";
            sTableData += "<tr class='" + sRowColor + "'><td colspan='2'><label for='" + step.StepNumber + "'>" + step.StepNumber + "  " +
                    step.StepName + "</label><input type='checkbox' name='" + step.StepNumber + " ' id='" + step.StepNumber +
                    "' data-toggle='toggle'></td><td>" + String.valueOf(step.Durations().getSeconds()) + "</td><td></td></tr>";
            sTableData += "</tbody><tbody class='hide'>";
            int i = 1;
            for (Substep SubStep : step.Substeps) {
                sTableData += "<tr class='results'>";
                sTableData += "<td>" + step.StepNumber + "." + i + "</td>";
                if (SubStep.Passed) {
                    if (SubStep.MessageAddon == null || SubStep.MessageAddon.isEmpty())
                        sTableData += "<td>" + SubStep.Name + "</td>";
                    else
                        sTableData += "<td onclick='ExpandMesageAddon(" + String.valueOf(step.StepNumber) + String.valueOf(i) + ")'>{SubStep.Name} <span id='Span" +
                                String.valueOf(step.StepNumber) + String.valueOf(i) + "' class='arrowMoreInfo'><b>+</b></span></td>";

                } else {
                    if (SubStep.MessageAddon == null || SubStep.MessageAddon.isEmpty()) {
                        //HTMLencode removed
                        if (SubStep.Ex != null)
                            sTableData += "<td>" + SubStep.Name + " <br><b>" + SubStep.Ex.getMessage() + "</b></td>";
                        else
                            sTableData += "<td>" + SubStep.Name + " <br><b>" + "Unknown error occured" + "</b></td>";
                    } else {
                        if (SubStep.Ex != null)
                            sTableData += "<td onclick='ExpandMesageAddon(" + String.valueOf(step.StepNumber) + String.valueOf(i) + ")'>" + SubStep.Name +
                                    " <span id='Span" + String.valueOf(step.StepNumber) + String.valueOf(i) + "' class='arrowMoreInfo'><b>+</b></span><br><b>" +
                                    SubStep.Ex.Message + "</b></td>";
                        else
                            sTableData += "<td onclick='ExpandMesageAddon(" + String.valueOf(step.StepNumber) + String.valueOf(i) + ")'>" +
                                    SubStep.Name + " <span id='Span" + String.valueOf(step.StepNumber) + String.valueOf(i) +
                                    "' class='arrowMoreInfo'><b>+</b></span> <br><b>" + "Unknown error occured" + "</b></td>";
                    }

                }
                sTableData += "<td>" + Duration.between(SubStep.Finish, SubStep.Start).getSeconds() + "</td>";
                if (SubStep.Passed)
                    sTableData += "<td></td>";
                else if (SubStep.Screenshot != null && !SubStep.Screenshot.isEmpty())
                    sTableData += "<td><button data-image='" + SubStep.Screenshot + "' onclick='DisplayScreenshot()'>...</button></td>";
                else
                    sTableData += "<td>N/A</td>";
                sTableData += "</tr>";
                if (SubStep.MessageAddon != null && !SubStep.MessageAddon.isEmpty()) {
                    sTableData += "<tr id='" + String.valueOf(step.StepNumber) + String.valueOf(i) +
                            "' style=\"display: none;\"><td colspan='4'>" + "SubStep.MessageAddon" + "</td></tr>";
                }
                i++;
            }
            sTableData += "</tbody>";
            sLogData += sTableData;
        }

        String sHtmlLog = "";
        if (String.IsNullOrEmpty(SharedVariables.Configuration.GetEntryValue("LogTemplateFilePath"))) {
            sHtmlLog = GetLogTemplateFromAssembly(defaultLogTemplate);
        } else {
            try {
                sHtmlLog = File.ReadAllText(Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), SharedVariables.Configuration.GetEntryValue("LogTemplateFilePath")));

                if (string.IsNullOrEmpty(sHtmlLog)) {
                    DebugLog.Add($"File '{SharedVariables.Configuration.GetEntryValue("LogTemplateFilePath")}' is empty. Default log template is used instead. ", 1);
                    sHtmlLog = GetLogTemplateFromAssembly(defaultLogTemplate);
                }

            } catch (Exception e) {
                DebugLog.Add($"File '{SharedVariables.Configuration.GetEntryValue("LogTemplateFilePath")}' does not exist or can't be opened." +
                        " Default log template is used instead." +
                        "\n Detailed error message: " + e.StackTrace, 1);
                sHtmlLog = GetLogTemplateFromAssembly(defaultLogTemplate);
            }
        }

        string sExport = sHtmlLog.Replace("[TableData]", sLogData);
        sExport = sExport.Replace("[TestCaseCode]", $" {TestCase.sTestCaseCode}");
        sExport = sExport.Replace("[TestCaseName]", $" {TestCase.sTestCaseName}");
        sExport = sExport.Replace("[TestCaseDuration]", $"{TimeSpan.FromSeconds(TestCase.Steps.Sum(s => s.Duration.TotalSeconds)).TotalSeconds.ToString("
        0.##")} sec");
        sExport = sExport.Replace("[TestCaseAuthor]", $"{TestCase.sTestCaseAuthor}");

        string sFolderPath = Path.Combine(SharedVariables.Configuration.GetEntryValue("LogFilePath"), TestCase.sTestCaseCode);

        if (!Directory.Exists(sFolderPath)) {
            Directory.CreateDirectory(sFolderPath);
        }

        sLogPath = Path.Combine(sFolderPath, DateTime.Now.ToString("ddMMyyyhhss") + ".html");
        File.WriteAllText(sLogPath, sExport);
    }
}
