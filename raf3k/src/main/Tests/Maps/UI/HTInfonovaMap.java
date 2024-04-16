package Maps.UI;

import ExtendedTypes.UI.*;
import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;

public class HTInfonovaMap {

    public static void initialize() {
        UIReferences.currentPageContext = "https://eu01.sb.infonova.com/";
    }

    public static class LoginPage {
        public static WbPageEx Page = new WbPageEx("auth/realms/HT_INTEGRATION_OPERATORS/protocol/openid-connect/auth?client_id=r6-ui&redirect_uri=https%3A%2F%2Feu01.sb.infonova.com%2Fr6-ui%2FSB_HT_INTEGRATION_1%2Findex%23!&state=1327326c-eee1-40be-855f-1c9da03df887&response_mode=fragment&response_type=code&scope=openid&nonce=454aa4b0-3b56-4689-9e0c-f2d074c5dffc&ui_locales=en", "Page");
        public static WbEditEx Username = new WbEditEx(By.id("username"), "Username");
        public static WbEditEx Password = new WbEditEx(By.id("password"), "Password");
        public static WbButtonEx Login = new WbButtonEx(By.id("kc-login"), "Login");
    }

    public static class MainPage {
        public static WbPageEx Page = new WbPageEx("r6-ui/SB_HT_INTEGRATION_1/index#!/userlandingpage", "Page");
        public static WbButtonEx Menu = new WbButtonEx(By.cssSelector("[class='bynd-button bynd-button--app-menu']"), "Menu");
        public static WbListEx Dashboard = new WbListEx(By.className("bynd-app-navigation-fixed"), "Dashboard");
        public static WbButtonEx Logout = new WbButtonEx(By.cssSelector("[r6-popover='r6-operator-toolbar-popover']"), "Logout");
        public static WbListEx LogoutList = new WbListEx(By.cssSelector("[class='r6nav r6nav--popover']"), "LogoutList");
        public static WbListEx NavigationBar = new WbListEx(By.className("bynd-app-navigation"), "NavigationBar");
        public static WbListEx CustomerManagement = new WbListEx(By.xpath("/html/body/div[1]/div[2]/header/r6-app-menu/div/nav/ul[1]"), "CustomerManagement");
    }

    public static class OffersAndCustomers {
        public static WbLabelEx Message = new WbLabelEx(By.xpath("/html/body/div[1]/div[1]/div/div/span[1]/span"), "Message");
        public static WbButtonEx Next = new WbButtonEx(By.id("r6-wizard-button-next"), "Next");
        public static WbButtonEx Previous = new WbButtonEx(By.cssSelector("[ng-click='r6Wizard.previous()']"), "Previous");
        public static WbButtonEx Cancel = new WbButtonEx(By.cssSelector("[r6-e2e-locator='r6-wizard-cancel-button']"), "Cancel");
        public static WbButtonEx SaveProspect = new WbButtonEx(By.id("r6-ordercapture-save-prospect-button"), "SaveProspect");
        public static WbButtonEx CreateCustomer = new WbButtonEx(By.id("r6-ordercapture-create-customer-button"), "CreateCustomer");
        public static WbButtonEx SaveDraftOrder = new WbButtonEx(By.cssSelector("[r6-e2e-locator='save-draft-order-button']"), "SaveDraftOrder");
        public static WbButtonEx AddOffer = new WbButtonEx(By.id("r6-ordercapture-add-or-modify-offer-button"), "AddOffer");

        public static class AddressCheck {
            public static WbEditEx StreetName = new WbEditEx(By.name("streetName"), "MenuList");
            public static WbEditEx Number = new WbEditEx(By.name("streetNumber1"), "Number");
            public static WbEditEx Suffix = new WbEditEx(By.name("suffix"), "Suffix");
            public static WbEditEx Zip = new WbEditEx(By.name("postCode"), "Zip");
            public static WbEditEx City = new WbEditEx(By.name("suburb"), "City");
            public static WbEditEx Place = new WbEditEx(By.name("place"), "Place");
            public static WbDropDownEx Country = new WbDropDownEx(By.name("countryKey"), "Country");
            public static WbButtonEx ValidateAddress = new WbButtonEx(By.cssSelector("[class='r6btn--showall r6btn r6btn--action ng-scope']"), "ValidateAddress");
            public static WbButtonEx ValidateServiceAddress = new WbButtonEx(By.xpath("//*[@id=\"service-address\"]/div/div/div[1]/div/r6-button-menu/div/div[1]"), "ValidateContactAddress");
            public static WbListEx ValidateServiceAddresses = new WbListEx(By.cssSelector("[class='r6nav r6nav--popover']"), "ValidateServiceAddresses");
            public static WbLabelEx ServiceAddress = new WbLabelEx(By.xpath("//*[@id=\"service-address\"]/div/div/div[2]/div/div/div"), "ServiceAddress");
        }

