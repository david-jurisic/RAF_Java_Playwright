package org.raf3k.guittesting.webtesting.engine.ngsearchby.filters;

import org.raf3k.guittesting.webtesting.engine.ngsearchby.JavaScriptBy;

/**
 * Wrapper around the NgBy.Binding() static method to provide typed By selector for FindsByAttribute usage.
 */
public class ByBinding extends JavaScriptBy {
    private String bindingValue;

    /**
     * Creates a new instance of "ByBinding".
     *
     * @param binding The binding, e.g. '{{cat.name}}'.
     */
    public ByBinding(String binding) {
        super(JSScripts.findBindings, binding, false);
        bindingValue = binding;
    }

    @Override
    public String toString() {
        return "NgBy.Binding: " + bindingValue;
    }
}
