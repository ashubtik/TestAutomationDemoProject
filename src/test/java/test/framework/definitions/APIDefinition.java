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

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static test.framework.api.apisteps.ContactApiSteps.getContactApiSteps;

public class APIDefinition extends BasicSteps {
    private String token;
    private Response getContactResponse;
    private Response patchContactResponse;
    private Response putContactResponse;

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
        getContactResponse = getContactApiSteps().getContact(contactId, token);
    }

    @Then("{method} response has status code {int}")
    public void verifyContactResponseStatusCode(Method method, int expectedStatusCode) {
        int actualStatusCode = 0;
        switch (method) {
            case GET -> actualStatusCode = getContactResponse.getStatusCode();
            case PUT -> actualStatusCode = putContactResponse.getStatusCode();
            case POST -> actualStatusCode = 2;
            case PATCH -> actualStatusCode = patchContactResponse.getStatusCode();
            case DELETE -> actualStatusCode = 3;
        }
        assertEquals(actualStatusCode, expectedStatusCode, "Actual status code - " + actualStatusCode);
    }

    @And("{method} response body is equal to expected")
    public void verifyContactResponseBody(Method method, DataTable table) {
        var expectedContactBody = createContactDto(table.asMap());
        ContactDto actualContactBody = null;
        switch (method) {
            case GET -> actualContactBody = getContactResponse.as(ContactDto.class);
            case PUT -> actualContactBody = putContactResponse.as(ContactDto.class);
            case POST -> {}
            case PATCH -> actualContactBody = patchContactResponse.as(ContactDto.class);
            case DELETE -> {}
        }
        assertEquals(actualContactBody, expectedContactBody, "Actual response body is different");
    }

    private ContactDto createContactDto(Map<String, String> contactData) {
        var dto = new ContactDto();
        dto.setFirstName(contactData.get("First Name"));
        dto.setLastName(contactData.get("Last Name"));
        dto.setBirthdate(contactData.get("Date of Birth"));
        dto.setEmail(contactData.get("Email"));
        dto.setPhone(contactData.get("Phone"));
        dto.setStreet1(contactData.get("Street Address 1"));
        dto.setStreet2(contactData.get("Street Address 2"));
        dto.setCity(contactData.get("City"));
        dto.setStateProvince(contactData.get("State or Province"));
        dto.setPostalCode(contactData.get("Postal Code"));
        dto.setCountry(contactData.get("Country"));
        return dto;
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
        var contactBody = createContactDto(table.asMap());
        putContactResponse = getContactApiSteps().putContact(contactId, token, contactBody);
    }
}
