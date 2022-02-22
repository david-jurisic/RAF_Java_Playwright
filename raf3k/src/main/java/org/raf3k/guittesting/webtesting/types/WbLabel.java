package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;

public class WbLabel extends WebControlBase {
    public  WbLabel(By searchBy, String alias) {super(searchBy, alias); }
    public  WbLabel(By searchBy, WebControlBase parent, String alias) {super(searchBy, parent, alias); }

    public Success verifyText(String sText, Boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            if(bExists && !control().getText().equals(sText))
            {
                try {
                    throw new Exception(MessageFormat.format("Label Text '{0}' is not as expected. Expected text: {1}.", control().getText(), sText));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(!bExists && control().getText().equals(sText))
            {
                try {
                    throw new Exception(MessageFormat.format("Text is expected not to exist. Label Text: {0}, not expected text: {1}", control().getText(), sText));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, this, "");
    }
}
