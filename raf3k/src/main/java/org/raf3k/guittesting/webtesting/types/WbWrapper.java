package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class WbWrapper extends WebControlBase {
    public WbWrapper(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbWrapper(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    /**
     * Method gets all web wrapper text.
     *
     * @return List of all web wrapper rows text.
     */
    public ArrayList<String> getText() {
        String sControlText = control().getText();
        int iMiliseconds = Integer.valueOf(SharedVariables.configuration.getProperty("controlWaitTime")) * 1000;
        if (sControlText == null || sControlText.isEmpty()) {
            while (iMiliseconds > 0) {
                sControlText = control().getText();
                if (sControlText != null && sControlText.isEmpty())
                    break;
                iMiliseconds -= 500;
            }
        }

        String[] lsAllText = sControlText.split("\n");
        ArrayList<String> lsParsedText = new ArrayList<>();

        for (String text : lsAllText) {
            lsParsedText.add(text.replace("\n", ""));
        }
        return lsParsedText;
    }

    /**
     * Method verifies text inside web wrapper.
     *
     * @param sText Text to be verified inside web wrapper.
     * @return Success object
     */
    public Success verifyText(String sText) {
        return UIReferences.eval().evaluate(() ->
        {
            boolean bFound = false;
            ArrayList<String> allText = getText();
            for (String currentRow : allText) {
                if (bFound)
                    break;
                if (currentRow.toLowerCase().contains(sText.toLowerCase()))
                    bFound = true;
            }
            if (!bFound)
                throw new RuntimeException(MessageFormat.format("Text not verified. Control text:\r\n{0},\r\nSearched text:\r\n{1}",
                        String.join(",", allText), sText));
        }, this, "");
    }

    /**
     * Method verifies if all checkboxes inside web wrapper are checked or not.
     *
     * @param bChecked Set to 'false' if you want to check if checkboxes inside web wrapper are unchecked. It is 'true' by default.
     * @return Success object
     */
    public Success verifyAllCheckboxesChecked(boolean bChecked) {
        return UIReferences.eval().evaluate(() ->
        {
            List<WebElement> allCheckboxes = control().findElements(By.xpath("//input[@type='checkbox']"));

            if (allCheckboxes.stream().anyMatch(m -> m.isSelected() == false) && bChecked)
                throw new RuntimeException("Not all checkboxes are checked");
            if (allCheckboxes.stream().anyMatch(m -> m.isSelected() == true) && !bChecked)
                throw new RuntimeException("Not all checkboxes are unchecked");
        }, this, "");
    }

    /**
     * Method verifies text inside web wrapper's input.
     *
     * @param sText Text to be verified inside input.
     * @return Success object
     */
    public Success verifyInputText(String sText) {
        return UIReferences.eval().evaluate(() ->
        {
            WebElement inputFIeld = control().findElement(By.tagName("input"));
            String text = inputFIeld.getAttribute("value");
            if (!text.toLowerCase().trim().contains(sText.toLowerCase()))
                throw new RuntimeException(MessageFormat.format("Text not verified. Control text:\r\n{0},\r\nSearched text:\r\n{1}", text, sText));
        }, this, "");

    }
}