        public static class MatchingAddresses {
            public static WbListEx Addresses = new WbListEx(By.xpath("/html/body/div[1]/div/div/div/div/div/div/div[2]/div"), "Addresses");
            public static WbButtonEx Cancel = new WbButtonEx(By.cssSelector("[r6-e2e-locator='cancel']"), "Cancel");
            public static WbButtonEx Ok = new WbButtonEx(By.cssSelector("[r6-e2e-locator='service-address-next']"), "Ok");
            public static WbButtonEx CancelWarning = new WbButtonEx(By.cssSelector("[class='r6btn r6btn--cancel ng-binding']"), "CancelWarning");
        }

        public static class OfferSelection {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbTableEx Offers = new WbTableEx(By.xpath("//table[@class='r6table r6table--productselector']"), "Offers");
        }

        public static class OfferConfiguration {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbEditEx TargetDate = new WbEditEx(By.name("effectiveDate"), "TargetDate");
            public static WbEditEx ChargeDate = new WbEditEx(By.name("chargeDate"), "ChargeDate");
            public static WbButtonEx OfferHead = new WbButtonEx(By.cssSelector("[class='r6offerpanel-title ng-binding']"), "OfferHead");
            public static WbListEx FeatureFilter = new WbListEx(By.xpath("//div[@class='r6offerpanel-body-addon ng-isolate-scope']"), "FeatureFilter");
            public static WbButtonEx Mobile_Service = new WbButtonEx(By.cssSelector("[class='r6offer ng-scope ng-isolate-scope']"), "Mobile_Service");
            public static WbButtonEx Fixed_Service = new WbButtonEx(By.cssSelector("[r6-e2e-locator='service-head-FIXED_VOICE_SERVICE']"), "Fixed_Service");
            public static WbTableEx SelectedFeatures = new WbTableEx(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[3]/div[3]/div[2]/div/form/div/div/div[2]/div/div/div/div/div/div[1]/div[2]/div[1]/div[3]/table"), "SelectedFeatures");

            public static class Service {
                public static WbButtonEx GetNumber = new WbButtonEx(By.cssSelector("[r6-e2e-locator='ht-phone-number-select-btn']"), "GetNumber");
                public static WbLabelEx PickedPhoneNumber = new WbLabelEx(By.cssSelector("[r6-e2e-locator='ht-phone-number-value']"), "PickedPhoneNumber");
                public static WbEditEx ShortNumber = new WbEditEx(By.cssSelector("[class='r6form-control ng-pristine ng-untouched ng-scope ng-empty ng-invalid ng-invalid-v-a-l-u-e_-m-a-n-d-a-t-o-r-y is-with-error']"), "ShortNumber");


                public static class SelectNumber {
                    public static WbTableEx NumbersMenu = new WbTableEx(By.cssSelector("[class='r6table r6table--phone-number ng-scope']"), "NumbersMenu");
                    public static WbButtonEx Cancel = new WbButtonEx(By.cssSelector("[r6-e2e-locator='cancel']"), "Cancel");
                }
            }
        }

        public static class ShippingDetails {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbEditEx FullName = new WbEditEx(By.id("full-name"), "FullName");
            public static WbButtonEx ValidateAddress = new WbButtonEx(By.xpath("//*[@id=\"shipping-address\"]/div/div/div[1]/div/r6-button-menu"), "ValidateAddress");
            public static WbListEx ValidateAddressMenu = new WbListEx(By.xpath("//*[@id=\"ng-app\"]/body/div[4]/div[2]/div/div[2]/ul"), "ValidateAddressMenu");
            public static WbLabelEx ShippingAddress = new WbLabelEx(By.xpath("//*[@id=\"shipping-address\"]/div/div/div[2]/div/div/div"), "BillingAddress");

        }

