package root;

import org.openqa.selenium.By;

public class WbEdit extends WebControlBase {
    public WbEdit(By SearchBy, String Alias) {
      super(SearchBy,Alias);
    }

    public WbEdit(By SearchBy, WebControlBase Parent, String Alias) {
        super(SearchBy,Parent,Alias);
    }
}
