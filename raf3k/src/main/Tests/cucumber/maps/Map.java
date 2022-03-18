package cucumber.maps;

import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.types.*;

public final class Map {
    public static void initialize() {
        UIReferences.currentPageContext = "https://roxoftkale.azurewebsites.net/";
    }

    public static class Login {
        public static final WbPage page = new WbPage("Account/Login", "Page");
        public static final WbEdit txtEmail = new WbEdit(By.id("Email"), "txtEmail");
        public static final WbEdit txtPassword = new WbEdit(By.id("password"), "txtPassword");
        public static final WbCheckBox chkRememberMe = new WbCheckBox(By.id("RememberMe"), "chkRememberMe");
        public static final WbButton btnSignIn = new WbButton(By.xpath("/html/body/main/div/section/div/form/div[4]/div/input"), "btnSignIn");
        public static WbLabel lblEmailErrorMessage = new WbLabel(By.id("Email-error"), "lblEmailErrorMessage");
        public static WbLabel lblPasswordErrorMessage = new WbLabel(By.id("password-error"), "lblPasswordErrorMessage");
        public static WbLabel lblInvalidLoginAttempt = new WbLabel(By.xpath("//li[text()='Invalid login attempt.']"), "lblInvalidLoginAttempt");
    }

    public static class Home {
        public static final WbPage page = new WbPage("", "Page");
        public static final WbButton btnLogOut = new WbButton(By.id("logoutForm"), "btnLogOut");
    }
}