        public static class Summary {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbTableEx BillingDetails = new WbTableEx(By.xpath("//div[@class='order-summary-section-content1']"), "BillingDetails");
            public static WbLabelEx OfferNameAndMobileNumber = new WbLabelEx(By.xpath("//div[@class='order-summary-all']/div/h3"), "OfferNameAndMobileNumber");
            public static WbTableEx ServiceDescription = new WbTableEx(By.xpath("//div[@class='subgroup1']/table[4]"), "ServiceDescription");
            public static WbTableEx ChargeSummaryForThisOrder = new WbTableEx(By.xpath("//table[@r6-e2e-locator=\"charge-sums-recurring-charges-1-EUR\"]"), "ChargeSummaryForThisOrder");
            public static WbCheckBoxEx TermsAndConditions = new WbCheckBoxEx(By.cssSelector("[r6-e2e-locator='terms-and-conditions']"), "TermsAndConditions");
        }
    }

    public static class CustomerPage {
        public static WbPageEx Page = new WbPageEx("", "Page");
        public static WbEditEx SearchCustomers = new WbEditEx(By.cssSelector("[r6-e2e-locator='r6-search-textfield']"), "SearchCustomers");
        public static WbButtonEx Search = new WbButtonEx(By.cssSelector("[r6-e2e-locator='r6-search-button']"), "Search");
        public static WbButtonEx CreateCustomer = new WbButtonEx(By.cssSelector("[r6-e2e-locator='r6-create-customer-action-button']"), "CreateCustomer");
        public static WbTableEx SearchedForCustomers = new WbTableEx(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[3]/div[3]/div[2]/div/div[2]/r6-search/div/div[2]/div[1]/div[2]/div/table"), "SearchedForCustomers");
        public static WbListEx CreateCustomerMenu = new WbListEx(By.xpath("/html/body/div[3]/div[2]/div/div[2]/ul"), "CreateCustomerMenu");
    }

    public static class ProfilePage {
        public static WbPageEx Page = new WbPageEx("", "Page");
    }

    public static class NewCustomer {
        public static WbPageEx Page = new WbPageEx("r6-ui/SB_HT_INTEGRATION_1/index#!/customermanagement/customercreation", "Page");
        public static WbButtonEx Cancel = new WbButtonEx(By.cssSelector("[r6-e2e-locator='r6-cancel-create-customer-button']"), "Cancel");
        public static WbListEx ExistingAddressesList = new WbListEx(By.cssSelector("[class='r6nav r6nav--popover']"), "ExistingAddressesList");

        public static class BusinessDetails {
            public static WbEditEx BusinessOIB = new WbEditEx(By.name("oib-business-number"), "BusinessOIB");
            public static WbDropDownEx BusinessType = new WbDropDownEx(By.name("business-type"), "BusinessType");
            public static WbEditEx LegalNameShort = new WbEditEx(By.name("legal-name-short"), "LegalNameShort");
            public static WbDropDownEx IndustryType = new WbDropDownEx(By.name("industry-type"), "IndustryType");
            public static WbDropDownEx CustomerSegment = new WbDropDownEx(By.name("customer-segment"), "CustomerSegment");
            public static WbDropDownEx CompanyStatus = new WbDropDownEx(By.name("company-status"), "CompanyStatus");
            public static WbCheckBoxEx PublicTender = new WbCheckBoxEx(By.id("procurement-type"), "PublicTender");
            public static WbDropDownEx Residency = new WbDropDownEx(By.name("residency"), "Residency");
        }

        public static class PrimaryResponsiblePerson {
            public static WbEditEx LastName = new WbEditEx(By.name("last-name"), "LastName");
            public static WbEditEx FirstName = new WbEditEx(By.name("first-name"), "FirstName");
            public static WbDropDownEx Department = new WbDropDownEx(By.name("department"), "Department");
            public static WbDropDownEx AuthorisationPerson = new WbDropDownEx(By.name("responsible-person"), "AuthorisationPerson");
            public static WbEditEx ResponsibleIssue = new WbEditEx(By.name("responsible-issue-date"), "ResponsibleIssue");
            public static WbEditEx ResponsibleExpiration = new WbEditEx(By.name("responsible-expiration-date"), "ResponsibleExpiration");
        }

        public static class PersonalIdentificationDocument {
            public static WbEditEx PersonalOIB = new WbEditEx(By.name("customer-oib"), "PersonalOIB");
            public static WbDropDownEx PersonalDocumentType = new WbDropDownEx(By.name("customer-document-type"), "PersonalDocumentType");
            public static WbEditEx PersonalDocumentID = new WbEditEx(By.name("customer-document-id"), "PersonalDocumentID");
            public static WbEditEx DocumentIssue = new WbEditEx(By.name("customer-document-issue-date"), "DocumentIssue");
            public static WbEditEx DocumentExpiration = new WbEditEx(By.name("customer-document-expiration-date"), "DocumentExpiration");
            public static WbEditEx DocumentIssueIns = new WbEditEx(By.name("customer-document-issue-ins"), "DocumentIssueIns");
        }

