package shared.ui;

import map.ui.AeviAdminMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import org.testng.Assert;

public class AeviAdminShared {
    public static By findButtonByName(String buttonName, String page) {
        By button = null;

        switch (page) {
            case "AEVI Pay Admin | Login" -> {
                switch (buttonName) {
                    case "Save Changes" -> button = AeviAdminMap.LoginPage.btnSaveChanges;
                }
            }
            case "AEVI Pay Admin | Organization Units" -> {
                switch (buttonName) {
                    case "New Record" -> button = AeviAdminMap.OrganizationUnits.btnNewRecord;
                    case "Add Site" -> button = AeviAdminMap.OrganizationUnits.btnAddSite;
                }
            }
            case "AEVI Pay Admin | Site" -> {
                switch (buttonName) {
                    case "Site ID Reload" -> button = AeviAdminMap.OrganizationUnitsAddSite.btnSiteIdReload;
                }
            }
            case "AEVI Pay Admin | Terminals" -> {
                switch (buttonName) {
                    case "New Record" -> button = AeviAdminMap.Terminals.btnNewRecord;
                    case "Copy" -> button = AeviAdminMap.Terminals.btnCopy;
                    case "Delete" -> button = AeviAdminMap.Terminals.btnDelete;
                    case "Copy Modal" -> button = AeviAdminMap.Terminals.btnCopyModal;
                    case "Delete Modal" -> button = AeviAdminMap.Terminals.btnDeleteModal;
                    case "Copy Modal Terminal ID Reload" -> button = AeviAdminMap.Terminals.btnCopyModalTerminalIdReload;
                }
            }
            case "AEVI Pay Admin | Terminal" -> {
                switch (buttonName) {
                    case "Terminal ID Reload" -> button = AeviAdminMap.TerminalsAdd.btnTerminalIdReload;
                    case "Device API Token Reload" -> button = AeviAdminMap.TerminalsAdd.btnDeviceApiTokenReload;
                    case "Host Terminal ID Reload" -> button = AeviAdminMap.TerminalsAdd.btnHostTerminalIdReload;
                    case "Host Device ID Reload" -> button = AeviAdminMap.TerminalsAdd.btnHostDeviceIdReload;
                    case "OK" -> button = AeviAdminMap.TerminalsAdd.btnOK;
                }
            }
            case "AEVI Pay Admin | Data Groups" -> {
                switch (buttonName) {
                    case "New Record" -> button = AeviAdminMap.DataGroups.btnNewRecord;
                    case "Search" -> button = AeviAdminMap.DataGroups.btnSearch;
                    case "Delete" -> button = AeviAdminMap.DataGroups.btnDelete;
                }
            }
            case "AEVI Pay Admin | Data Group" -> {
                switch (buttonName) {
                    case "OK" -> button = AeviAdminMap.DataGroupsAdd.btnOK;
                }
            }
            case "AEVI Pay Admin | Contracts" -> {
                switch (buttonName) {
                    case "New Record" -> button = AeviAdminMap.Contracts.btnNewRecord;
                    case "Add Physical Contract" -> button = AeviAdminMap.Contracts.btnAddPhysicalContract;
                    case "Delete" -> button = AeviAdminMap.Contracts.btnDelete;
                }
            }
            case "AEVI Pay Admin | Contract" -> {
                switch (buttonName) {
                    case "OK" -> button = AeviAdminMap.ContractsAdd.btnOK;
                    case "Save" -> button = AeviAdminMap.ContractsAdd.btnSave;
                }
            }
            case "AEVI Pay Admin | Acquirers/Service Providers" -> {
                switch (buttonName) {
                    case "New Record" -> button = AeviAdminMap.AcquirersServiceProviders.btnNewRecord;
                    case "Add Physical Acquirer" -> button = AeviAdminMap.AcquirersServiceProviders.btnAddPhysicalAcquirer;
                }
            }
            case "AEVI Pay Admin | Contract Profiles" -> {
                switch (buttonName) {
                    case "New Record" -> button = AeviAdminMap.ContractProfiles.btnNewRecord;
                }
            }
            case "AEVI Pay Admin | Acquirer/Service Provider" -> {
                switch (buttonName) {
                    case "OK" -> button = AeviAdminMap.AcquirersServiceProvidersAdd.btnOK;
                    case "Yes" -> button = AeviAdminMap.AcquirersServiceProvidersAdd.btnModalYes;
                }
            }
        }

        return button;
    }

