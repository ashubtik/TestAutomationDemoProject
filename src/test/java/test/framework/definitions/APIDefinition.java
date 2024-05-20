package test.framework.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import test.framework.api.models.ContactDto;

import static test.framework.api.apisteps.ContactApiSteps.getContactApiSteps;

public class APIDefinition extends BasicSteps {
    private String token;
    private Response contactResponse;

    @Given("Authorization token extracted from cookies")
    public void extractAuthorizationToken() {
        token = getPageManager().getContactListPage().getToken();
    }

    @When("GET request is sent to retrieve contact data for id {string}")
    public void getContactResponse(String contactId) {
        contactResponse = getContactApiSteps().getContactResponse(contactId, token);
        var res = contactResponse.as(ContactDto.class);
        System.out.println("^^^ " + res.getCity());
        System.out.println("^^^ " + res.getEmail());
    }
}
