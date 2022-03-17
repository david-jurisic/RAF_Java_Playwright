package org.raf3k.guittesting.webtesting.engine.ngsearchby;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.engine.ngsearchby.filters.*;

public class NgBy {
    /**
     * Gets a mechanism to find elements by their Angular binding.
     *
     * @param binding The binding, e.g. '{{cat.name}}'.
     * @return A "By" object the driver can use to find the elements.
     */
    public static By binding(String binding) {
        return new ByBinding(binding);
    }

    /**
     * Gets a mechanism to find elements by their exact Angular binding.
     *
     * @param binding The exact binding, e.g. '{{cat.name}}'.
     * @return A "By" object the driver can use to find the elements.
     */
    public static By exactBinding(String binding) {
        return new ByExactBinding(binding);
    }

    /**
     * Gets a mechanism to find elements by their model name.
     *
     * @param model The model name.
     * @return A "By" object the driver can use to find the elements.
     */
    public static By model(String model) {
        return new ByModel(model);
    }

    /**
     * Gets a mechanism to find select option elements by their model name.
     *
     * @param model The model name.
     * @return A "By" object the driver can use to find the elements.
     */
    public static By selectedOption(String model) {
        return new BySelectedOption(model);
    }

    /**
     * Gets a mechanism to find all rows of an ng-repeat.
     *
     * @param repeat The text of the repeater, e.g. 'cat in cats'.
     * @return A "By" object the driver can use to find the elements.
     */
    public static By repeater(String repeat) {
        return new ByRepeater(repeat);
    }

    /**
     * Gets a mechanism to find all rows of an ng-repeat.
     *
     * @param repeat The exact text of the repeater, e.g. 'cat in cats'.
     * @return A "By" object the driver can use to find the elements.
     */
    public static By exactRepeater(String repeat) {
        return new ByExactRepeater(repeat);
    }
}
