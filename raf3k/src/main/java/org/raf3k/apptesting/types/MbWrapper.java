package org.raf3k.apptesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.apptesting.AppReferences;
import org.raf3k.apptesting.basetypes.AppControlBase;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.logging.Success;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MbWrapper extends AppControlBase {

    public MbWrapper(String sAlias, By searchByAndroid, By searchByIos) {
        super(sAlias, searchByAndroid, searchByIos);
    }

    public MbWrapper(AppControlBase parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }

    public MbWrapper(WebElement parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }

    /**
     * Method gets all wrapper text.
     *
     * @return List of all app wrapper rows text.
     */
    public List<String> getWrapperText() {

        List<String> lsAllText;
        List<String> lsParsedText = new ArrayList<String>();

        String sControlText = control().getText();

        int iMiliseconds = Integer.parseInt(SharedVariables.configuration.getProperty("controlWaitTime")) * 1000;

        if (sControlText.isBlank()) {
            while (iMiliseconds > 0) {
                sControlText = control().getText();
                if (!sControlText.isBlank())
                    break;
                iMiliseconds -= 500;
            }
        }
        lsAllText = Arrays.stream(sControlText.split("\\r?\\n|\\r")).toList();
        for (String text : lsAllText
        ) {
            lsParsedText.add(text.replace("\n", ""));
        }
        return lsParsedText;
    }

    /**
     * Method verifies text inside app wrapper.
     *
     * @param sText Text to be verified inside app wrapper.
     * @return Success object.
     */
    public Success verifyText(String sText) {
        return AppReferences.eval().evaluate(() -> {

            boolean bFound = false;

            List<String> lsAllText = getWrapperText();
            for (String sCurrentRow : lsAllText
            ) {
                if (bFound)
                    break;
                if (sCurrentRow.toLowerCase().contains(sText.toLowerCase()))
                    bFound = true;
            }
            if (!bFound)
                throw new RuntimeException(String.format("Text not verified. Control text:" + System.lineSeparator() + "{0}"
                        + System.lineSeparator() + ", Searched text:" + System.lineSeparator() + " {1} ", String.join(",", lsAllText), sText));
        }, this, "");
    }
}
