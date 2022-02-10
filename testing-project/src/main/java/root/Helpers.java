package root;

public class Helpers {
    public String CleanupPath(String sPath)
    {
        return sPath.replace("+", ".");
    }
}
