import org.raf3k.apptesting.AppReferences;
import org.raf3k.apptesting.types.*;
import org.raf3k.guittesting.webtesting.types.*;
import org.raf3k.testproject.extendedtypes.api.QueryStringEx;
import org.raf3k.testproject.extendedtypes.ui.*;
import org.raf3k.apitesting.APIReferences;
import org.raf3k.apitesting.basetypes.QueryString;
import org.raf3k.testproject.extendedtypes.ui.WbEditEx;
import org.raf3k.testproject.extendedtypes.ui.WbLabelEx;
import org.raf3k.testproject.extendedtypes.ui.WbPageEx;
import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;

public final class Map {
    public static void initialize() {
        UIReferences.currentPageContext = "https://roxoftkale.azurewebsites.net/";
    }

    public static void initialize2() {
        UIReferences.currentPageContext = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_lists_unordered2";
    }
    
    public static void initializeAPI() {
        APIReferences.currentPageContext = "https://pokeapi.co/api/v2/";
    }

    public static void initializeAPITweeter() {
        APIReferences.currentPageContext = "https://api.twitter.com/2/";
    }

    public static void accessToken()
    {
        UIReferences.currentPageContext = "https://reacthttprequestapp.web.app/";
    }
    public static void twitterMapInit()
    {
        UIReferences.currentPageContext = "https://twitter.com/";
    }

    public static void corsAnyWhere()
    {
        UIReferences.currentPageContext = "https://cors-anywhere.herokuapp.com/corsdemo";
    }

    public static void initialize3() {
        AppReferences.sAppPackageName = "io.appium.android.apis";
    }

    public static class Login {
        public static final WbPageEx page = new WbPageEx("Account/Login", "Page");
        public static final WbLabelEx lblParent = new WbLabelEx(By.xpath("//div[@align='center']"), "parent");
        public static final WbLabelEx lblMemberLogin = new WbLabelEx(By.xpath(".//h2[@style='font-style: oblique;']"), lblParent, "lblMmemberLogin");
        public static final WbEditEx txtEmail = new WbEditEx(By.id("Email"), "txtEmail");
        public static final WbEditEx txtPassword = new WbEditEx(By.id("password"), "txtPassword");
        public static final WbCheckBoxEx chkRememberMe = new WbCheckBoxEx(By.id("RememberMe"), "chkRememberMe");
        public static final WbButtonEx btnSignIn = new WbButtonEx(By.xpath("/html/body/main/div/section/div/form/div[4]/div/input"), "btnSignIn");
        public static final WbWrapperEx wrpMain = new WbWrapperEx(By.xpath("/html/body/main/div"), "wrpMain");
    }

    public static class Home {
        public static WbPageEx page = new WbPageEx("", "Page");
        public static WbButtonEx btnProjects = new WbButtonEx(By.xpath("//*[@id=\"collapse_target\"]/ul[1]/li[2]/a"), "btnProjects");
        public static WbButtonEx btnTags = new WbButtonEx(By.xpath("//*[@id=\"collapse_target\"]/ul[1]/li[3]/a"), "btnTags");
        public static WbLinkEx link1 = new WbLinkEx(By.xpath("/html/body/main/div/section/div[2]/div/ul/li[2]/a"), "link1");
        public static WbLinkEx link2 = new WbLinkEx(By.xpath("//*[@id=\"collapse_target\"]/ul[1]/li[2]/a"), "link2");
    }

    public static class Projects {
        public static WbPageEx page = new WbPageEx("Projects", "Projects");
        public static WbButtonEx btnCreate = new WbButtonEx(By.xpath("//*[@id=\"main\"]/div/input"), "btnCreate");
    }

    public static class Tags {
        public static WbDropDownListEx ddlPageSize = new WbDropDownListEx(By.xpath("/html/body/main/div/section/div[1]/div/form/div/select"), "ddlPageSize");
        public static WbDropDownEx ddPageSize = new WbDropDownEx(By.xpath("/html/body/main/div/section/div[1]/div/form/div/select"), "ddPageSize");
    }

    public static class NewProject {
        public static WbPageEx page = new WbPageEx("Projects/Create", "NewProject");
        public static WbWrapperEx wrpMain = new WbWrapperEx(By.xpath("//*[@id=\"New\"]/div/form/div[1]"), "wrpMain");
        public static WbEditEx txtName = new WbEditEx(By.name("Name"), "txtName");
        public static WbPageEx page2 = new WbPageEx("https://www.index.hr/vijesti/clanak/traju-velike-borbe-diljem-zemlje-ukrajina-ubili-smo-desetke-rusa-oborili-6-aviona/2342018.aspx?index_ref=naslovnica_vijesti_prva_d", "Index");
    }

    public static class RadioButton {
        public static WbPageEx page = new WbPageEx("internet/web-contact-form-example-radio-buttons.shtml", "Page");
        public static WbButtonEx btnAcceptAll = new WbButtonEx(By.xpath("//*[@id=\"save\"]/span[2]"), "btnAcceptAll");
        public static WbReadioButtonEx rbExcel = new WbReadioButtonEx(By.xpath("//*[@id=\"align\"]/span[4]/input"), "rbExcel");
        public static WbReadioButtonEx rbQuickBook = new WbReadioButtonEx(By.xpath("//*[@id=\"align\"]/span[5]/input"), "rbQuickBook");
        public static WbReadioButtonEx rbBeginner = new WbReadioButtonEx(By.xpath("//*[@id=\"align\"]/span[8]/input"), "rbBeginner");
        public static WbReadioButtonEx rbIntermediate = new WbReadioButtonEx(By.xpath("//*[@id=\"align\"]/span[9]/input"), "rbIntermediate");
    }

