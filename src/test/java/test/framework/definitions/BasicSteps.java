package test.framework.definitions;

import test.framework.config.Configuration;
import test.framework.config.DriverManager;
import test.framework.pages.PageManager;

public abstract class BasicSteps {
    private PageManager pageManager;
    protected Configuration config = Configuration.getConfig();

    protected PageManager getPageManager() {
        return pageManager == null ?
                pageManager = new PageManager(DriverManager.getDriver()) : pageManager;
    }
}
