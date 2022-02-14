package RAF3kShared;

public class ControlObject {
    private String sPath;
    private String sAlias;
    private String sControlType;

    public String getsPath() {
        return sPath;
    }

    public void setsPath(String newsPath) {
        this.sPath = newsPath;
    }

    public String getsAlias() {
        return sAlias;
    }

    public void setsAlias(String newsAlias) {
        this.sAlias = newsAlias;
    }

    public String getsControlType() {
        return sControlType;
    }

    public void setsControlType(String newControlType) {
        this.sControlType = newControlType;
    }
}