    public static class Menu {
        public static WbPageEx page = new WbPageEx("howto/howto_css_icon_bar.asp", "page");
        public static WbButtonEx btnAccept = new WbButtonEx(By.id("accept-choices"), "btnAccept");
        public static WbMenu menu = new WbMenu(By.id("leftmenuinnerinner"), "menu");
    }

    public static class ListTest {
        public static WbPageEx page = new WbPageEx("", "page");
        public static WbList list = new WbList(By.xpath("/html/body/ul"), "list");
    }

    public static class TableTest {
        public static WbPageEx page = new WbPageEx("", "page");
        public static WbTableEx tblExample = new WbTableEx(By.xpath("//*[@id=\"content\"]/div/div[1]/div[1]/div/div[1]/div[5]/table"), "tblExample");
    }

    public static class AndroidApp {
        public static Application App = new Application("APIDemos");

        public static class MainMenu {
            public static MbButton btnFirst = new MbButton("btnFirst", By.id("android:id/button1"), null);
            public static MbWrapper wrpMain = new MbWrapper("wrpMain", By.id("android:id/list"), null);
            public static MbButton btnViews = new MbButton("btnViews", By.xpath("//android.widget.TextView[@content-desc='Views']"), null);
            public static MbButton btnControls = new MbButton("btnControls", By.xpath("//android.widget.TextView[@content-desc='Controls']"), null);
            public static MbButton btnDarkTheme = new MbButton("btnDarkTheme", By.xpath("//android.widget.TextView[@content-desc='2. Dark Theme']"), null);
            public static MbButton btnEnabledSave = new MbButton("btnEnabledSave", By.id("io.appium.android.apis:id/button"), null);
            public static MbButton btnDisabledSave = new MbButton("btnDisabledSave", By.id("io.appium.android.apis:id/button_disabled"), null);
            public static MbEdit txtHint = new MbEdit("txtHint", By.id("io.appium.android.apis:id/edit"), null);
            public static MbCheckBox chkCheckbox1 = new MbCheckBox("chkCheckbox1", By.id("io.appium.android.apis:id/check1"), null);
            public static MbRadioButton radRadioButton1 = new MbRadioButton("radRadioButton1", By.id("io.appium.android.apis:id/radio1"), null);
            public static MbCheckBox chkToggle = new MbCheckBox("chkToggle", By.id("io.appium.android.apis:id/toggle1"), null);
            public static MbButton btnDropdown = new MbButton("btnDropdown", By.id("io.appium.android.apis:id/spinner1"), null);
            public static MbButton btnMars = new MbButton("btnMars", By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.CheckedTextView[4]"), null);
            public static MbLabel lblTextColorPrimary = new MbLabel("lblTextColorPrimary", By.id("textColorPrimary"), null);
            public static MbButton lblScroll = new MbButton("lblScroll", By.xpath("//android.widget.TextView[@content-desc='Hover Events']"), null);
        }
    }

    public static class TwitterMap{
        public static class RequestToken
        {
            public static WbPageEx page = new WbPageEx("", "page");
            public static WbLinkEx lnkTempAccess = new WbLinkEx(By.name("corsLinkDemo"), "lnkTempAccess");
            public static WbEditEx txtClientId = new WbEditEx(By.name("ClientId"), "txtClientId");
            public static WbEditEx txtClientSecret = new WbEditEx(By.name("ClientSecret"), "txtClientSecret");
            public static WbEditEx txtScope = new WbEditEx(By.name("Scope"), "txtScope");
            public static WbButtonEx btnRequest = new WbButtonEx(By.name("requestButton"), "btnRequest");
            public static WbLabelEx lblAccessToken = new WbLabelEx(By.name("access_token"), "lblAccessToken");
        }

        public static class RequestAccess
        {
            public static WbPageEx page = new WbPageEx("", "page");
            public static WbButtonEx btnRequest = new WbButtonEx(By.xpath("//*[@type='submit']"), "btnRequest");
        }

        public static class OAuth
        {
            public static WbButtonEx btnNext = new WbButtonEx(By.xpath("//*[text()='Next']"), "btnNext");
            public static WbButtonEx btnLogin = new WbButtonEx(By.xpath("//*[text()='Log in']"), "btnLogin");
            public static WbEditEx txtUsername = new WbEditEx(By.name("text"), "txtUsername");
            public static WbLabelEx txtPhoneOrEmail = new WbLabelEx(By.xpath("//*[text() = 'Enter your phone number or email address']"), "txtPhoneOrEmail");
            public static WbEditEx txtPassword = new WbEditEx(By.name("password"), "txtPassword");
            public static WbButtonEx btnAuthorize = new WbButtonEx(By.xpath("//*[@data-testid='OAuth_Consent_Button']"), "btnAuthorize");
        }
    }

    public static class API{
        public static QueryStringEx Get = new QueryStringEx("pokemon");
        public static QueryStringEx Tweet = new QueryStringEx("tweets");
    }

}
