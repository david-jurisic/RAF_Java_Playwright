package map.ui;

import org.openqa.selenium.By;
import util.ui.Settings;

public class AeviAdminMap {
    private static final String WEBPAGE_URL = Settings.getSettingsValue("webpage_url");

    public static class LoginPage {
        public static final String page = WEBPAGE_URL + "user/login";
        public static final By btnSaveChanges = By.xpath("//*[@id=\"command\"]/div/div[3]/button[2]");
        public static final By ddmUserCard = By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[2]");
        public static final By btnUserCard = By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[1]/div");
        public static final By btnLogOut = By.xpath("//a[.='Log Out']");
    }

    public static class SideBarMenu {
        public static final By btnFormConfigs = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/a");
        public static final By btnDataGroups = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/div/ul/li[2]/a");
        public static final By btnTerminals = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[6]/a/span");
        public static final By btnContracts = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[5]/a/span");
        public static final By btnAdvancedConfigs = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[5]/a");
        public static final By btnAcquirerServiceProvides = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[5]/div/ul/li[2]/a/span");
        public static final By btnProfiles = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[4]/a/span");
        public static final By btnContractProfiles = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[4]/div/ul/li[8]/a/span");
        public static final By btnOrganizationsAndTerminals = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/a/span");
    }

    public static class OrganizationUnits {
        public static final String page = WEBPAGE_URL + "organization-units";
        public static final By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/button");
        public static final By ddlChooseAnOption = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul");
        public static final By btnAddSite = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul/li[2]/a");
    }

    public static class OrganizationUnitsAddSite {
        public static final String page = WEBPAGE_URL + "organization-units/create?type=site";
        public static final By txtSiteId = By.id("siteIdInput");
        public static final By btnSiteIdReload = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[1]/div[1]/div[1]/div/button");
        public static final By ddlParentUnit = By.id("select2-organizationUnitInput-container");
        public static final By txtParentUnitSearch = By.xpath("/html/body/span/span/span[1]/input");
        public static final By listParentUnit = By.id("select2-organizationUnitInput-results");
    }

    public static class DataGroups {
        public static final String page = WEBPAGE_URL + "data-groups";
        public static final By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a");
        public static final By btnSearch = By.name("_form1");
        public static final By txtName = By.id("criteria.name");
        public static final By tblDataGroups = By.xpath("//*[@id=\"command\"]/div[2]/div[5]/div[1]/div/table");
        public static final By btnDelete = By.id("deleteButton");
        public static final By modal = By.id("deleteModal");
        public static final By btnModalDelete = By.name("_form-3");
        public static final By msgSuccess = By.xpath("//*[@id=\"kt_content\"]/div[1]/div[2]");
    }

    public static class DataGroupsAdd {
        public static final String page = WEBPAGE_URL + "data-groups/create";
        public static final String pageEdit = WEBPAGE_URL + "data-groups/edit/";
        public static final By txtName = By.id("dataGroup.name");
        public static final By chkStatus = By.id("status");
        public static final By chkSiteTerminalId = By.id("checkSiteTerminalId");
        public static final By chkStatus_Click = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[2]/div[1]/div[1]");
        public static final By chkSiteTerminalId_Click = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[3]/div[1]/div[1]/label/span");
        public static final By tabParameters = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a");
        public static final By ddlTIDGeneratorTemplate = By.id("dataGroup.tidGeneratorTemplate");
        public static final By btnOK = By.name("_form2");
    }

    public static class Terminals {
        public static final String page = WEBPAGE_URL + "terminals";
        public static final String pageEdit = WEBPAGE_URL + "terminals/edit/";
        public static final By txtCopyModalTerminalId = By.id("terminalIdInput");
        public static final By txtCopyModalName = By.id("name");
        public static final By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a");
        public static final By btnTableFirstCheckBox = By.id("criteria.selectedItems1");
        public static final By btnCopy = By.id("copyTerminal");
        public static final By btnDelete = By.id("deleteButton");
        public static final By btnCopyModal = By.id("terminalCopyModalSubmit");
        public static final By btnCopyModalTerminalIdReload = By.xpath("//*[@id=\"terminalCopyModal\"]/div/div/div[2]/div[3]/div/div/div/button");
        public static final By btnDeleteModal = By.id("//*[@id=\"deleteModal\"]/div/div/div[3]/button[2]");
        public static final By tblTerminals = By.id("terminal-list");
        public static final By modalCopy = By.id("terminalCopyModal");
        public static final By modalDelete = By.id("deleteModal");
        public static final By ddlCopyModalOrganizationUnit = By.id("select2-copyOrganizationUnitInput-container");
    }

