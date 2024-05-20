package test.framework.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Map;

@Slf4j
public class AddContactPage extends BasePage {
    private final static String ADD_CONTACT = "Add Contact";
    private final static String CONTACT_TABLE_XPATH = "//table[@class='contactTable']";
    private final static String FORM_INPUT_XPATH_PATTERN = "//label[contains(.,'%s')]//following-sibling::input[1]";
    private WebDriver driver;

    public AddContactPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    public boolean isAddContactPageOpen() {
        return getElement(PAGE_HEADER_XPATH)
                .getText()
                .contains(ADD_CONTACT);
    }

    public void addNewContact(Map<String, String> data) {
        waitUntilVisible(SUBMIT_BUTTON_XPATH);
        log.info("Adding a new contact with the following data:");
        data.forEach((key, value) -> {
            log.info(key + " - " + value);
            setInputValue(String.format(FORM_INPUT_XPATH_PATTERN, key), value);
        });
        clickElement(SUBMIT_BUTTON_XPATH);
        waitUntilVisible(CONTACT_TABLE_XPATH);
    }
}
