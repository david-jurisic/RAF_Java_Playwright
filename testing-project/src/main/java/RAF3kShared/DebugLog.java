package RAF3kShared;

import java.util.logging.Logger;

public class DebugLog {
    static final Logger log = Logger.getLogger("Log");
    public static void Add(String sMessage, int iError)
    {
        log.info(sMessage);
    }
    public static void Add(Exception ex)
    {
        log.info("Error******** " + ex.getMessage());
    }
}
