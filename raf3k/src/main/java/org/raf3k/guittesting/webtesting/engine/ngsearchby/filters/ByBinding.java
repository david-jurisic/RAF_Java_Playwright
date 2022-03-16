package org.raf3k.guittesting.webtesting.engine.ngsearchby.filters;

import org.raf3k.guittesting.webtesting.engine.ngsearchby.JavaScriptBy;

public class ByBinding extends JavaScriptBy {
    private String bindingValue;

    /**
     * Creates a new instance of "JavaScriptBy"
     *
     * @param script The JavaScript code to execute.
     * @param args   The arguments to the script.
     */
    public ByBinding(String script, Object[] args) {
        super(script, args);
    }

    @Override
    public String toString() {
        return "NgBy.Binding: " + bindingValue;
    }
}
