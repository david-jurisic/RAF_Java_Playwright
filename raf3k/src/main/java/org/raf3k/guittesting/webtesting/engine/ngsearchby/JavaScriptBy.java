package org.raf3k.guittesting.webtesting.engine.ngsearchby;

import org.openqa.selenium.*;

import java.util.Arrays;
import java.util.List;

/**
 * Provides a mechanism by which to find elements within a document using JavaScript.
 */
public class JavaScriptBy extends By {
    public Object[] additionalScriptArguments;
    private String script;
    private Object[] args;

    /**
     * Creates a new instance of "JavaScriptBy"
     *
     * @param script The JavaScript code to execute.
     * @param args   The arguments to the script.
     */
    public JavaScriptBy(String script, Object[] args) {
        this.script = script;
        this.args = args;
    }

    /**
     * Finds the first element matching the criteria.
     *
     * @param context An "SearchContext" object to use to search for the elements.
     * @return The first matching WebElement on the current context.
     */
    @Override
    public WebElement findElement(SearchContext context) {
        List<WebElement> elements = this.findElements(context);
        if (elements.size() == 0)
            throw new NoSuchElementException(String.format("Unable to locate element: {{ {0} }}.", this.toString()));

        return elements.get(0);
    }

    /**
     * Finds all elements matching the criteria.
     *
     * @param context An "SearchContext" object to use to search for the elements.
     * @return A collection of all "WebElement" matching the current criteria, or an empty list if nothing matches.
     */
    @Override
    public List<WebElement> findElements(SearchContext context) {
        // Create script arguments
        Object[] scriptArgs = this.args;
        if (this.additionalScriptArguments != null && this.additionalScriptArguments.length > 0) {
            // Add additional script arguments
            scriptArgs = new Object[this.args.length + this.additionalScriptArguments.length];
            scriptArgs = Arrays.copyOf(this.args, 0);
            scriptArgs = Arrays.copyOf(this.additionalScriptArguments, this.args.length);
        }

        // Get JS executor
        JavascriptExecutor jsExecutor = (JavascriptExecutor) context;
        if (jsExecutor == null) {
            WrapsDriver wrapsDriver = (WrapsDriver) context;
            if (wrapsDriver != null)
                jsExecutor = (JavascriptExecutor) wrapsDriver.getWrappedDriver();
        }
        if (jsExecutor == null)
            throw new UnsupportedOperationException("Could not get an IJavaScriptExecutor instance from the context.");

        List<WebElement> elements = (List<WebElement>) jsExecutor.executeScript(this.script, scriptArgs);

        return elements;
    }

    @Override
    public String toString() {
        return "Protractor.JavaScriptBy";
    }
}
