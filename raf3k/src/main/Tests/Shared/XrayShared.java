package Shared;

import ExtendedTypes.API.QueryStringEx;
import ExtendedTypes.API.RAFRestResponseEx;
import Hooks.HTInfonovaHooks;
import Maps.UI.XrayMap;
import Settings.GlobalParameters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.raf3k.apitesting.basetypes.APITestCase;
import org.raf3k.apitesting.basetypes.QueryString;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.logging.Step;
import org.raf3k.shared.logging.Substep;
import org.raf3k.shared.logging.TestCaseBase;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.util.Map.entry;

public class XrayShared {

    static RAFRestResponseEx rafRestResponseEx;
    public static Path sLogPath;
    private static String defaultLogTemplate = "Shared/HTInfonovaLogTemplate.html";

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TestCaseInfo {
        @JsonProperty("testCaseCode")
        private String testCaseCode;
        @JsonProperty("testKey")
        private String testKey;

        public String getTestCaseCode() {
            return testCaseCode;
        }

        public String getTestKey() {
            return testKey;
        }
    }

    public static String getBearerToken() {
        XrayMap.initialize();
        String bearerToken="";

        Map<String, String> headers = new HashMap<>();
        Map<String, Object> body = Map.ofEntries(
                entry("client_id", Authorization.ClientId),
                entry("client_secret", Authorization.ClientSecret));

        QueryStringEx queryStringEx = XrayMap.authenticate();
//        GlobalParameters.testCaseBase.success(queryStringEx.POST("", body, headers, QueryString.contentType.json));
//
//        rafRestResponseEx = new RAFRestResponseEx(queryStringEx.response);
//        bearerToken = rafRestResponseEx.response.getBody().asString();

        return bearerToken;
    }

    private static String getLogTemplateFromAssembly(String fileName) {
        StringBuilder resultStringBuilder = new StringBuilder();

        try {
            InputStream in = ClassLoader.getSystemResourceAsStream(fileName);

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        resultStringBuilder.append(line).append("\n");
                    }
                } catch (Throwable var8) {
                    try {
                        reader.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }

                    throw var8;
                }

