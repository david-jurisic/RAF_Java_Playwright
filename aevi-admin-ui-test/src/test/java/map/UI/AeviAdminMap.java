package map.UI;

import org.openqa.selenium.By;
import util.UI.AdminUtil;

public class AeviAdminMap {
    public static class LoginPage
    {
        public static String page = AdminUtil.WEBPAGE_URL + "user/login";
        public static By btnSaveChanges = By.xpath("//*[@id=\"command\"]/div/div[3]/button[2]");
        public static By ddmUserCard = By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[2]");
        public static By btnUserCard = By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[1]/div");
        public static By btnLogOut = By.xpath("//a[.='Log Out']");
    }

    public static class SideBarMenu
    {
        public static By btnFormConfigs = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/a");
        public static By btnDataGroups = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/div/ul/li[2]/a/span");
        public static By btnTerminals = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[6]/a/span");
        public static By btnContracts = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[5]/a/span");
    }

    public static class OrganizationUnits
    {
        public static String page = AdminUtil.WEBPAGE_URL + "organization-units";
        public static By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/button");
        public static By ddlChooseAnOption = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul");
        public static By btnAddSite = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul/li[2]/a");
    }

    public static class OrganizationUnitsAddSite
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "organization-units/create?type=site";
        public static By txtSiteId = By.id("siteIdInput");
        public static By btnSiteIdReload = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[1]/div[1]/div[1]/div/button");
        public static By ddlParentUnit = By.id("select2-organizationUnitInput-container");
        public static By txtParentUnitSearch = By.xpath("/html/body/span/span/span[1]/input");
        public static By listParentUnit = By.id("select2-organizationUnitInput-results");
    }

    public static class DataGroups
    {
        public static String page = AdminUtil.WEBPAGE_URL + "data-groups";
        public static By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a");
        public static By btnSearch = By.name("_form1");
        public static By txtName = By.id("criteria.name");
        public static By tblDataGroups = By.xpath("//*[@id=\"command\"]/div[2]/div[5]/div[1]/div/table");
        public static By btnDelete = By.id("deleteButton");
        public static By modal = By.id("deleteModal");
        public static By btnModalDelete = By.name("_form-3");
        public static By msgSuccess = By.xpath("//*[@id=\"kt_content\"]/div[1]/div[2]");
    }

    public static class DataGroupsAdd
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "data-groups/create";
        public static final String pageEdit = AdminUtil.WEBPAGE_URL + "data-groups/edit/";
        public static By txtName = By.id("dataGroup.name");
        public static By chkStatus = By.id("status");
        public static By chkStatus_Click = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[2]/div[1]/div[1]");
        public static By chkSiteTerminalId = By.id("checkSiteTerminalId");
        public static By chkSiteTerminalId_Click = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[3]/div[1]/div[1]/label/span");
        public static By tabParameters = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a");
        public static By ddlTIDGeneratorTemplate = By.id("dataGroup.tidGeneratorTemplate");
        public static By btnOK = By.name("_form2");

    }

    public static class Terminals
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "terminals";
        public static By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a");
    }

    public static class TerminalsAdd
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "terminals/create";
        public static By txtTerminalId = By.id("terminalIdInput");
        public static By ddlOrganizationUnit = By.id("select2-organizationUnitInput-container");
        public static By ddlTerminalProfile = By.id("terminalProfileSelector");
        public static By btnSiteIdReload = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[1]/div[1]/div[1]/div/button");
    }

    public static class Contracts
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "contracts";
        public static By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/button");
        public static By ddlChooseAnOption = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul");
        public static By btnAddPhysicalContract = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul/li[2]/a");
    }

    public static class ContractsAdd
    {
        public static final String page = AdminUtil.WEBPAGE_URL + "contracts/create?type=physical";
        public static By tabParameters = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a");
        public static By ddlApplicationsProfile = By.id("acceptedAppProfilesSelect");
        public static By tblProfile = By.xpath("//*[@id=\"paramPropertyGroup\"]/div[4]/div/table");
        public static By tblProfileHeaders = By.xpath("//*[@id=\"paramPropertyGroup\"]/div[4]/div/table/thead");
        public static By tblProfileBodyFirstRow = By.id("acceptedAppProfilesTable");
    }
}
