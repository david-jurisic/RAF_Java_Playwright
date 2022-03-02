import org.checkerframework.checker.index.qual.LessThan;
import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.enumerations.Operations;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

import java.util.ArrayList;

public class TestRunnerWbDropDown extends UITestCase {
    public TestRunnerWbDropDown() {
        super("DropDown test", "TC012", "Dražen Kozić");
    }

    @Test
    void First() {
        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        success(Map.Login.page.navigate());
        success(Map.Login.page.verifyDisplayed());

        success(Map.Login.wrpMain.verifyAllCheckboxesChecked(false));
        success(Map.Login.chkRememberMe.check(true, false));
        success(Map.Login.wrpMain.verifyAllCheckboxesChecked(true));

        success(Map.Login.txtEmail.setText("admin@roxoft.hr", false, false, false));
        success(Map.Login.txtPassword.setText("RoxoftKale123", false, false, false));
        success(Map.Login.btnSignIn.click());

        success(Map.Home.btnTags.click());
        success(Map.Tags.ddPageSize.setItem(5, 0));
        success(Map.Tags.ddPageSize.setItem("15", 0));
        success(Map.Tags.ddPageSize.verifySelected("15", true));
        success(Map.Tags.ddPageSize.verifySelected("10", false));
        success(Map.Tags.ddPageSize.verifyOptionCount(5, Operations.Equals));
        success(Map.Tags.ddPageSize.verifyOptionCount(6, Operations.LessThan));
        success(Map.Tags.ddPageSize.verifyOptionCount(2, Operations.MoreThan));
        success(Map.Tags.ddPageSize.verifyFirstSelectedOptionTextContains("15"));
        ArrayList<String> options = Map.Tags.ddPageSize.getAllOptions();
        success(Map.Tags.ddPageSize.verifyItemSelectedByValue("10", true));
        success(Map.Tags.ddPageSize.verifyItemExists("50", true));
        success(Map.Tags.ddPageSize.verifyItemExists("60", false));
        success(Map.Tags.ddPageSize.verifyItemExists(options,true));
        success(Map.Tags.ddPageSize.getFirstSelectedOptionText());
        System.out.println(Map.Tags.ddPageSize.firstSelectedOption);
        success(Map.Tags.ddPageSize.verifyNotEmpty());
        success(Map.Tags.ddPageSize.getItemTextByValue("15"));
        System.out.println(Map.Tags.ddPageSize.itemText);
        success(Map.Tags.ddPageSize.getItemValueByText("15"));
        System.out.println(Map.Tags.ddPageSize.itemValue);

    }
}
