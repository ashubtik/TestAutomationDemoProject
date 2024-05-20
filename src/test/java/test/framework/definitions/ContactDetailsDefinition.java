package test.framework.definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertTrue;

public class ContactDetailsDefinition extends BasicSteps {

    @Then("Contact Details page is open")
    public void verifyContactDetailsPageIsOpen() {
        assertTrue(getPageManager().getContactDetailsPage().isContactDetailsPageOpen(),
                "Contact Details page was not open");
    }

    @When("User clicks delete button")
    public void clickDeleteButton() {
        getPageManager().getContactDetailsPage().clickDeleteButton();
    }
}
