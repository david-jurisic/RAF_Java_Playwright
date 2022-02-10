package root;

import org.openqa.selenium.By;

public class WbEditEx extends WbEdit {
    public WbEditEx(By SearchBy, String Alias) {
        super(SearchBy, Alias);
    }

    public WbEditEx(By SearchBy, WebControlBase Parent, String Alias) {
        super(SearchBy, Parent, Alias);
    }
}