        public static class LegalAddress {
            public static WbEditEx StreetName = new WbEditEx(By.cssSelector("[r6-e2e-locator='legal-address-street-name']"), "StreetName");
            public static WbEditEx Number = new WbEditEx(By.cssSelector("[r6-e2e-locator='legal-address-street-number']"), "Number");
            public static WbEditEx Suffix = new WbEditEx(By.cssSelector("[r6-e2e-locator='legal-address-suffix']"), "Suffix");
            public static WbEditEx Zip = new WbEditEx(By.cssSelector("[r6-e2e-locator='legal-address-postcode']"), "Zip");
            public static WbEditEx City = new WbEditEx(By.cssSelector("[r6-e2e-locator='legal-address-town-suburb']"), "City");
            public static WbEditEx Place = new WbEditEx(By.cssSelector("[r6-e2e-locator='legal-address-place']"), "Place");
            public static WbDropDownEx Country = new WbDropDownEx(By.id("legal-address-countryKey"), "Country");
            public static WbButtonEx ValidateAddress = new WbButtonEx(By.xpath("//*[@id=\"legal-address\"]/div/div/div[1]/div/button"), "ValidateAddress");
            public static WbLabelEx Address = new WbLabelEx(By.xpath("//*[@id=\"legal-address\"]/div/div/div[2]/div/div/div"), "Address");
        }

        public static class ContactAddress {
            public static WbEditEx StreetName = new WbEditEx(By.cssSelector("[r6-e2e-locator='contact-address-street-name']"), "StreetName");
            public static WbEditEx Number = new WbEditEx(By.cssSelector("[r6-e2e-locator='contact-address-street-number']"), "Number");
            public static WbEditEx Suffix = new WbEditEx(By.cssSelector("[r6-e2e-locator='contact-address-suffix']"), "Suffix");
            public static WbEditEx Zip = new WbEditEx(By.cssSelector("[r6-e2e-locator='contact-address-postcode']"), "Zip");
            public static WbEditEx City = new WbEditEx(By.cssSelector("[r6-e2e-locator='contact-address-town-suburb']"), "City");
            public static WbEditEx Place = new WbEditEx(By.cssSelector("[r6-e2e-locator='contact-address-place']"), "Place");
            public static WbDropDownEx Country = new WbDropDownEx(By.id("contact-address-countryKey"), "Country");
            public static WbButtonEx ExistingAddresses = new WbButtonEx(By.cssSelector("[r6-e2e-locator='button-menu-btn']"), "ExistingAddresses");
            public static WbLabelEx Address = new WbLabelEx(By.xpath("//*[@id=\"contact-address\"]/div/div/div[2]/div/div/div"), "Address");
        }

        public static class ContactDetails {
            public static WbDropDownEx ContactMethod = new WbDropDownEx(By.name("preferred-contact-method"), "ContactMethod");
            public static WbEditEx Mobile = new WbEditEx(By.name("mobile"), "Mobile");
            public static WbEditEx Email = new WbEditEx(By.name("email"), "Email");
            public static WbDropDownEx ContactLanguage = new WbDropDownEx(By.name("preferred-language"), "ContactLanguage");
        }

        public static class CreditScoring {
            public static WbButtonEx CheckCredit = new WbButtonEx(By.cssSelector("[r6-e2e-locator='customer-credit-check-button']"), "CheckCredit");
            public static WbLabelEx Decision = new WbLabelEx(By.xpath("/html/body/div[1]/div/div/div/div/div/div/div[2]/div/div[1]/span[2]"), "Decision");
            public static WbLabelEx Reason = new WbLabelEx(By.xpath("/html/body/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/span[2]"), "Reason");
            public static WbLabelEx CreditScore = new WbLabelEx(By.xpath("/html/body/div[1]/div/div/div/div/div/div/div[2]/div/div[3]/span[2]"), "CreditScore");
            public static WbButtonEx Cancel = new WbButtonEx(By.cssSelector("[class='r6btn r6btn--cancel ng-binding']"), "Cancel");
            public static WbButtonEx Ok = new WbButtonEx(By.cssSelector("[r6-e2e-locator='dialog-ok']"), "Ok");
        }
    }

