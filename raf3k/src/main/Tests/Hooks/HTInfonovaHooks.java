package Hooks;

import java.util.ArrayList;
import java.util.List;

public class HTInfonovaHooks {

    // region Shared
    public static List<String> LastArea = new ArrayList<>();
    public static boolean SubAccount = false;
    public static boolean Offer = false;
    public static boolean Logout = false;
    public static String StreetName;
    public static String Number;
    public static String Suffix;
    public static String Zip;
    public static String City;
    public static String Place;
    public static String Country;
    public static String Address;
    public static String Location;
    public static String BillingAccount;
    // endregion
    // region TC0001_CreateCustomer
    public static String LegalName;
    public static String LastName;
    public static String FirstName;
    public static String Mobile;
    // endregion
    // region TC0002_CreateUser
    public static String TelekomID;
    public static String BillingAccountIDTC2 = "1194724231";
    public static boolean User = false;
    // endregion
    // region Ordering Test Cases
    public static String BillingAccountID = "1195116833";
    public static  String CustomerLocation = "I. RETKOVEC 20, GRAD ZAGREB 10000 Hrvatska";
    public static  String CustomerLocationFormatted = "I. RETKOVEC 20\n" + "Place/City: GRAD ZAGREB\n" + "10000 GRAD ZAGREB Hrvatska";
    public static String Offers;
    public static String ProvisID;
    public static String DisplayedName = "N/A";
    public static List<String> DisplayedNames = new ArrayList<>();
    public static String MobileNumber;
    public static String Email;
    // endregion
}