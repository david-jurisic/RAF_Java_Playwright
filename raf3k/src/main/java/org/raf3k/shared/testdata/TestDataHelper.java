package org.raf3k.shared.testdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.raf3k.shared.DebugLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDataHelper {
    public TestData.TestCase readTestCaseData(String filePath, String testCaseCode) {
        try {
            TestData data = readTestData(filePath);
            if (data == null)
                return null;
            return data.testCases.stream().filter(t -> t.code.equals(testCaseCode)).findFirst().orElse(null);
        } catch (Exception ex) {
            DebugLog.add(ex);
            return null;
        }
    }

    public TestData readTestData(String filePath) {
        try {
            String dataFile = Paths.get("target", filePath + ".xml").toUri().toURL().toString();
            TestData data = null;
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                DebugLog.add("Test Data Not Found. Continuing without data.", 1);
                return null;
            }

            File initialFile = new File(dataFile);
            InputStream dataFileStream = new FileInputStream(initialFile);
            ObjectMapper objectMapper = new XmlMapper();

            return objectMapper.readValue(dataFileStream, TestData.class);
        } catch (Exception ex) {
            DebugLog.add(ex);
            return null;
        }
    }
}