    public static class UsersPage {
        public static WbPageEx Page = new WbPageEx("", "Page");
        public static WbButtonEx AddUser = new WbButtonEx(By.cssSelector("[r6-e2e-locator='r6-add-user-btn']"), "AddUser");
        public static WbEditEx TelekomID = new WbEditEx(By.cssSelector("[id='username']"), "TelekomID");
        public static WbEditEx LastName = new WbEditEx(By.id("lastname"), "LastName");
        public static WbEditEx FirstName = new WbEditEx(By.id("firstname"), "FirstName");
        public static WbEditEx Email = new WbEditEx(By.id("email"), "Email");
        public static WbDropDownEx UserRole = new WbDropDownEx(By.id("userrole"), "UserRole");
        public static WbButtonEx Cancel = new WbButtonEx(By.cssSelector("[class='r6btn r6btn--cancel ng-binding']"), "Cancel");
        public static WbButtonEx SaveChanges = new WbButtonEx(By.cssSelector("[r6-e2e-locator='ok']"), "SaveChanges");
        public static WbLabelEx Message = new WbLabelEx(By.xpath("/html/body/div[1]/div[1]/div/div/span[1]/span"), "Message");
        public static WbTableEx UsersTable = new WbTableEx(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[3]/div[3]/div[2]/div/div/div/div/div[3]/table"), "UsersTable");
    }

    public static class SubaccountsPage {
        public static WbPageEx Page = new WbPageEx("", "Page");
        public static WbButtonEx AddSubaccounts = new WbButtonEx(By.cssSelector("[r6-e2e-locator='customer-add-subaccount-popover']"), "AddSubaccounts");
        public static WbListEx AddSubaccountsList = new WbListEx(By.cssSelector("[class='r6nav r6nav--popover r6nav--popoverlong']"), "AddSubaccountsList");
        public static WbTableEx Subaccounts = new WbTableEx(By.xpath("/html/body/div[1]/div[3]/div[3]/div[2]/div/div/div/div/div[4]/div[1]/div[2]/div[2]/table"), "Subaccounts");


        public static class CreateSubAccount {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbButtonEx ShoppingCart = new WbButtonEx(By.cssSelector("[ng-class='animateShoppincart']"), "ShoppingCart");

            public static class ShoppingCartContent {
                public static WbListEx ShoppingCartList = new WbListEx(By.className("r6shoppingcard-content"), "ShoppingCartList");
                public static WbLabelEx ShoppingCartCounter = new WbLabelEx(By.cssSelector("[class='r6shoppingcard-itemcounter ng-binding']"), "ShoppingCartCounter");
                public static WbLabelEx OnceOnlyChargeTotal = new WbLabelEx(By.cssSelector("[r6-e2e-locator='cart-totals-onceonly-0']"), "OnceOnlyChargeTotal");
                public static WbLabelEx OnceOnlyChargeAlt = new WbLabelEx(By.cssSelector("[r6-e2e-locator='cart-totals-onceonly-alt-0']"), "OnceOnlyChargeAlt");
                public static WbLabelEx RecurringChargeTotal = new WbLabelEx(By.cssSelector("[r6-e2e-locator='cart-totals-recurring-0']"), "RecurringChargeTotal");
                public static WbLabelEx RecurringChargeAlt = new WbLabelEx(By.cssSelector("[r6-e2e-locator='cart-totals-recurring-alt-0']"), "RecurringChargeAlt");
            }
        }

        public static class CustomerDetails {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbEditEx DisplayName = new WbEditEx(By.id("tradingName"), "DisplayName");
            public static WbLabelEx CustomerHeader = new WbLabelEx(By.cssSelector("[r6-e2e-locator='r6-customerheader-name']"), "CustomerHeader");
            public static WbEditEx BusinessNumberOIB = new WbEditEx(By.id("business-number"), "BusinessNumberOIB");
        }

