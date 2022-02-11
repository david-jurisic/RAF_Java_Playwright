package RAF3kShared;

public class Helpers {
    public String CleanupPath(String sPath)
    {
        return sPath.replace("+", ".");
    }

    public static void WaitForAction(int iWaitTIme)
    {
        //System.Threading.Thread.Sleep(new TimeSpan(0, 0, iWaitTIme));
    }
}
