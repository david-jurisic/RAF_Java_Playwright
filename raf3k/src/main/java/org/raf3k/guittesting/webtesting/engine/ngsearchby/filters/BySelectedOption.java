package org.raf3k.guittesting.webtesting.engine.ngsearchby.filters;

import org.raf3k.guittesting.webtesting.engine.ngsearchby.JavaScriptBy;

/**
 * Wrapper around the NgBy.SelectedOption() static method to provide typed By selector for FindsByAttribute usage.
 */
public class BySelectedOption extends JavaScriptBy {
    private String modelValue;

    /**
     * Creates a new instance of "BySelectedOption".
     *
     * @param model The model name.
     */
    public BySelectedOption(String model) {
        super(JSScripts.findSelectedOptions, model);
        modelValue = model;
    }

    @Override
    public String toString() {
        return "NgBy.SelectedOption: " + modelValue;
    }
}