        public static class PaymentDetails {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbEditEx CustomerName = new WbEditEx(By.name("tradingname"), "CustomerName");
            public static WbButtonEx ValidateAddress = new WbButtonEx(By.xpath("//*[@id=\"billing-address\"]/div/div/div[1]/div/r6-button-menu/div/div[1]"), "ValidateAddress");
            public static WbListEx ValidateAddressMenu = new WbListEx(By.xpath("//*[@id=\"ng-app\"]/body/div[4]/div[2]/div/div[2]/ul"), "ValidateAddressMenu");
            public static WbLabelEx BillingAddress = new WbLabelEx(By.xpath("//*[@id=\"billing-address\"]/div/div/div[2]/div/div/div"), "BillingAddress");
            public static WbDropDownEx BillMedia = new WbDropDownEx(By.name("deliveryMethod"), "BillMedia");
            public static WbDropDownEx EDIBillMediaEligible = new WbDropDownEx(By.name("EDIBillMediaEligible"), "EDIBillMediaEligible");
            public static WbDropDownEx DunningTreatment = new WbDropDownEx(By.name("dunningProcedures"), "DunningTreatment");
            public static WbEditEx Email = new WbEditEx(By.name("billing-details-email"), "Email");
            public static WbRadioButtonEx TestAccNo = new WbRadioButtonEx(By.id("billing-test-account-false"), "TestAccNo");
            public static WbRadioButtonEx TestAccYes = new WbRadioButtonEx(By.id("billing-test-account-true"), "TestAccYes");
            public static WbDropDownEx BillType = new WbDropDownEx(By.name("billing-bill-type"), "BillType");
            public static WbDropDownEx CreditClass = new WbDropDownEx(By.name("billing-credit-class"), "CreditClass");
            public static WbEditEx Region = new WbEditEx(By.name("billing-details-region"), "Region");
            public static WbEditEx FrameContractID = new WbEditEx(By.name("billing-details-frame-contract"), "FrameContractID");
        }
    }

    public static class OffersPage {
        public static WbPageEx Page = new WbPageEx("", "Page");
        public static WbButtonEx Actions = new WbButtonEx(By.cssSelector("[class='r6btn ng-isolate-scope r6btn--action r6btn--align-right r6btn--align-right-negative']"), "Actions");
        public static WbListEx ActionsList = new WbListEx(By.cssSelector("[class='r6nav r6nav--popover']"), "ActionsList");
        public static WbTableEx Offers = new WbTableEx(By.xpath("//table[@class='r6table--fixed r6table r6table--customeroffer']"), "Subaccounts");

        public static class AddOffer {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbButtonEx ShoppingCart = new WbButtonEx(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[3]/div[2]/r6-customer-offer-add-sidebar/div[1]/div"), "ShoppingCart");
        }

        public static class OrdersAndServiceRequests {
            public static WbPageEx Page = new WbPageEx("", "Page");
            public static WbTableEx Orders = new WbTableEx(By.xpath("/html/body/div[1]/div[3]/div[3]/div[2]/div/div/div/div/div[1]/div[2]/r6-datatable/div/div[1]/table"), "Orders");
            public static WbTableEx ProcessDetails = new WbTableEx(By.xpath("//table[@class='r6table r6table--fixed r6table--orderdetails']"), "ProcessDetails");
            public static WbLabelEx ProvisOrder = new WbLabelEx(By.xpath("/html/body/div[1]/div[3]/div[3]/div[2]/div/div/div/div/div[2]/div[2]/div[1]/div[1]/h1"), "ProvisOrder");
            public static WbListEx ProvisNavTabs = new WbListEx(By.xpath("//div[@active='0']/ul"), "ProvisNavTabs");
            public static WbTableEx OrderHistory = new WbTableEx(By.xpath("//table[@class='r6table--fixed r6table r6table--provisioning--orders']"), "OrderHistory");
            public static WbLabelEx ProvisioningRequestDetails = new WbLabelEx(By.xpath("//div[@class='r6modal-body r6table--scrollable-height-limiter ng-scope']"), "ProvisioningRequestDetails");
            public static WbButtonEx Close = new WbButtonEx(By.xpath("//button[@r6-e2e-locator='ok']"), "Close");
        }
    }

    public static class OverviewPage {
        public static WbPageEx Page = new WbPageEx("", "Page");
        public static WbButtonEx BillingAccount = new WbButtonEx(By.cssSelector("[r6-popover='r6-button-menu-popover']"), "BillingAccount");
        public static WbListEx CustomerMenu = new WbListEx(By.cssSelector("[r6-e2e-locator='r6-customer-menu']"), "CustomerMenu");
        public static WbListEx AccountList = new WbListEx(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[3]/div[1]/div/r6-customer-side-menu/div/div[2]"), "AccountList");
        public static WbListEx OffersList = new WbListEx(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[3]/div[1]/div/r6-customer-side-menu/div/div[3]"), "OffersList");
        public static WbListEx ContactDetails = new WbListEx(By.cssSelector("[class='r6widget-listing ng-scope']"), "ContactDetails");
    }
}