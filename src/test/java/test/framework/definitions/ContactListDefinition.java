package test.framework.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactListDefinition extends BasicSteps {

    @Then("Contact List page is open")
    public void verifyIsContactListPageIsOpen() {
        assertTrue(getPageManager().getContactListPage().isContactListPageOpen(),
                "Contact List page was not open");
    }

    @When("Add a new contact button is clicked")
    public void clickAddContactButton() {
        getPageManager().getContactListPage().clickAddContactButton();
    }

    @Given("User logs out")
    public void logOut() {
        getPageManager().getContactListPage().logOut();
    }

    @When("User clicks {string} contact")
    public void clickContact(String contactName) {
        getPageManager().getContactListPage().clickContact(contactName);
    }

    @Then("There is no {string} in contact list anymore")
    public void verifyContactDeleted(String contact) {
        assertFalse(getPageManager().getContactListPage().isContactExist(contact),
                "The Contact is still in the List of contacts");
    }
}
