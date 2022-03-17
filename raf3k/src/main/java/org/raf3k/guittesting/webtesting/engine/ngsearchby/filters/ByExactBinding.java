package org.raf3k.guittesting.webtesting.engine.ngsearchby.filters;

import org.raf3k.guittesting.webtesting.engine.ngsearchby.JavaScriptBy;

/**
 * Wrapper around the NgBy.ExactBinding() static method to provide typed By selector for FindsByAttribute usage.
 */
public class ByExactBinding extends JavaScriptBy {
    private String bindingValue;

    /**
     * Creates a new instance of "ByExactBinding".
     *
     * @param binding The exact binding, e.g. '{{cat.name}}'
     */
    public ByExactBinding(String binding) {
        super(JSScripts.findBindings, binding, true);
        bindingValue = binding;
    }

    @Override
    public String toString() {
        return "NgBy.ExactBinding: " + bindingValue;
    }

}