    public static class TerminalsAdd {
        public static final String page = WEBPAGE_URL + "terminals/create";
        public static final String pageEdit = WEBPAGE_URL + "terminals/edit/";
        public static final By tabERCIntegration = By.xpath("//*[@id=\"kt_content\"]/div/div/div/ul/li[3]/a");
        public static final By txtTerminalId = By.id("terminalIdInput");
        public static final By txtName = By.id("terminal.name");
        public static final By txtDeviceApiToken = By.id("deviceApiToken");
        public static final By ddlOrganizationUnit = By.id("select2-organizationUnitInput-container");
        public static final By ddlTerminalProfile = By.id("terminalProfileSelector");
        public static final By btnTerminalIdReload = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[1]/div[1]/div[1]/div/button");
        public static final By btnDeviceApiTokenReload = By.xpath("//*[@id=\"terminalEcrIntegrationSection\"]/div/div/div/div/div[1]/div/button");
        public static final By btnOK = By.name("_form2");
        public static final By txtROMEAcquirer1001TerminalId = By.id("hostTerminalIdInput0");
        public static final By txtROMEAcquirer1001DeviceId = By.id("hostDeviceIdInput0");
        public static final By btnHostTerminalIdReload = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[8]/div[1]/div/div/button");
        public static final By btnHostDeviceIdReload = By.xpath("//*[@id=\"basicPropertyGroup\"]/div[9]/div[1]/div/div/button");
        public static final By listOrganizationUnit = By.id("select2-organizationUnitInput-results");
    }

    public static class Contracts {
        public static final String page = WEBPAGE_URL + "contracts";
        public static final By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/button");
        public static final By btnDelete = By.id("deleteButton");
        public static final By ddlChooseAnOption = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul");
        public static final By btnAddPhysicalContract = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul/li[2]/a");
        public static final By tblContracts = By.id("contract-list");
        public static final By modal = By.id("deleteModal");
    }

    public static class ContractsAdd {
        public static final String page = WEBPAGE_URL + "contracts/create?type=physical";
        public static final String pageEdit = WEBPAGE_URL + "contracts/edit/";
        public static final By tabParameters = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a");
        public static final By tabBasicSettings = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[1]/a");
        public static final By txtContract = By.id("contract.contractNumber");
        public static final By txtContractCode = By.id("contract.code");
        public static final By txtSiteSearch = By.xpath("/html/body/span/span/span[1]/input");
        public static final By txtMerchantCategoryCode = By.id("contract.merchantCategoryCode");
        public static final By txtValidFrom = By.id("validFrom");
        public static final By ddlApplicationsProfile = By.id("acceptedAppProfilesSelect");
        public static final By ddlAquireServiceProvider = By.id("acquirerSelect");
        public static final By ddlSite = By.id("select2-organizationUnitInput-container");
        public static final By listSite = By.id("select2-organizationUnitInput-results");
        public static final By btnOK = By.name("_form2");
        public static final By btnSave = By.name("_form1");
        public static final By msgContractCodeValidationMessage = By.id("contract.code.errors");
        public static final By tblProfile = By.xpath("//*[@id=\"paramPropertyGroup\"]/div[4]/div/table");
        public static final By tblProfileHeaders = By.xpath("//*[@id=\"paramPropertyGroup\"]/div[4]/div/table/thead");
        public static final By tblProfileBodyFirstRow = By.id("acceptedAppProfilesTable");
    }

    public static class AcquirersServiceProviders {
        public static final String page = WEBPAGE_URL + "acquirers";
        public static final By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/button");
        public static final By ddlChooseAnOption = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul");
        public static final By btnAddPhysicalAcquirer = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/div/div/ul/li[2]/a");
        public static final By tblAcquirerServiceProviders = By.xpath("//*[@id=\"command\"]/div[2]/div[5]/div[1]/div/table");
    }

    public static class AcquirersServiceProvidersAdd {
        public static final String page = WEBPAGE_URL + "acquirers/create?type=physical";
        public static final String pageEdit = WEBPAGE_URL + "acquirers/edit/";
        public static final By txtNonFinancialMessageTimeout = By.id("acquirer.nonFinancialTimeout");
        public static final By txtAuthorizationTimeout = By.id("acquirer.authTimeout");
        public static final By txtName = By.id("acquirer.name");
        public static final By txtHostTIDMask = By.id("acquirer.hostTerminalIdMask");
        public static final By txtHostDeviceIdMask = By.id("acquirer.hostDeviceIdMask");
        public static final By btnModalYes = By.id("changeHostTerminalIdTypeYes");
        public static final By btnOK = By.name("_form2");
        public static final By ddlHostTIDType = By.id("acquirer.hostTerminalIdType");
        public static final By ddlHostDeviceIdType = By.id("acquirer.hostDeviceIdType");
        public static final By ddlHost = By.id("acquirer.hostConfigXml");
        public static final By ddlBatchCloseType = By.id("acquirer.batchCloseType");
        public static final By modal = By.id("changeHostTerminalIdTypeModal");
    }

    public static class ContractProfiles {
        public static final String page = WEBPAGE_URL + "contract-profiles";
        public static final By btnNewRecord = By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a");
    }

    public static class ContractProfilesAdd {
        public static final String page = WEBPAGE_URL + "contract-profiles/create";
        public static final By tabParameters = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a");
        public static final By ddlApplicationsProfile = By.id("contrProfileAcceptedAppProfilesSelect");
        public static final By tblProfile = By.xpath("//*[@id=\"paramPropertyGroup\"]/div[2]/div/table");
        public static final By tblProfileHeaders = By.xpath("//*[@id=\"paramPropertyGroup\"]/div[2]/div/table/thead");
        public static final By tblProfileBodyFirstRow = By.id("acceptedAppProfilesTable");
    }
}
