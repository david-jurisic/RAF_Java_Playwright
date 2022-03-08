package org.raf3k.apitesting;

public class Helpers {
    public String cleanupPath(String sPath)
    {
        return sPath.replace("$", ".");
    }

    public static void waitForAction(int iWaitTIme)
    {
        //System.Threading.Thread.Sleep(new TimeSpan(0, 0, iWaitTIme));
    }


}
