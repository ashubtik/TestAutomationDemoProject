package test.framework.pages;

import lombok.extern.slf4j.Slf4j;
import test.framework.config.Configuration;
import org.openqa.selenium.WebDriver;

@Slf4j
public class LoginPage extends BasePage {
    private WebDriver driver;
    private final static String LOGIN_INPUT_XPATH = "//input[@id='email']";
    private final static String PASSWORD_INPUT_XPATH = "//input[@id='password']";
    private final static String LOGIN_ERROR_XPATH = "//span[@id='error']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public void getPlatform() {
        getUrl(Configuration.getConfig().getBaseUrl());
    }

    public void loginToPlatform(String username, String password) {
        waitUntilClickable(LOGIN_INPUT_XPATH);

        log.info("Entering username " + username);
        setInputValue(LOGIN_INPUT_XPATH, username);

        log.info("Entering password *****");
        setInputValue(PASSWORD_INPUT_XPATH, password);

        log.info("Clicking submit");
        clickElement(SUBMIT_BUTTON_XPATH);
    }

    public boolean isErrorDisplayed() {
        waitUntilVisible(LOGIN_ERROR_XPATH);
        return isElementDisplayed(LOGIN_ERROR_XPATH);
    }
}
