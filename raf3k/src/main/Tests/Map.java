import org.raf3k.testproject.extendedtypes.ui.*;
import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;

public final class Map {
    public static void initialize() {
        UIReferences.currentPageContext = "https://roxoftkale.azurewebsites.net/";
    }

    public static class Login {
        public static final WbPageEx page = new WbPageEx("Account/Login", "Page");
        public static final WbLabelEx lblMemberLogin = new WbLabelEx(By.xpath("//h2[@style='font-style: oblique;']"), "lblMmemberLogin");
        public static final WbEditEx txtEmail = new WbEditEx(By.id("Email"), "txtEmail");
        public static final WbEditEx txtPassword = new WbEditEx(By.id("password"), "txtPassword");
        public static final WbCheckBoxEx chkRememberMe = new WbCheckBoxEx(By.id("RememberMe"), "chkRememberMe");
        public static final WbButtonEx btnSignIn = new WbButtonEx(By.xpath("/html/body/main/div/section/div/form/div[4]/div/input"),"btnSignIn");
    }

    public static class Home {
        public static WbPageEx page = new WbPageEx("","Page");
        public static WbButtonEx btnProjects = new WbButtonEx(By.xpath("//*[@id=\"collapse_target\"]/ul[1]/li[2]/a"),"btnProjects");
    }

    public static class Projects {
        public static WbPageEx page = new WbPageEx("Projects","Projects");
        public static WbButtonEx btnCreate = new WbButtonEx(By.xpath("//*[@id=\"main\"]/div/input"),"btnCreate");
    }

    public static class NewProject {
       public static WbPageEx page = new WbPageEx("Projects/Create","NewProject");
    }

}
