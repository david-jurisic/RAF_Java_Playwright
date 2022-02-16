package RAF3kShared;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface RAFtestCaseAttribute {
    String testCaseName();
    String testCaseCode();
    TestType testType();
    String author();
}