                reader.close();
            } catch (Throwable var9) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (in != null) {
                in.close();
            }
        } catch (Exception var10) {
            DebugLog.add(var10);
        }

        return resultStringBuilder.toString();
    }

    public static String generateHTMLLog(TestCaseBase testCase) {
        String sLogData = "";

        String sTableData;
        for (Iterator var3 = testCase.steps.iterator(); var3.hasNext(); sLogData = sLogData + sTableData) {
            Step step = (Step) var3.next();
            String sRowColor;
            if (step.bSuccess()) {
                sRowColor = "success";
            } else {
                sRowColor = "danger";
            }

            sTableData = "<tbody class='labels'>";
            sTableData = sTableData + "<tr class='" + sRowColor + "'><td colspan='2'><label for='" + step.stepNumber + "'>" + step.stepNumber + "  " + step.stepName + "</label><input type='checkbox' name='" + step.stepNumber + " ' id='" + step.stepNumber + "' data-toggle='toggle'></td><td>" + String.format("%.2f", (float) step.durations().toMillis() / 1000.0F) + "</td>";
            if (testCase.getClass().getName() != APITestCase.class.getName()) {
                sTableData = sTableData + "<td></td></tr>";
            } else {
                sTableData = sTableData + "</tr>";
            }

            sTableData = sTableData + "</tbody><tbody class='hide'>";
            int i = 1;

            for (Iterator var7 = step.substeps.iterator(); var7.hasNext(); ++i) {
                Substep SubStep = (Substep) var7.next();
                sTableData = sTableData + "<tr class='results'>";
                sTableData = sTableData + "<td>" + step.stepNumber + "." + i + "</td>";
                if (SubStep.passed) {
                    if (SubStep.messageAddon != null && !SubStep.messageAddon.isEmpty()) {
                        sTableData = sTableData + "<td onclick='ExpandMesageAddon(" + step.stepNumber + i + ")'>" + SubStep.name + "<span id='Span" + step.stepNumber + i + "' class='arrowMoreInfo'><b>+</b></span></td>";
                    } else {
                        sTableData = sTableData + "<td>" + SubStep.name + "</td>";
                    }
                } else if (SubStep.messageAddon != null && !SubStep.messageAddon.isEmpty()) {
                    if (SubStep.ex != null && SubStep.ex.getCause() != null) {
                        sTableData = sTableData + "<td onclick='ExpandMesageAddon(" + step.stepNumber + i + ")'>" + SubStep.name + " <span id='Span" + step.stepNumber + i + "' class='arrowMoreInfo'><b>+</b></span><br><b>" + SubStep.ex.getCause().getMessage() + "</b></td>";
                    } else if (SubStep.ex != null && SubStep.ex.getCause() == null) {
                        sTableData = sTableData + "<td onclick='ExpandMesageAddon(" + step.stepNumber + i + ")'>" + SubStep.name + " <span id='Span" + step.stepNumber + i + "' class='arrowMoreInfo'><b>+</b></span><br><b>" + SubStep.ex.getMessage() + "</b></td>";
                    } else {
                        sTableData = sTableData + "<td onclick='ExpandMesageAddon(" + step.stepNumber + i + ")'>" + SubStep.name + " <span id='Span" + step.stepNumber + i + "' class='arrowMoreInfo'><b>+</b></span> <br><b>Unknown error occured</b></td>";
                    }
                } else if (SubStep.ex != null) {
                    sTableData = sTableData + "<td>" + SubStep.name + " <br><b>" + SubStep.ex.getMessage() + "</b></td>";
                } else {
                    sTableData = sTableData + "<td>" + SubStep.name + " <br><b>Unknown error occured</b></td>";
                }

                sTableData = sTableData + "<td>";
                if (SubStep.start != null && SubStep.finish != null) {
                    sTableData = sTableData + String.format("%.2f", (float) Duration.between(SubStep.start, SubStep.finish).toMillis() / 1000.0F);
                } else {
                    sTableData = sTableData + "N/A";
                }

                sTableData = sTableData + "</td>";
                if (SubStep.passed && !APITestCase.class.equals(testCase.getClass())) {
                    sTableData = sTableData + "<td></td>";
                } else if (SubStep.screenshot != null && !SubStep.screenshot.isEmpty() && !APITestCase.class.equals(testCase.getClass())) {
                    sTableData = sTableData + "<td><button data-image='" + SubStep.screenshot + "' onclick='DisplayScreenshot()'>...</button></td>";
                } else if (!APITestCase.class.equals(testCase.getClass())) {
                    sTableData = sTableData + "<td>N/A</td>";
                }

                sTableData = sTableData + "</tr>";
                if (SubStep.messageAddon != null && !SubStep.messageAddon.isEmpty()) {
                    sTableData = sTableData + "<tr id='" + step.stepNumber + i + "' style=\"display: none;\"><td colspan='4'>" + SubStep.messageAddon + "</td></tr>";
                }
            }

            sTableData = sTableData + "</tbody>";
        }

        String logTemplateFilePath = SharedVariables.configuration.getProperty("logTemplateFilePath");
        String sHtmlLog;
        if (logTemplateFilePath != null && !logTemplateFilePath.isEmpty()) {
            try {
                ClassLoader classLoader = ClassLoader.getSystemClassLoader();
                InputStream is = classLoader.getResourceAsStream(logTemplateFilePath);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);
                sHtmlLog = (String) reader.lines().collect(Collectors.joining(System.lineSeparator()));
                if (sHtmlLog == null || sHtmlLog.isEmpty()) {
                    DebugLog.add("File '" + SharedVariables.configuration.getProperty("logTemplateFilePath") + "' is empty. Default log template is used instead. ", 1);
                    sHtmlLog = getLogTemplateFromAssembly(defaultLogTemplate);
                }
            } catch (Exception var13) {
                DebugLog.add("File '" + SharedVariables.configuration.getProperty("LogTemplateFilePath") + "' does not exist or can't be opened. Default log template is used instead.\n Detailed error message: " + var13.getMessage(), 1);
                sHtmlLog = getLogTemplateFromAssembly(defaultLogTemplate);
            }
        } else {
            sHtmlLog = getLogTemplateFromAssembly(defaultLogTemplate);
        }

        sTableData = sHtmlLog.replace("[TableData]", sLogData);
        String var10002 = testCase.sTestCaseCode;
        sTableData = sTableData.replace("[TestCaseCode]", " " + var10002);
        var10002 = testCase.sTestCaseName;
        sTableData = sTableData.replace("[TestCaseName]", " " + var10002);
        var10002 = String.format("%.2f", (float) testCase.steps.stream().filter((m) -> {
            return m.durations().toMillis() > 0L;
        }).mapToLong((n) -> {
            return n.durations().toMillis();
        }).sum() / 1000.0F);
        sTableData = sTableData.replace("[TestCaseDuration]", var10002 + " sec");
        sTableData = sTableData.replace("[TestCaseAuthor]", testCase.sTestCaseAuthor);
        if (HTInfonovaHooks.DisplayedNames.isEmpty()) {
            sTableData = sTableData.replace("[TestCaseSubAccount]", HTInfonovaHooks.DisplayedName);
        } else {
            HTInfonovaHooks.DisplayedName = HTInfonovaHooks.DisplayedNames.toString();
            sTableData = sTableData.replace("[TestCaseSubAccount]", HTInfonovaHooks.DisplayedName.substring(1, HTInfonovaHooks.DisplayedName.length() - 1));
        }
        var10002 = testCase.bPass ? "success" : "danger";
        sTableData = sTableData.replace("[TestResult]", "<p class='moveRight " + var10002 + "'>Test: " + (testCase.bPass ? "Passed" : "Failed") + "</p>");
        if (testCase.getClass().getName() != APITestCase.class.getName()) {
            sTableData = sTableData.replace("[scrnTableHead]", "<th>Scrn</th>");
        } else {
            sTableData = sTableData.replace("[scrnTableHead]", "");
        }

        Path sFolderPath = Path.of(SharedVariables.configuration.getProperty("logFilePath"), testCase.sTestCaseCode);
        if (!Files.exists(sFolderPath, new LinkOption[0])) {
            try {
                Files.createDirectory(sFolderPath);
            } catch (IOException var12) {
                var12.printStackTrace();
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyHHmmss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        sLogPath = Path.of(sFolderPath.toString(), currentDateTime);

        try {
            FileWriter myWriter = new FileWriter(sLogPath + ".html");
            myWriter.write(sTableData);
            myWriter.close();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        File testVideoFile = new File(Path.of(sFolderPath.toString(), "video.tmp").toString());
        if (testVideoFile.exists()) {
            if (testCase.bPass) {
                testVideoFile.delete();
            } else {
                testVideoFile.renameTo(new File(sLogPath.toString() + ".avi"));
            }
        }
        return sTableData;
    }

    private static List<String> encodeToBase64(List<String> logs) {
        List<String> encodedLogs = new ArrayList<>();
        for (String log : logs) {
            byte[] encodedBytes = Base64.getEncoder().encode(log.getBytes());
            encodedLogs.add(new String(encodedBytes));
        }
        return encodedLogs;
    }

    public static void importExecutionResults(List<TestCaseBase> testCases) {
        String bearerToken = getBearerToken();
        XrayMap.initialize();

        ObjectMapper objectMapper = new ObjectMapper();
        String userDir = System.getProperty("user.dir");
        String relativePath = ".." + File.separator + "xrayBinding.json";
        try {
            List<TestCaseInfo> testCaseInfoList = objectMapper.readValue(
                    new File(userDir, relativePath),
                    new TypeReference<List<TestCaseInfo>>() {
                    }
            );

            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + bearerToken.replace("\"", ""));
            headers.put("Content-Type", "application/json");
            List<Map<String, Object>> testsToImport = new ArrayList<>();

            for (int i = 0; i < testCases.size(); i++) {
                TestCaseBase testCase = testCases.get(i);

                String logData = XrayShared.generateHTMLLog(testCase);
                List<String> allLogs = Collections.singletonList(logData);

                List<String> testCaseEncodedLogs = encodeToBase64(allLogs);

                String logFileName = sLogPath.toString();
                logFileName = logFileName.substring(logFileName.length() - 14) + ".html";

                Map<String, String> evidenceMap = Map.of(
                        "data", testCaseEncodedLogs.get(0),
                        "filename", logFileName,
                        "contentType", "html"
                );

                for (TestCaseInfo testCaseInfo : testCaseInfoList) {
                    if (testCaseInfo.getTestCaseCode().equals(testCase.sTestCaseCode)) {

                        String status = testCase.bPass ? "Passed" : "Failed";

                        Map<String, Object> testMap = Map.of(
                                "testKey", testCaseInfo.getTestKey(),
                                "status", status,
                                "evidence", List.of(evidenceMap)
                        );
                        testsToImport.add(testMap);
                    }
                }
            }

            Map<String, Object> body = Map.of(
                    "info", Map.of(
                            "summary", "Execution of automated cucumber tests",
                            "description", "This execution is automatically created when importing execution results from an external source",
                            "testPlanKey", SharedVariables.configuration.getProperty("testPlanKey")
                    ),
                    "tests", testsToImport
            );

//            QueryStringEx queryStringEx = XrayMap.importExecution();
//            GlobalParameters.testCaseBase.success(queryStringEx.POST("", body, headers, QueryString.contentType.json));
//            rafRestResponseEx = new RAFRestResponseEx(queryStringEx.response);
//            String textBody = rafRestResponseEx.response.getBody().asString();
//            System.out.println("Response Body: " + textBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFeatureFiles(String sourceDirectory, String zipFilePath) {
        //String bearerToken = getBearerToken();
        try {
            File sourceFolder = new File(sourceDirectory);
            File zipFile = new File(zipFilePath);

            try (FileOutputStream fos = new FileOutputStream(zipFile);
                 ZipOutputStream zos = new ZipOutputStream(fos)) {

                zipDirectory(sourceFolder, sourceFolder, zos);
            }

            System.out.println("Zip file created successfully: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipDirectory(File sourceFolder, File currentFile, ZipOutputStream zos) throws IOException {
        if (currentFile.isDirectory()) {
            String entries[] = currentFile.list();

            if (entries != null) {
                for (String entry : entries) {
                    File file = new File(currentFile, entry);
                    zipDirectory(sourceFolder, file, zos);
                }
            }
        } else {
            try (FileInputStream fis = new FileInputStream(currentFile)) {
                String entryPath = currentFile.getAbsolutePath().substring(sourceFolder.getAbsolutePath().length() + 1);

                ZipEntry zipEntry = new ZipEntry(entryPath);
                zos.putNextEntry(zipEntry);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
        }
    }

    public static class Authorization {
        public static String ClientId = SharedVariables.configuration.getProperty("clientId");
        public static String ClientSecret = SharedVariables.configuration.getProperty("clientSecret");
    }
}
