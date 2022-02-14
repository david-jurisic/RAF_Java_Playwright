import TestProject.ExtendedTypes.UI.WbEditEx;
import TestProject.ExtendedTypes.UI.WbPageEx;
import org.openqa.selenium.By;
import RAF3kGUItesting.UIReferences;

public final class Map {
    public static void Initialize() {
        UIReferences.CurrentPageContext = "https://roxoftkale.azurewebsites.net";
    }

    public static class Login {
        public static final WbPageEx page = new WbPageEx("Account/Login", "LoginPage");
        public static final WbEditEx txtEmail = new WbEditEx(By.id("Email"), "txtEmail");
        public static final WbEditEx txtPassword = new WbEditEx(By.id("password"), "txtPassword");
    }
}