    public static By findTextboxByName(String textboxName, String page) {
        By textbox = null;

        switch (page) {
            case "AEVI Pay Admin | Data Groups" -> {
                switch (textboxName) {
                    case "Name" -> textbox = AeviAdminMap.DataGroups.txtName;
                }
            }
            case "AEVI Pay Admin | Data Group" -> {
                switch (textboxName) {
                    case "Name" -> textbox = AeviAdminMap.DataGroupsAdd.txtName;
                }
            }
            case "AEVI Pay Admin | Site" -> {
                switch (textboxName) {
                    case "Site ID" -> textbox = AeviAdminMap.OrganizationUnitsAddSite.txtSiteId;
                    case "Parent Unit" -> textbox = AeviAdminMap.OrganizationUnitsAddSite.txtParentUnitSearch;
                }
            }
            case "AEVI Pay Admin | Terminal" -> {
                switch (textboxName) {
                    case "Terminal ID" -> textbox = AeviAdminMap.TerminalsAdd.txtTerminalId;
                    case "Organization Unit" -> textbox = AeviAdminMap.OrganizationUnitsAddSite.txtParentUnitSearch;
                    case "Name" -> textbox = AeviAdminMap.TerminalsAdd.txtName;
                    case "Device API Token" -> textbox = AeviAdminMap.TerminalsAdd.txtDeviceApiToken;
                    case "ROME Acquirer 1001 Terminal ID" -> textbox = AeviAdminMap.TerminalsAdd.txtROMEAcquirer1001TerminalId;
                    case "ROME Acquirer 1001 Device ID" -> textbox = AeviAdminMap.TerminalsAdd.txtROMEAcquirer1001DeviceId;
                }
            }
            case "AEVI Pay Admin | Terminals" -> {
                switch (textboxName) {
                    case "Copy Modal Terminal ID" -> textbox = AeviAdminMap.Terminals.txtCopyModalTerminalId;
                    case "Copy Modal Name" -> textbox = AeviAdminMap.Terminals.txtCopyModalName;
                }
            }
            case "AEVI Pay Admin | Acquirer/Service Provider" -> {
                switch (textboxName) {
                    case "Non-financial Message Timeout" -> textbox = AeviAdminMap.AcquirersServiceProvidersAdd.txtNonFinancialMessageTimeout;
                    case "Authorization Timeout" -> textbox = AeviAdminMap.AcquirersServiceProvidersAdd.txtAuthorizationTimeout;
                    case "Name" -> textbox = AeviAdminMap.AcquirersServiceProvidersAdd.txtName;
                    case "Host TID Mask" -> textbox = AeviAdminMap.AcquirersServiceProvidersAdd.txtHostTIDMask;
                    case "Host Device ID Mask" -> textbox = AeviAdminMap.AcquirersServiceProvidersAdd.txtHostDeviceIdMask;
                }
            }
            case "AEVI Pay Admin | Contract" -> {
                switch (textboxName) {
                    case "Contract Number" -> textbox = AeviAdminMap.ContractsAdd.txtContract;
                    case "Contract Code" -> textbox = AeviAdminMap.ContractsAdd.txtContractCode;
                    case "Site" -> textbox = AeviAdminMap.ContractsAdd.txtSiteSearch;
                    case "Merchant Category Code" -> textbox = AeviAdminMap.ContractsAdd.txtMerchantCategoryCode;
                    case "Valid From" -> textbox = AeviAdminMap.ContractsAdd.txtValidFrom;
                }
            }
        }

        return textbox;
    }

    public static By findCheckboxByName(String checkboxName, String page) {
        By checkbox = null;

        switch (page) {
            case "AEVI Pay Admin | Data Group" -> {
                switch (checkboxName) {
                    case "Status" -> checkbox = AeviAdminMap.DataGroupsAdd.chkStatus;
                    case "Check Site ID/Terminal ID" -> checkbox = AeviAdminMap.DataGroupsAdd.chkSiteTerminalId;
                }
            }
        }

        return checkbox;
    }

    public static By findTabByName(String tabName, String page) {
        By tab = null;

        switch (page) {
            case "AEVI Pay Admin | Data Group" -> {
                switch (tabName) {
                    case "Parameters" -> tab = AeviAdminMap.DataGroupsAdd.tabParameters;
                }
            }
            case "AEVI Pay Admin | Contract" -> {
                switch (tabName) {
                    case "Parameters" -> tab = AeviAdminMap.ContractsAdd.tabParameters;
                    case "Basic Parameters" -> tab = AeviAdminMap.ContractsAdd.tabBasicSettings;
                }
            }
            case "AEVI Pay Admin | Contract Profile" -> {
                switch (tabName) {
                    case "Parameters" -> tab = AeviAdminMap.ContractProfilesAdd.tabParameters;
                }
            }
            case "AEVI Pay Admin | Terminal" -> {
                switch (tabName) {
                    case "ECR Integration" -> tab = AeviAdminMap.TerminalsAdd.tabERCIntegration;
                }
            }
        }

        return tab;
    }

    public static void verifyTableColumnExists(List<WebElement> headers, String columnName) {
        for (WebElement header : headers) {
            if (header.getText().equals(columnName)) {
                return;
            }
        }

        Assert.fail("Table column doesn't exist: " + columnName);
    }


    public static String getCellValueByColumnNameAndRowIndex(List<WebElement> headers, List<WebElement> row, String columnName) {
        verifyTableColumnExists(headers, columnName);

        int columnNumber = 0;
        for (int i = 1; i <= headers.size(); i++) {
            if (headers.get(i).getText().equals(columnName)) {
                columnNumber = i;
                break;
            }
        }

        return row.get(columnNumber).getText();
    }
}
