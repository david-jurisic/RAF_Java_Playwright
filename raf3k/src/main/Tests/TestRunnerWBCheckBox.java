import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.junit.jupiter.api.Test;

public class TestRunnerWBCheckBox extends UITestCase {
    public TestRunnerWBCheckBox() {
        super("CheckBox test", "TC003", "Dražen Kozić");
    }

    @Test
    void First() {

        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        success(Map.Login.page.navigate());
        success(Map.Login.chkRememberMe.verifyChecked(false));
        success(Map.Login.chkRememberMe.getCheckedAndSkip("getCheckedAndSkip message"));
        System.out.println("chkRememberMe: " + Map.Login.chkRememberMe.getCheckedValue);
        success(Map.Login.chkRememberMe.check(true, false));
        success(Map.Login.chkRememberMe.getCheckedAndSkip("getCheckedAndSkip message"));
        System.out.println("chkRememberMe: " + Map.Login.chkRememberMe.getCheckedValue);
        success(Map.Login.chkRememberMe.check(false, false));
        success(Map.Login.chkRememberMe.getCheckedAndSkip("getCheckedAndSkip message"));
        System.out.println("chkRememberMe: " + Map.Login.chkRememberMe.getCheckedValue);
        success(Map.Login.chkRememberMe.check(true, true));
        success(Map.Login.chkRememberMe.getCheckedAndSkip("getCheckedAndSkip message"));
        System.out.println("chkRememberMe: " + Map.Login.chkRememberMe.getCheckedValue);
    }
}
