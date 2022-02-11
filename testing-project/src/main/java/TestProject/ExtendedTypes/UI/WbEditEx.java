package TestProject.ExtendedTypes.UI;

import RAF3kGUItesting.WebTesting.BaseTypes.WebControlBase;
import RAF3kGUItesting.WebTesting.Types.WbEdit;
import org.openqa.selenium.By;

public class WbEditEx extends WbEdit {
    public WbEditEx(By SearchBy, String Alias) {
        super(SearchBy, Alias);
    }

    public WbEditEx(By SearchBy, WebControlBase Parent, String Alias) {
        super(SearchBy, Parent, Alias);
    }
}
