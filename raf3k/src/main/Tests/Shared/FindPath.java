package Shared;

import ExtendedTypes.API.QueryStringEx;
import ExtendedTypes.UI.*;
import Hooks.HTInfonovaHooks;
import Maps.UI.HTInfonovaMap;

public class FindPath {

    public static WbPageEx FindPage(String sPageName) {
        WbPageEx page = new WbPageEx();

        switch (sPageName.toLowerCase()) {
            case "ht infonova":
            case "login":
                page = HTInfonovaMap.LoginPage.Page;
                break;

            case "main":
                page = HTInfonovaMap.MainPage.Page;
                break;

            case "customers":
                page = HTInfonovaMap.CustomerPage.Page;
                break;

            case "profile":
                page = HTInfonovaMap.ProfilePage.Page;
                break;

            case "new customer":
                page = HTInfonovaMap.NewCustomer.Page;
                break;

            case "overview":
                page = HTInfonovaMap.OverviewPage.Page;
                break;

            case "subaccounts":
                page = HTInfonovaMap.SubaccountsPage.Page;
                break;

            case "users":
                page = HTInfonovaMap.UsersPage.Page;
                break;

            case "address check":
                page = HTInfonovaMap.SubaccountsPage.CreateSubAccount.Page;
                break;

            case "offer selection":
                page = HTInfonovaMap.OffersAndCustomers.OfferSelection.Page;
                break;

            case "offer configuration":
                page = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Page;
                break;

            case "customer details":
                page = HTInfonovaMap.SubaccountsPage.CustomerDetails.Page;
                break;

            case "payment details":
                page = HTInfonovaMap.SubaccountsPage.PaymentDetails.Page;
                break;

            case "summary":
                page = HTInfonovaMap.OffersAndCustomers.Summary.Page;
                break;

            case "offers":
                page = HTInfonovaMap.OffersPage.Page;
                break;

            case "shipping details":
                page = HTInfonovaMap.OffersAndCustomers.ShippingDetails.Page;
                break;

            case "add offer address check":
                page = HTInfonovaMap.OffersPage.AddOffer.Page;
                break;
        }
        return page;
    }

