package test.framework.pages;

import org.openqa.selenium.WebDriver;

public class ContactListPage extends BasePage {
    private WebDriver driver;
    private final static String CONTACT_LIST = "Contact List";
    private final static String LOGOUT_BUTTON_XPATH = "//button[@id='logout']";
    private final static String CONTACT_CELL_XPATH_PATTERN = "//td[contains(.,'%s')]";

    public ContactListPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public boolean isContactListPageOpen() {
        return getElement(PAGE_HEADER_XPATH)
                .getText()
                .contains(CONTACT_LIST);
    }

    public void logOut() {
        waitUntilVisible(LOGOUT_BUTTON_XPATH)
                .click();
    }

    public void clickAddContactButton() {
        waitUntilClickable(ADD_CONTACT_BUTTON_XPATH)
                .click();
    }

    public boolean isContactAttributeCorrect(String value) {
        return isElementDisplayed(String.format(CONTACT_CELL_XPATH_PATTERN, value));
    }

    public void clickContact(String contactName) {
        waitUntilVisible(String.format(CONTACT_CELL_XPATH_PATTERN, contactName))
                .click();
    }

    public boolean isContactExist(String contactName) {
        return isElementDisplayed(String.format(CONTACT_CELL_XPATH_PATTERN, contactName));
    }
}
