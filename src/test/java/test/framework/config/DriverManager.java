package test.framework.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private static WebDriver driver;
    private static final Configuration config = Configuration.getConfig();

    private DriverManager(WebDriver driver) {
        DriverManager.driver = driver;
    }

    public static WebDriver getDriver() {
        var browser = config.getBrowser();
        if (driver == null) {
            switch (browser) {
                case EDGE -> initEdgeDriver();
                case CHROME -> initChromeDriver();
                case FIREFOX -> initGeckoDriver();
            }
        }
        return driver;
    }

    private static void initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    private static void initGeckoDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    private static void initEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriverManager().create();
        driver.manage().window().maximize();
    }

    public void openUrl() {
        driver.get(config.getBaseUrl());
    }

    public void endSession() {
        driver.quit();
    }
}
