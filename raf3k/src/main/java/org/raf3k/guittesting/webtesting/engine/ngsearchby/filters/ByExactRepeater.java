package org.raf3k.guittesting.webtesting.engine.ngsearchby.filters;

import org.raf3k.guittesting.webtesting.engine.ngsearchby.JavaScriptBy;

/**
 * Wrapper around the NgBy.ExactRepeater() static method to provide typed By selector for FindsByAttribute usage.
 */
public class ByExactRepeater extends JavaScriptBy {
    private String repeatValue;

    /**
     * Creates a new instance of "ByRepeater".
     *
     * @param repeat The exact text of the repeater, e.g. 'cat in cats'.
     */
    public ByExactRepeater(String repeat) {
        super(JSScripts.findAllRepeaterRows, repeat, true);
        repeatValue = repeat;
    }

    @Override
    public String toString() {
        return "NgBy.ExactRepeater: " + repeatValue;
    }
}
