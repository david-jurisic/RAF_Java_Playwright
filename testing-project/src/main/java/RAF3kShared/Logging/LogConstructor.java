package RAF3kShared.Logging;

import RAF3kShared.DebugLog;
import RAF3kShared.SharedVariables;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class LogConstructor {
    private static String defaultLogTemplate = "LogTemplate.html";
    private static Path sLogPath;

    public static void GenerateLog(TestCaseBase TestCase) {
        String sLogType = SharedVariables.Configuration.getProperty("logExportType");
        switch (sLogType) {
            case "html":
                GenerateHTMLLog(TestCase);
                break;
            case "json":
                //GenerateJsonLog(TestCase);
                break;
            default:
                GenerateHTMLLog(TestCase);
                break;
        }

        //AttachLogFileToRun(sLogPath);
    }

    private static void GenerateHTMLLog(TestCaseBase TestCase) {
        String sRowColor = "success";
        String sLogData = "";
        for (Step step : TestCase.Steps) {
            if (step.bSuccess()) sRowColor = "success";
            else sRowColor = "danger";
            String sTableData = "<tbody class='labels'>";
            sTableData += "<tr class='" + sRowColor + "'><td colspan='2'><label for='" + step.StepNumber + "'>" + step.StepNumber + "  " +
                    step.StepName + "</label><input type='checkbox' name='" + step.StepNumber + " ' id='" + step.StepNumber +
                    "' data-toggle='toggle'></td><td>" + String.valueOf((float) step.Durations().toMillis() / 1000) + "</td><td></td></tr>";
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
                                    SubStep.Ex.getMessage() + "</b></td>";
                        else
                            sTableData += "<td onclick='ExpandMesageAddon(" + String.valueOf(step.StepNumber) + String.valueOf(i) + ")'>" +
                                    SubStep.Name + " <span id='Span" + String.valueOf(step.StepNumber) + String.valueOf(i) +
                                    "' class='arrowMoreInfo'><b>+</b></span> <br><b>" + "Unknown error occured" + "</b></td>";
                    }

                }
                sTableData += "<td>" + String.valueOf((float) Duration.between(SubStep.Start, SubStep.Finish).toMillis() / 1000) + "</td>";
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

        String logTemplateFilePath = SharedVariables.Configuration.getProperty("logTemplateFilePath");
        if (logTemplateFilePath == null || logTemplateFilePath.isEmpty()) {
            sHtmlLog = GetLogTemplateFromAssembly(defaultLogTemplate);
        } else {
            try {
                ClassLoader classLoader = ClassLoader.getSystemClassLoader();
                try (InputStream is = classLoader.getResourceAsStream(logTemplateFilePath)) {
                    if (is == null)
                        sHtmlLog = null;
                    try (InputStreamReader isr = new InputStreamReader(is);
                         BufferedReader reader = new BufferedReader(isr)) {
                        sHtmlLog = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                    }
                }

                if (sHtmlLog == null || sHtmlLog.isEmpty()) {
                    DebugLog.Add("File '" + SharedVariables.Configuration.getProperty("LogTemplateFilePath") + "' is empty. Default log template is used instead. ", 1);
                    sHtmlLog = GetLogTemplateFromAssembly(defaultLogTemplate);
                }
            } catch (Exception e) {
                DebugLog.Add("File '" + SharedVariables.Configuration.getProperty("LogTemplateFilePath") + "' does not exist or can't be opened." +
                        " Default log template is used instead." +
                        "\n Detailed error message: " + e.getMessage(), 1);
                sHtmlLog = GetLogTemplateFromAssembly(defaultLogTemplate);
            }
        }

        String sExport = sHtmlLog.replace("[TableData]", sLogData);
        sExport = sExport.replace("[TestCaseCode]", " " + TestCase.sTestCaseCode);
        sExport = sExport.replace("[TestCaseName]", " " + TestCase.sTestCaseName);
        sExport = sExport.replace("[TestCaseDuration]", String.valueOf(
                (float) TestCase.Steps.stream().filter(m -> m.Durations().getSeconds() > 0).mapToLong(n -> n.Durations().toMillis()).sum() / 1000) + " sec");
        sExport = sExport.replace("[TestCaseAuthor]", TestCase.sTestCaseAuthor);

        Path sFolderPath = Path.of(SharedVariables.Configuration.getProperty("logFilePath"), TestCase.sTestCaseCode);

        if (!Files.exists(sFolderPath)) {
            try {
                Files.createDirectory(sFolderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyhhss");
        sLogPath = Path.of(sFolderPath.toString(), LocalDateTime.now().format(formatter) + ".html");

        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(sLogPath.toString());
            myWriter.write(sExport);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String GetLogTemplateFromAssembly(String fileName) {
        StringBuilder resultStringBuilder = new StringBuilder();

        try (InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line;
            while ((line = reader.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (Exception ex) {
            DebugLog.Add(ex);
        }

        return resultStringBuilder.toString();
    }
}
