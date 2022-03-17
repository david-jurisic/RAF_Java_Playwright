package org.raf3k.guittesting.webtesting.engine.ngsearchby.filters;

import org.raf3k.guittesting.webtesting.engine.ngsearchby.JavaScriptBy;

/**
 * Wrapper around the NgBy.Repeater() static method to provide typed By selector for FindsByAttribute usage.
 */
public class ByRepeater extends JavaScriptBy {
    private String repeatValue;

    /**
     * Creates a new instance of "ByRepeater".
     *
     * @param repeat The text of the repeater, e.g. 'cat in cats'.
     */
    public ByRepeater(String repeat) {
        super(JSScripts.findAllRepeaterRows, repeat, false);
        repeatValue = repeat;
    }

    @Override
    public String toString() {
        return "NgBy.Repeater: " + repeatValue;
    }
}
