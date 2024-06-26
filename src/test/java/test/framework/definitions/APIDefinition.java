package test.framework.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import test.framework.api.models.ContactDto;
import test.framework.constants.Method;
import test.framework.utilities.DtoUtils;
import test.framework.utilities.JsonUtils;

import static org.testng.Assert.assertEquals;
import static test.framework.api.apisteps.ContactApiSteps.getContactApiSteps;

public class APIDefinition extends BasicSteps {
    private String token;
    private Response getContactResponse;
    private Response patchContactResponse;
    private Response deleteContactResponse;
    private Response postContactResponse;
    private Response putContactResponse;
    private ContactDto deserializedFromJson;

    @ParameterType("GET|DELETE|PATCH|POST|PUT")
    public Method method(String method) {
        return Method.valueOf(method);
    }

    @Given("Authorization token extracted from cookies")
    public void extractAuthorizationToken() {
        token = getPageManager().getContactListPage().getToken();
    }

    @When("GET request is sent to retrieve contact with id {string}")
    public void getContactResponse(String contactId) {
        if (contactId.equals("deleted")) {
            contactId = postContactResponse.as(ContactDto.class).get_id();
        }
        getContactResponse = getContactApiSteps().getContact(contactId, token);
    }

    @Then("{method} response has status code {int}")
    public void verifyContactResponseStatusCode(Method method, int expectedStatusCode) {
        int actualStatusCode = 0;
        switch (method) {
            case GET -> actualStatusCode = getContactResponse.getStatusCode();
            case PUT -> actualStatusCode = putContactResponse.getStatusCode();
            case POST -> actualStatusCode = postContactResponse.getStatusCode();
            case PATCH -> actualStatusCode = patchContactResponse.getStatusCode();
            case DELETE -> actualStatusCode = deleteContactResponse.getStatusCode();
        }
        assertEquals(actualStatusCode, expectedStatusCode, "Actual status code - " + actualStatusCode);
    }

    @And("{method} response body is equal to expected")
    public void verifyContactResponseBody(Method method, DataTable table) {
        var expectedContactBody = DtoUtils.createContactDto(table.asMap());
        ContactDto actualContactBody = null;
        switch (method) {
            case GET -> actualContactBody = getContactResponse.as(ContactDto.class);
            case PUT -> actualContactBody = putContactResponse.as(ContactDto.class);
            case POST -> actualContactBody = postContactResponse.as(ContactDto.class);
            case PATCH -> actualContactBody = patchContactResponse.as(ContactDto.class);
            case DELETE -> actualContactBody = deleteContactResponse.as(ContactDto.class);
        }
        assertEquals(actualContactBody, expectedContactBody, "Actual response body is different");
    }

    @When("PATCH request is sent to partially update contact with id {string}")
    public void patchContact(String contactId, DataTable table) {
        var data = table.asMap();
        var patchDto = new ContactDto();
        patchDto.setCountry(data.get("Country"));
        patchDto.setCity(data.get("City"));
        patchContactResponse = getContactApiSteps().patchContact(contactId, token, patchDto);
    }

    @When("PUT request is sent to update contact with id {string}")
    public void putContact(String contactId, DataTable table) {
        var contactBody = DtoUtils.createContactDto(table.asMap());
        putContactResponse = getContactApiSteps().putContact(contactId, token, contactBody);
    }

    @When("POST request is sent to create a new contact from JSON file {string}")
    public void postContactFromJSON(String fileName) {
        deserializedFromJson = JsonUtils.deserializeFromJsonFile(fileName, ContactDto.class);
        postContactResponse = getContactApiSteps().postContact(token, deserializedFromJson);
    }

    @And("POST response body is equal to the contact from JSON file")
    public void verifyPostResponseBodyIsEqualToJSONContact() {
        assertEquals(postContactResponse.as(ContactDto.class), deserializedFromJson,
                "Actual response body is different from JSON");
    }

    @When("DELETE request is sent to delete created contact")
    public void deleteContact() {
        var id = postContactResponse.as(ContactDto.class).get_id();
        deleteContactResponse = getContactApiSteps().deleteContact(id, token);
    }
}
