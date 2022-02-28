import org.raf3k.guittesting.webtesting.types.WbDropDownList;
import org.raf3k.guittesting.webtesting.types.WbMenu;
import org.raf3k.guittesting.webtesting.types.WbRadioButton;
import org.raf3k.testproject.extendedtypes.ui.*;
import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;

public final class Map {
    public static void initialize() {
        UIReferences.currentPageContext = "https://roxoftkale.azurewebsites.net/";
    }

    public static class Login {
        public static final WbPageEx page = new WbPageEx("Account/Login", "Page");
        public static final WbLabelEx lblParent = new WbLabelEx(By.xpath("//div[@align='center']"), "parent");
        public static final WbLabelEx lblMemberLogin = new WbLabelEx(By.xpath(".//h2[@style='font-style: oblique;']"), lblParent, "lblMmemberLogin");
        public static final WbEditEx txtEmail = new WbEditEx(By.id("Email"), "txtEmail");
        public static final WbEditEx txtPassword = new WbEditEx(By.id("password"), "txtPassword");
        public static final WbCheckBoxEx chkRememberMe = new WbCheckBoxEx(By.id("RememberMe"), "chkRememberMe");
        public static final WbButtonEx btnSignIn = new WbButtonEx(By.xpath("/html/body/main/div/section/div/form/div[4]/div/input"), "btnSignIn");
    }

    public static class Home {
        public static WbPageEx page = new WbPageEx("", "Page");
        public static WbButtonEx btnProjects = new WbButtonEx(By.xpath("//*[@id=\"collapse_target\"]/ul[1]/li[2]/a"), "btnProjects");
        public static WbButtonEx btnTags = new WbButtonEx(By.xpath("//*[@id=\"collapse_target\"]/ul[1]/li[3]/a"),"btnTags");
        public static WbLinkEx link1 = new WbLinkEx(By.xpath("/html/body/main/div/section/div[2]/div/ul/li[2]/a"), "link1");
        public static WbLinkEx link2 = new WbLinkEx(By.xpath("//*[@id=\"collapse_target\"]/ul[1]/li[2]/a"), "link2");
    }

    public static class Projects {
        public static WbPageEx page = new WbPageEx("Projects", "Projects");
        public static WbButtonEx btnCreate = new WbButtonEx(By.xpath("//*[@id=\"main\"]/div/input"), "btnCreate");
    }

    public static class Tags {
        public static WbDropDownListEx ddlPageSize = new WbDropDownListEx(By.xpath("/html/body/main/div/section/div[1]/div/form/div/select"),"ddlPageSize");
    }
    public static class NewProject {
        public static WbPageEx page = new WbPageEx("Projects/Create", "NewProject");
        public static WbPageEx page2 = new WbPageEx("https://www.index.hr/vijesti/clanak/traju-velike-borbe-diljem-zemlje-ukrajina-ubili-smo-desetke-rusa-oborili-6-aviona/2342018.aspx?index_ref=naslovnica_vijesti_prva_d", "Index");
    }

    public static class RadioButton {
        public static WbPageEx page = new WbPageEx("internet/web-contact-form-example-radio-buttons.shtml", "Page");
        public static WbButtonEx btnAcceptAll = new WbButtonEx(By.xpath("//*[@id=\"save\"]/span[2]"),"btnAcceptAll");
        public static WbReadioButtonEx rbExcel = new WbReadioButtonEx(By.xpath("//*[@id=\"align\"]/span[4]/input"), "rbExcel");
        public static WbReadioButtonEx rbQuickBook = new WbReadioButtonEx(By.xpath("//*[@id=\"align\"]/span[5]/input"), "rbQuickBook");
        public static WbReadioButtonEx rbBeginner = new WbReadioButtonEx(By.xpath("//*[@id=\"align\"]/span[8]/input"), "rbBeginner");
        public static WbReadioButtonEx rbIntermediate = new WbReadioButtonEx(By.xpath("//*[@id=\"align\"]/span[9]/input"), "rbIntermediate");
    }

    public static class Menu {
        public static WbPageEx page = new WbPageEx("howto/howto_css_icon_bar.asp","page");
        public static WbButtonEx btnAccept = new WbButtonEx(By.id("accept-choices"),"btnAccept");
        public static WbMenu menu = new WbMenu(By.id("leftmenuinnerinner"),"menu");
    }



}
