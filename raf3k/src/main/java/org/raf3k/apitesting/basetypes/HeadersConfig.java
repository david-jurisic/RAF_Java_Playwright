package org.raf3k.apitesting.basetypes;

import java.util.HashMap;
import java.util.Map;

public class HeadersConfig {
    public static Map<String,String> headers(String sKey, String sValue){
        Map<String,String> defaultHeaders = new HashMap<String, String>();
        defaultHeaders.put(sKey,sValue);
        return defaultHeaders;
    }
}
