package org.raf3k.shared.testdata;

import java.util.ArrayList;

public class TestData {
    public ArrayList<TestCase> testCases;

    public class TestCase {
        public String code;
        public ArrayList<Parameter> parameters;
    }

    public class Parameter {
        public String code;
        public String value;
    }
}