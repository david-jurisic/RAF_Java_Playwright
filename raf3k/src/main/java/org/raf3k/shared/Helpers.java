package org.raf3k.shared;

import java.util.concurrent.TimeUnit;

public class Helpers {
    public String cleanupPath(String sPath) {
        return sPath.replace("$", ".");
    }

    public static void waitForAction(int iWaitTIme) {
        try {
            TimeUnit.SECONDS.sleep(iWaitTIme);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        ;
    }
}
