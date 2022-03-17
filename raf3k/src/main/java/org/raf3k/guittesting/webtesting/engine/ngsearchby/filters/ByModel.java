package org.raf3k.guittesting.webtesting.engine.ngsearchby.filters;

import org.raf3k.guittesting.webtesting.engine.ngsearchby.JavaScriptBy;

/**
 * Wrapper around the NgBy.Model() static method to provide typed By selector for FindsByAttribute usage.
 */
public class ByModel extends JavaScriptBy {
    private String modelValue;

    /**
     * Creates a new instance of "ByModel".
     *
     * @param model The model name.
     */
    public ByModel(String model) {
        super(JSScripts.findModel, model);
        modelValue = model;
    }

    @Override
    public String toString() {
        return "NgBy.Model: " + modelValue;
    }
}