    public static WbEditEx FindTextbox(String sTextboxName) {
        WbEditEx textbox = null;

        switch (sTextboxName.toLowerCase()) {
            case "username":
                textbox = HTInfonovaMap.LoginPage.Username;
                break;
            case "password":
                textbox = HTInfonovaMap.LoginPage.Password;
                break;
            case "business number (oib)":
                textbox = HTInfonovaMap.NewCustomer.BusinessDetails.BusinessOIB;
                break;
            case "personal oib":
                textbox = HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalOIB;
                break;
            case "legal name (short)":
                textbox = HTInfonovaMap.NewCustomer.BusinessDetails.LegalNameShort;
                break;
            case "last name":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.LastName;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("users")) {
                    textbox = HTInfonovaMap.UsersPage.LastName;
                }
                break;
            case "first name":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.FirstName;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("users")) {
                    textbox = HTInfonovaMap.UsersPage.FirstName;
                }
                break;
            case "personal document id":
                textbox = HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalDocumentID;
                break;
            case "document issue institution":
                textbox = HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentIssueIns;
                break;
            case "street name":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.LegalAddress.StreetName;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("address check")) {
                    textbox = HTInfonovaMap.OffersAndCustomers.AddressCheck.StreetName;
                }
                break;
            case "number":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.LegalAddress.Number;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("address check")) {
                    textbox = HTInfonovaMap.OffersAndCustomers.AddressCheck.Number;
                }
                break;
            case "suffix":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.LegalAddress.Suffix;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("address check")) {
                    textbox = HTInfonovaMap.OffersAndCustomers.AddressCheck.Suffix;
                }
                break;
            case "zip":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.LegalAddress.Zip;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("address check")) {
                    textbox = HTInfonovaMap.OffersAndCustomers.AddressCheck.Zip;
                }
                break;
            case "city":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.LegalAddress.City;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("address check")) {
                    textbox = HTInfonovaMap.OffersAndCustomers.AddressCheck.City;
                }
                break;
            case "place":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.LegalAddress.Place;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("address check")) {
                    textbox = HTInfonovaMap.OffersAndCustomers.AddressCheck.Place;
                }
                break;
            case "mobile":
                textbox = HTInfonovaMap.NewCustomer.ContactDetails.Mobile;
                break;
            case "email":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("new customer")) {
                    textbox = HTInfonovaMap.NewCustomer.ContactDetails.Email;
                } else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("users")) {
                    textbox = HTInfonovaMap.UsersPage.Email;
                }
                break;
            case "short number":
                textbox = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Service.ShortNumber;
                break;
            case "displayed name":
                textbox = HTInfonovaMap.SubaccountsPage.CustomerDetails.DisplayName;
                break;
            case "region":
                textbox = HTInfonovaMap.SubaccountsPage.PaymentDetails.Region;
                break;
            case "e-mail":
                textbox = HTInfonovaMap.SubaccountsPage.PaymentDetails.Email;
                break;
            case "frame contract id":
                textbox = HTInfonovaMap.SubaccountsPage.PaymentDetails.FrameContractID;
                break;

            case "responsible person's period of authority beginning date":
                textbox = HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.ResponsibleIssue;
                break;
            case "responsible person's period of authority end date":
                textbox = HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.ResponsibleExpiration;
                break;
            case "document issue date":
                textbox = HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentIssue;
                break;
            case "document expiration date":
                textbox = HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentExpiration;
                break;
            case "search customers":
                textbox = HTInfonovaMap.CustomerPage.SearchCustomers;
                break;
        }
        return textbox;
    }

    public static WbButtonEx FindButton(String sButtonName) {
        WbButtonEx button = null;

        switch (sButtonName.toLowerCase()) {
            case "log in":
                button = HTInfonovaMap.LoginPage.Login;
                break;

            case "validate address":
                switch (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).toLowerCase()) {
                    case "new customer":
                        button = HTInfonovaMap.NewCustomer.LegalAddress.ValidateAddress;
                        break;

                    case "address check":
                        button = HTInfonovaMap.OffersAndCustomers.AddressCheck.ValidateAddress;
                        break;

                    case "payment details":
                        button = HTInfonovaMap.SubaccountsPage.PaymentDetails.ValidateAddress;
                        break;

                    case "shipping details":
                        button = HTInfonovaMap.OffersAndCustomers.ShippingDetails.ValidateAddress;
                        break;

                    case "add offer address check":
                        button = HTInfonovaMap.OffersAndCustomers.AddressCheck.ValidateServiceAddress;
                        break;
                }
                break;

            case "edit address":
                button = HTInfonovaMap.NewCustomer.LegalAddress.ValidateAddress;
                break;

            case "existing addresses":
                button = HTInfonovaMap.NewCustomer.ContactAddress.ExistingAddresses;
                break;

            case "check credit":
                button = HTInfonovaMap.NewCustomer.CreditScoring.CheckCredit;
                break;

            case "cancel":
                switch (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).toLowerCase()) {
                    case "address check", "new customer":
                        button = HTInfonovaMap.OffersAndCustomers.MatchingAddresses.Cancel;
                        break;
                    case "credit check":
                        button = HTInfonovaMap.NewCustomer.CreditScoring.Cancel;
                        break;
                    case "offer configuration":
                        button = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Service.SelectNumber.Cancel;
                        break;
                }
                break;

            case "ok":
                switch (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).toLowerCase()) {
                    case "new customer", "address check":
                        button = HTInfonovaMap.OffersAndCustomers.MatchingAddresses.Ok;
                        break;
                    case "credit check":
                        button = HTInfonovaMap.NewCustomer.CreditScoring.Ok;
                        break;
                }
                break;

            case "create customer":
                button = HTInfonovaMap.OffersAndCustomers.CreateCustomer;
                break;

            case "search":
                button = HTInfonovaMap.CustomerPage.Search;
                break;

            case "add user":
                button = HTInfonovaMap.UsersPage.AddUser;
                break;

            case "save changes":
                button = HTInfonovaMap.UsersPage.SaveChanges;
                break;

            case "next":
                button = HTInfonovaMap.OffersAndCustomers.Next;
                break;

            case "shopping cart":
                button = HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCart;
                break;

            case "mobile service [mobile_service]":
                button = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Mobile_Service;
                break;

            case "fixed voice service [fixed_voice_service]":
                button = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Fixed_Service;
                break;

            case "get number":
                button = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Service.GetNumber;
                break;

            case "menu":
                button = HTInfonovaMap.MainPage.Menu;
                break;

            case "create":
                button = HTInfonovaMap.CustomerPage.CreateCustomer;
                break;

            case "add offer":
                button = HTInfonovaMap.OffersAndCustomers.AddOffer;
                break;

            case "add ...":
                button = HTInfonovaMap.SubaccountsPage.AddSubaccounts;
                break;

            case "cancel warning":
                button = HTInfonovaMap.OffersAndCustomers.MatchingAddresses.CancelWarning;
                break;

            case "actions":
                button = HTInfonovaMap.OffersPage.Actions;
                break;

            case "close":
                button = HTInfonovaMap.OffersPage.OrdersAndServiceRequests.Close;
                break;
        }
        return button;
    }

    public static WbDropDownEx FindDropdown(String sDropdownMenu) {
        WbDropDownEx dropdown = null;

        switch (sDropdownMenu.toLowerCase()) {
            case "business type":
                dropdown = HTInfonovaMap.NewCustomer.BusinessDetails.BusinessType;
                break;
            case "industry type":
                dropdown = HTInfonovaMap.NewCustomer.BusinessDetails.IndustryType;
                break;
            case "company status":
                dropdown = HTInfonovaMap.NewCustomer.BusinessDetails.CompanyStatus;
                break;
            case "residency":
                dropdown = HTInfonovaMap.NewCustomer.BusinessDetails.Residency;
                break;
            case "authorisation of the responsible person":
                dropdown = HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.AuthorisationPerson;
                break;
            case "user role":
                dropdown = HTInfonovaMap.UsersPage.UserRole;
                break;
            case "edi bill media eligible":
                dropdown = HTInfonovaMap.SubaccountsPage.PaymentDetails.EDIBillMediaEligible;
                break;
        }
        return dropdown;
    }

    public static WbTableEx FindTable(String sTableName) {
        WbTableEx table = null;

        switch (sTableName.toLowerCase()) {
            case "search for customers":
                table = HTInfonovaMap.CustomerPage.SearchedForCustomers;
                break;

            case "numbers":
                table = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Service.SelectNumber.NumbersMenu;
                break;

            case "subaccount":
                table = HTInfonovaMap.SubaccountsPage.Subaccounts;
                break;

            case "orders":
                table = HTInfonovaMap.OffersPage.OrdersAndServiceRequests.Orders;
                break;

            case "process details":
                table = HTInfonovaMap.OffersPage.OrdersAndServiceRequests.ProcessDetails;
                break;

            case "order history":
                table = HTInfonovaMap.OffersPage.OrdersAndServiceRequests.OrderHistory;
                break;

            case "selected features":
                table = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.SelectedFeatures;
                break;
        }
        return table;
    }

    public static WbLabelEx FindLabel(String sLabelName) {
        WbLabelEx label = null;

        switch (sLabelName.toLowerCase()) {
            case "legal address":
                label = HTInfonovaMap.NewCustomer.LegalAddress.Address;
                break;
            case "contact address":
                label = HTInfonovaMap.NewCustomer.ContactAddress.Address;
                break;
            case "decision":
                label = HTInfonovaMap.NewCustomer.CreditScoring.Decision;
                break;
            case "reason":
                label = HTInfonovaMap.NewCustomer.CreditScoring.Reason;
                break;
            case "credit score":
                label = HTInfonovaMap.NewCustomer.CreditScoring.CreditScore;
                break;
            case "service address":
                label = HTInfonovaMap.OffersAndCustomers.AddressCheck.ServiceAddress;
                break;
            case "offers counter":
                label = HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.ShoppingCartCounter;
                break;
            case "recurring gross charge":
                label = HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.RecurringChargeTotal;
                break;
            case "recurring net charge":
                label = HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.RecurringChargeAlt;
                break;
            case "phone number":
                label = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Service.PickedPhoneNumber;
                break;
            case "customer header":
                label = HTInfonovaMap.SubaccountsPage.CustomerDetails.CustomerHeader;
                break;
            case "billing address":
                label = HTInfonovaMap.SubaccountsPage.PaymentDetails.BillingAddress;
                break;
            case "shipping address":
                label = HTInfonovaMap.OffersAndCustomers.ShippingDetails.ShippingAddress;
                break;
            case "provisioning order":
                label = HTInfonovaMap.OffersPage.OrdersAndServiceRequests.ProvisOrder;
                break;
            case "provisioning request details":
                label = HTInfonovaMap.OffersPage.OrdersAndServiceRequests.ProvisioningRequestDetails;
                break;
        }
        return label;
    }

    public static WbListEx FindList(String sListName) {
        WbListEx list = null;

        switch (sListName.toLowerCase()) {
            case "menu":
                list = HTInfonovaMap.MainPage.CustomerManagement;
                break;

            case "create customer menu":
                list = HTInfonovaMap.CustomerPage.CreateCustomerMenu;
                break;

            case "customer menu":
                list = HTInfonovaMap.OverviewPage.CustomerMenu;
                break;

            case "addresses":
                list = HTInfonovaMap.OffersAndCustomers.MatchingAddresses.Addresses;
                break;

            case "existing addresses":
                list = HTInfonovaMap.NewCustomer.ExistingAddressesList;
                break;

            case "account":
                list = HTInfonovaMap.OverviewPage.AccountList;
                break;

            case "offers":
                list = HTInfonovaMap.OverviewPage.OffersList;
                break;

            case "add ...":
                list = HTInfonovaMap.SubaccountsPage.AddSubaccountsList;
                break;

            case "validate address":
                if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("payment details"))
                    list = HTInfonovaMap.SubaccountsPage.PaymentDetails.ValidateAddressMenu;
                else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("shipping details"))
                    list = HTInfonovaMap.OffersAndCustomers.ShippingDetails.ValidateAddressMenu;
                else if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("add offer address check"))
                    list = HTInfonovaMap.OffersAndCustomers.AddressCheck.ValidateServiceAddresses;
                break;

            case "actions":
                list = HTInfonovaMap.OffersPage.ActionsList;
                break;

            case "provisioning":
                list = HTInfonovaMap.OffersPage.OrdersAndServiceRequests.ProvisNavTabs;
                break;

            case "feature filter", "feature group":
                list = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.FeatureFilter;
                break;

            case "shopping cart content":
                list = HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.ShoppingCartList;
                break;
        }
        return list;
    }

    public static WbCheckBoxEx FindCheckbox(String sCheckboxName) {
        WbCheckBoxEx checkbox = null;

        switch (sCheckboxName.toLowerCase()) {
            case "public tender":
                checkbox = HTInfonovaMap.NewCustomer.BusinessDetails.PublicTender;
                break;

            case "terms and conditions":
                checkbox = HTInfonovaMap.OffersAndCustomers.Summary.TermsAndConditions;
                break;
        }
        return checkbox;
    }
}
