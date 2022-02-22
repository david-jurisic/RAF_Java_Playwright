import org.raf3k.testproject.extendedtypes.ui.WbEditEx;
import org.raf3k.testproject.extendedtypes.ui.WbLabelEx;
import org.raf3k.testproject.extendedtypes.ui.WbPageEx;
import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;

public final class Map {
    public static void initialize() {
        UIReferences.currentPageContext = "https://roxoftkale.azurewebsites.net";
    }

    public static class Login {
        public static final WbPageEx page = new WbPageEx("Account/Login", "Page");
        public static final WbLabelEx lblMemberLogin = new WbLabelEx(By.xpath("//h2[@style='font-style: oblique;']"), "lblMmemberLogin");
        public static final WbEditEx txtEmail = new WbEditEx(By.id("Email"), "txtEmail");
        public static final WbEditEx txtPassword = new WbEditEx(By.id("password"), "txtPassword");
    }
}
