package org.raf3k.shared;

import java.util.logging.Logger;

public class DebugLog {
    static final Logger log = Logger.getLogger("Log");
    public static void add(String sMessage, int iError)
    {
        log.info(sMessage);
    }
    public static void add(Exception ex)
    {
        log.info("Error******** " + ex.getMessage());
    }
}
