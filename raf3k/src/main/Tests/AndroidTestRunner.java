import io.appium.java_client.appmanagement.ApplicationState;
import org.junit.jupiter.api.Test;
import org.raf3k.apptesting.basetypes.AppTestCase;

import java.util.List;

public class AndroidTestRunner extends AppTestCase {
    public AndroidTestRunner() {
        super("Testing", "TC069", "Filip_Borić");
    }

    @Test
    void First() {
        Map.initialize3();
        newStep(1, "Open the app");

        success(Map.AndroidApp.MainMenu.wrpMain.displayed(true));
        success(Map.AndroidApp.App.verifyRunning());
        ApplicationState state = Map.AndroidApp.App.getCurrentAppState();
        success(Map.AndroidApp.App.verifyState(state));
        success(Map.AndroidApp.App.reset());
        success(Map.AndroidApp.MainMenu.btnViews.displayed(true));
        success(Map.AndroidApp.MainMenu.btnViews.enabled(true));
        success(Map.AndroidApp.MainMenu.btnViews.click());
        success(Map.AndroidApp.MainMenu.btnControls.click());
        success(Map.AndroidApp.MainMenu.btnDarkTheme.click());
        success(Map.AndroidApp.MainMenu.btnEnabledSave.enabled(true));
        success(Map.AndroidApp.MainMenu.btnEnabledSave.click());
        success(Map.AndroidApp.MainMenu.btnDisabledSave.enabled(false));
        success(Map.AndroidApp.MainMenu.txtHint.displayed(true));
        success(Map.AndroidApp.MainMenu.txtHint.enabled(true));
        success(Map.AndroidApp.MainMenu.txtHint.verifyEmpty(false));
        success(Map.AndroidApp.MainMenu.txtHint.setText("bla"));
        success(Map.AndroidApp.MainMenu.chkCheckbox1.select(true, false));
        success(Map.AndroidApp.MainMenu.radRadioButton1.select(true, false));
        success(Map.AndroidApp.MainMenu.chkToggle.select(true, false));
        success(Map.AndroidApp.MainMenu.btnDropdown.click());
        success(Map.AndroidApp.MainMenu.btnMars.click());

    }
}
