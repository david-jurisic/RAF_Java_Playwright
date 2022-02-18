package RAF3kGUItesting.WebTesting.BaseTypes;

import RAF3kShared.DebugLog;
import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import RAF3kShared.ControlObject;
import RAF3kGUItesting.UIReferences;

import java.util.stream.Stream;

public class WebControlBase extends ControlObject {
    public Boolean bDisplayed = true;
    public Boolean bMustBeVisible = true;
    public int iTimeoutOverride = -1;
    private WebElement _Controlreference;
    private By searchBy;
    private WebControlBase parent;

    public WebElement control() {
        try {
            String sError = "";
            if (checkIfControlStale())
                _Controlreference = UIReferences.getWebEng().findControl(sControlType, sPath, searchBy, parent, sError, bDisplayed, bMustBeVisible, iTimeoutOverride);
            return _Controlreference;
        } catch (Exception ex) {
            DebugLog.add(ex);
            return null;
        }
    }

    public WebControlBase() {
        this.sControlType = this.getClass().getSimpleName();
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
    }

    public WebControlBase(By searchBy, String alias) {
        this.sControlType = this.getClass().getName();
        this.searchBy = searchBy;
        this.sAlias = alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
    }

    public WebControlBase(By searchBy, WebControlBase parent, String alias) {
        this.sControlType = this.getClass().getName();
        this.searchBy = searchBy;
        this.sAlias=alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
        this.parent = parent;
    }

    private Boolean checkIfControlStale() {
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
