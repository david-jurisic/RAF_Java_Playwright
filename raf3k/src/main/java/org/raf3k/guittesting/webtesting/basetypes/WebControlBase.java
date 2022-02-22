package org.raf3k.guittesting.webtesting.basetypes;

import org.raf3k.shared.DebugLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.shared.ControlObject;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.logging.Success;

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
        this.sAlias = alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
        this.parent = parent;
    }

    public WebControlBase(WebElement control, String alias) {
        this.sControlType = this.getClass().getName();
        this.sAlias = alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());

        _Controlreference = control;
    }

    public WebControlBase(By searchBy, WebElement Parent, String Alias) {
        this.sControlType = this.getClass().getName();
        this.searchBy = searchBy;
        this.sAlias = Alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());

        this.parent = new WebControlBase();
        this.parent._Controlreference = Parent;
        this.parent.sControlType = "inner";
        this.parent.sAlias = "Control parent";
        this.parent.sPath = this.sPath + ".Parent";
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

    /**
     * Method retrieves web control value.If textbox type is 'password' it will return empty string.
     *
     * @return Web control value
     */
    public String getControlText() {
        if (control().getAttribute("type") != null)
            if (!control().getAttribute("type").equals("password"))
                return "";

        if (control().getAttribute("value") != null)
            return control().getAttribute("value").toString();
        else
            return control().getText();
    }

    /**
     * Method verifies if web control exists.
     *
     * @param iControlWaitTime Control wait time.
     * @param bExists          Set to 'false' if you want to check if web control does not exist. It is 'true' by default.
     * @return Success object.
     */
    public Success exists(int iControlWaitTime, boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            iTimeoutOverride = iControlWaitTime;

            if (bExists) {
                if (control() == null)
                    try {
                        throw new Exception("Web control " + sAlias + " does not exist.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            } else {
                if (control() != null)
                    try {
                        throw new Exception("Web control " + sAlias + " exists.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
            iTimeoutOverride = -1;
        }, this, "");
    }
}
