package root;

import jdk.jfr.StackTrace;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebControlBase extends ControlObject {
    public Boolean bDisplayed = true;
    public Boolean bMustBeVisible = true;
    public int iTimeoutOverride = -1;
    private WebElement _Controlreference;
    private By SearchBy;
    private WebControlBase parent;

    public By getSearchBy() {
        return SearchBy;
    }

    public void setSearchBy(By newSearchBy) {
        SearchBy = newSearchBy;
    }

    public WebControlBase getParent() {
        return parent;
    }

    public void setParent(WebControlBase newParent) {
        parent = newParent;
    }

    public WebElement Control() {
        try {
            String sError = "";
            if (CheckIfControlStale())
                _Controlreference = UIReferences.getWebEng().FindControl(getsControlType(), getsPath(), getSearchBy(), getParent(), sError, bDisplayed, bMustBeVisible, iTimeoutOverride);
            return _Controlreference;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public WebControlBase()
    {
        this.setsControlType(this.getClass().getName());
        this.setsPath(UIReferences.Hlpr().CleanupPath(""));
    }

    public WebControlBase(By SearchBy, String Alias)
    {
        this.setsControlType(this.getClass().getName());
        this.setSearchBy(SearchBy);
        this.setsAlias(Alias);
        this.setsPath(UIReferences.Hlpr().CleanupPath(""));
    }

    public WebControlBase(By SearchBy, WebControlBase Parent, String Alias)
    {
        this.setsControlType(this.getClass().getName());
        this.setSearchBy(SearchBy);
        this.setsAlias(Alias);
        this.setsPath(UIReferences.Hlpr().CleanupPath(""));
        this.setParent(Parent);
    }

    private Boolean CheckIfControlStale() {
        try {
            if (_Controlreference != null) {
                if (_Controlreference.isDisplayed())
                    return false;
            }
            return true;
        } catch (Exception ex) {
            return true;
        }
    }
}
