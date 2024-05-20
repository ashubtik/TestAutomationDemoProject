package test.framework.config;

import test.framework.constants.Browser;
import lombok.Data;
import test.framework.utilities.FileUtils;

@Data
public class Configuration {
    private Browser browser;
    private String username;
    private String password;
    private long fluentWait;
    private long pollingInterval;
    private boolean headless;
    private String baseUrl;

    private static Configuration config;

    public Configuration() {
        var properties = FileUtils.getProperties();
        browser = Browser.valueOf(properties.getProperty("config.browser"));
        username = properties.getProperty("config.username");
        password = properties.getProperty("config.password");
        fluentWait = Long.parseLong(properties.getProperty("config.fluent.wait"));
        pollingInterval = Long.parseLong(properties.getProperty("config.polling.interval"));
        headless = Boolean.parseBoolean(properties.getProperty("config.headless"));
        baseUrl = properties.getProperty("config.base.url");
    }

    public static Configuration getConfig() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }
}
