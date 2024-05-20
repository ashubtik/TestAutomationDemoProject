package test.framework.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static org.testng.Assert.assertTrue;

public class AddContactDefinition extends BasicSteps {
    private Map<String, String> contactData;

    @Then("Add Contact page is open")
    public void verifyAddContactPageIsOpen() {
        assertTrue(getPageManager().getAddContactPage().isAddContactPageOpen(),
                "Add Contact page was not open");
    }

    @When("New contact created with the following data")
    public void addNewContact(DataTable table) {
        contactData = table.asMap();
        getPageManager().getAddContactPage().addNewContact(contactData);
    }

    @Then("New contact is displayed on Contact List page")
    public void verifyNewContactIsDisplayed() throws InterruptedException {
        var page = getPageManager().getContactListPage();
        var softAssert = new SoftAssert();
        softAssert.assertTrue(page.isContactAttributeCorrect(contactData.get("Date of Birth")));
        softAssert.assertTrue(page.isContactAttributeCorrect(contactData.get("Email")));
        softAssert.assertTrue(page.isContactAttributeCorrect(contactData.get("Phone")));
        softAssert.assertTrue(page.isContactAttributeCorrect(contactData.get("Country")));
        softAssert.assertTrue(page.isContactAttributeCorrect(contactData.get("First Name")
                .concat(" ")
                .concat(contactData.get("Last Name"))));
        softAssert.assertAll("One of the components is not correct");
    }
}
