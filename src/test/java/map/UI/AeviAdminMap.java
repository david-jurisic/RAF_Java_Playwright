package map.UI;

import base.UI.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.UI.AdminUtil;

public class AeviAdminMap extends BaseUtil {
    public static class ChromeWarningPage
    {
        public static String chromePage = AdminUtil.WEBPAGE_URL;
        public static WebElement btnAdvanced = driver.findElement(By.id("details-button"));
        public static WebElement hlkProceedTo = driver.findElement(By.xpath("//*[@id=\"proceed-link\"]"));
    }

    public static class LoginPage
    {
        public static String page = AdminUtil.WEBPAGE_URL + "/user/login";
        public static WebElement btnSaveChanges = driver.findElement(By.xpath("//*[@id=\"command\"]/div/div[3]/button[2]"));
    }

    public static class OrganizationUnits
    {
        public static String page = AdminUtil.WEBPAGE_URL + "/organization-units";
        public static WebElement btnUserSettings = driver.findElement(By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[1]/div/span[3]"));
    }

    public static class UserSettings
    {
        public static WebElement hlkLogOut = driver.findElement(By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[2]/div[2]/div/a"));
    }

    public static class SideBarMenu
    {
        public static WebElement hlkFormConfigs = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/a"));
        public static WebElement hlkDataGroups = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/div/ul/li[2]/a/span"));
    }

    public static class DataGroups
    {
        public static String page = AdminUtil.WEBPAGE_URL + "/data-groups";
        public static WebElement btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a"));
        public static WebElement btnSearch = driver.findElement(By.name("_form1"));
        public static WebElement txtName = driver.findElement(By.id("criteria.name"));
        public static WebElement table = driver.findElement(By.xpath("//*[@id=\"command\"]/div[2]/div[5]/div[1]/div/table/tbody/tr/td[4]"));
    }

    public static class DataGroupsAdd
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "/data-groups/create";
        public static WebElement txtName = driver.findElement(By.id("dataGroup.name"));
        public static WebElement chkStatus = driver.findElement(By.id("status"));
        public static WebElement chkStatus_Click = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[2]/div[1]/div[1]"));
        public static WebElement chkSiteTerminalId = driver.findElement(By.id("checkSiteTerminalId"));
        public static WebElement chkSiteTerminalId_Click = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[3]/div[1]/div[1]/label/span"));
        public static WebElement pageParameters = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a"));
        public static WebElement ddlTIDGeneratorTamplate = driver.findElement(By.id("dataGroup.tidGeneratorTemplate"));
        public static WebElement btnOK = driver.findElement(By.name("_form2"));
    }

    public static class DataGroupsAddMessage
    {
        public static WebElement msgSuccess = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div[1]/div[2]"));
    }
}
