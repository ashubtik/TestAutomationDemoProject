package test.framework.pages;

import org.openqa.selenium.WebDriver;

public class ContactDetailsPage extends BasePage {
    private WebDriver driver;
    private final static String CONTACT_DETAILS = "Contact Details";
    private final static String DELETE_BUTTON_XPATH = "//button[@id='delete']";

    public ContactDetailsPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public boolean isContactDetailsPageOpen() {
        return getElement(PAGE_HEADER_XPATH)
                .getText()
                .contains(CONTACT_DETAILS);
    }

    public void clickDeleteButton() {
        waitUntilClickable(DELETE_BUTTON_XPATH)
                .click();
    }
}
