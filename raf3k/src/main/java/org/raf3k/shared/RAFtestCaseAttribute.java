package org.raf3k.shared;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RAFtestCaseAttribute {
    String testCaseName();
    String testCaseCode();
    TestType testType();
    String author();
}
