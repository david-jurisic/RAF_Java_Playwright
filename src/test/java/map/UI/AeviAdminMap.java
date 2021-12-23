package map.UI;

import base.UI.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.UI.AdminUtil;

import java.util.List;

public class AeviAdminMap extends BaseUtil {
    public static class ChromeWarningPage
    {
        public static String chromePage = AdminUtil.WEBPAGE_URL;
        public static WebElement btnAdvanced = driver.findElement(By.id("details-button"));
        public static WebElement btnProceedTo = driver.findElement(By.id("proceed-link"));
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
        public static WebElement btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/button"));
        public static WebElement ddlChooseAnOption = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul"));
        public static WebElement btnAddSite = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul/li[2]/a"));
    }

    public static class UserSettings
    {
        public static WebElement btnLogOut = driver.findElement(By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[2]/div[2]/div/a"));
    }

    public static class SideBarMenu
    {
        public static WebElement btnFormConfigs = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/a"));
        public static WebElement btnDataGroups = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/div/ul/li[2]/a/span"));
        public static WebElement btnTerminals = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[6]/a/span"));
        public static WebElement btnContracts = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[5]/a/span"));
    }

    public static class DataGroups
    {
        public static String page = AdminUtil.WEBPAGE_URL + "/data-groups";
        public static WebElement btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a"));
        public static WebElement btnSearch = driver.findElement(By.name("_form1"));
        public static WebElement txtName = driver.findElement(By.id("criteria.name"));
    }

    public static class DataGroupsAdd
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "/data-groups/create";
        public static WebElement txtName = driver.findElement(By.id("dataGroup.name"));
        public static WebElement chkStatus = driver.findElement(By.id("status"));
        public static WebElement chkStatus_Click = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[2]/div[1]/div[1]"));
        public static WebElement chkSiteTerminalId = driver.findElement(By.id("checkSiteTerminalId"));
        public static WebElement chkSiteTerminalId_Click = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[3]/div[1]/div[1]/label/span"));
        public static WebElement tabParameters = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a"));
        public static WebElement ddlTIDGeneratorTemplate = driver.findElement(By.id("dataGroup.tidGeneratorTemplate"));
        public static WebElement btnOK = driver.findElement(By.name("_form2"));
    }

    public static class DataGroupsAddMessage
    {
        public static WebElement msgSuccess = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div[1]/div[2]"));
        public static WebElement btnCloseMessage = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div[1]/div[3]/button"));
    }

    public static class OrganizationUnitsSite
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "organization-units/create?type=site";
        public static WebElement txtSiteId = driver.findElement(By.id("siteIdInput"));
        public static WebElement btnSiteIdReload = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[1]/div[1]/div[1]/div/button"));
        public static WebElement ddlParentUnit = driver.findElement(By.id("select2-organizationUnitInput-container"));
    }

    public static class OrganizationUnitsSiteDropdownSite
    {
        public static WebElement txtParentUnitSearch = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
    }

    public static class OrganizationUnitsSiteDropdownSiteList
    {
        public static WebElement listParentUnit = driver.findElement(By.id("select2-organizationUnitInput-results"));
    }

    public static class Terminals
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "terminals";
        public static  WebElement btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a"));

    }

    public static class TerminalsAdd
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "terminals/create";
        public static WebElement txtTerminalId = driver.findElement(By.id("terminalIdInput"));
        public static WebElement ddlOrganizationUnit = driver.findElement(By.id("select2-organizationUnitInput-container"));
        public static WebElement ddlTerminalProfile = driver.findElement(By.id("terminalProfileSelector"));
        public static WebElement btnSiteIdReload = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[1]/div[1]/div[1]/div/button"));
    }

    public static class Contracts
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "contracts";
        public static WebElement btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/button"));
        public static WebElement ddlChooseAnOption = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul"));
        public static WebElement btnAddPhysicalContract = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul/li[2]/a"));
    }

    public static class ContractsAdd
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "contracts/create?type=physical";
        public static WebElement tabParameters = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a"));
        public static WebElement ddlApplicationsProfile = driver.findElement(By.id("acceptedAppProfilesSelect"));
    }
}
