package org.raf3k.shared.logging;

import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;

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

    public static void generateLog(TestCaseBase TestCase) {
        String sLogType = SharedVariables.configuration.getProperty("logExportType");
        switch (sLogType) {
            case "html":
                generateHTMLLog(TestCase);
                break;
            case "json":
                //generateJsonLog(TestCase);
                break;
            default:
                generateHTMLLog(TestCase);
                break;
        }

        //AttachLogFileToRun(sLogPath);
    }

    private static void generateHTMLLog(TestCaseBase testCase) {
        String sRowColor = "success";
        String sLogData = "";
        for (Step step : testCase.steps) {
            if (step.bSuccess()) {
                sRowColor = "success";
            } else {
                sRowColor = "danger";
            }
            String sTableData = "<tbody class='labels'>";
            sTableData += "<tr class='" + sRowColor + "'><td colspan='2'><label for='" + step.stepNumber + "'>" + step.stepNumber + "  " +
                    step.stepName + "</label><input type='checkbox' name='" + step.stepNumber + " ' id='" + step.stepNumber +
                    "' data-toggle='toggle'></td><td>" + String.valueOf((float) step.durations().toMillis() / 1000) + "</td><td></td></tr>";
            sTableData += "</tbody><tbody class='hide'>";
            int i = 1;
            for (Substep SubStep : step.substeps) {
                sTableData += "<tr class='results'>";
                sTableData += "<td>" + step.stepNumber + "." + i + "</td>";
                if (SubStep.passed) {
                    if (SubStep.messageAddon == null || SubStep.messageAddon.isEmpty())
                        sTableData += "<td>" + SubStep.name + "</td>";
                    else
                        sTableData += "<td onclick='ExpandMesageAddon(" + String.valueOf(step.stepNumber) + String.valueOf(i) + ")'>{SubStep.Name} <span id='Span" +
                                String.valueOf(step.stepNumber) + String.valueOf(i) + "' class='arrowMoreInfo'><b>+</b></span></td>";

                } else {
                    if (SubStep.messageAddon == null || SubStep.messageAddon.isEmpty()) {
                        //HTMLencode removed
                        if (SubStep.ex != null)
                            sTableData += "<td>" + SubStep.name + " <br><b>" + SubStep.ex.getMessage() + "</b></td>";
                        else
                            sTableData += "<td>" + SubStep.name + " <br><b>" + "Unknown error occured" + "</b></td>";
                    } else {
                        if (SubStep.ex != null)
                            sTableData += "<td onclick='ExpandMesageAddon(" + String.valueOf(step.stepNumber) + String.valueOf(i) + ")'>" + SubStep.name +
                                    " <span id='Span" + String.valueOf(step.stepNumber) + String.valueOf(i) + "' class='arrowMoreInfo'><b>+</b></span><br><b>" +
                                    SubStep.ex.getMessage() + "</b></td>";
                        else
                            sTableData += "<td onclick='ExpandMesageAddon(" + String.valueOf(step.stepNumber) + String.valueOf(i) + ")'>" +
                                    SubStep.name + " <span id='Span" + String.valueOf(step.stepNumber) + String.valueOf(i) +
                                    "' class='arrowMoreInfo'><b>+</b></span> <br><b>" + "Unknown error occured" + "</b></td>";
                    }

                }
                sTableData += "<td>" + String.valueOf((float) Duration.between(SubStep.start, SubStep.finish).toMillis() / 1000) + "</td>";
                if (SubStep.passed)
                    sTableData += "<td></td>";
                else if (SubStep.screenshot != null && !SubStep.screenshot.isEmpty())
                    sTableData += "<td><button data-image='" + SubStep.screenshot + "' onclick='DisplayScreenshot()'>...</button></td>";
                else
                    sTableData += "<td>N/A</td>";
                sTableData += "</tr>";
                if (SubStep.messageAddon != null && !SubStep.messageAddon.isEmpty()) {
                    sTableData += "<tr id='" + String.valueOf(step.stepNumber) + String.valueOf(i) +
                            "' style=\"display: none;\"><td colspan='4'>" + "SubStep.MessageAddon" + "</td></tr>";
                }
                i++;
            }
            sTableData += "</tbody>";
            sLogData += sTableData;
        }

        String sHtmlLog = "";

        String logTemplateFilePath = SharedVariables.configuration.getProperty("logTemplateFilePath");
        if (logTemplateFilePath == null || logTemplateFilePath.isEmpty()) {
            sHtmlLog = getLogTemplateFromAssembly(defaultLogTemplate);
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
                    DebugLog.add("File '" + SharedVariables.configuration.getProperty("logTemplateFilePath") + "' is empty. Default log template is used instead. ", 1);
                    sHtmlLog = getLogTemplateFromAssembly(defaultLogTemplate);
                }
            } catch (Exception e) {
                DebugLog.add("File '" + SharedVariables.configuration.getProperty("LogTemplateFilePath") + "' does not exist or can't be opened." +
                        " Default log template is used instead." +
                        "\n Detailed error message: " + e.getMessage(), 1);
                sHtmlLog = getLogTemplateFromAssembly(defaultLogTemplate);
            }
        }

        String sExport = sHtmlLog.replace("[TableData]", sLogData);
        sExport = sExport.replace("[TestCaseCode]", " " + testCase.sTestCaseCode);
        sExport = sExport.replace("[TestCaseName]", " " + testCase.sTestCaseName);
        sExport = sExport.replace("[TestCaseDuration]", String.valueOf(
                (float) testCase.steps.stream().filter(m -> m.durations().getSeconds() > 0).mapToLong(n -> n.durations().toMillis()).sum() / 1000) + " sec");
        sExport = sExport.replace("[TestCaseAuthor]", testCase.sTestCaseAuthor);

        Path sFolderPath = Path.of(SharedVariables.configuration.getProperty("logFilePath"), testCase.sTestCaseCode);

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

    private static String getLogTemplateFromAssembly(String fileName) {
        StringBuilder resultStringBuilder = new StringBuilder();

        try (InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line;
            while ((line = reader.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (Exception ex) {
            DebugLog.add(ex);
        }

        return resultStringBuilder.toString();
    }
}
