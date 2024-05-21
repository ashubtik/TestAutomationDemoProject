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
            case PUT -> actualStatusCode = 1;
            case POST -> actualStatusCode = patchContactResponse.getStatusCode();
            case PATCH -> actualStatusCode = 2;
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
            case PUT -> {}
            case POST -> actualContactBody = patchContactResponse.as(ContactDto.class);
            case PATCH -> {}
            case DELETE -> {}
        }
        assertEquals(actualContactBody, expectedContactBody, "Actual response body is different");
    }

    private ContactDto createContactDto(Map<String, String> contactData) {
        return ContactDto.builder()
                .firstName(contactData.get("First Name"))
                .lastName(contactData.get("Last Name"))
                .birthdate(contactData.get("Date of Birth"))
                .email(contactData.get("Email"))
                .phone(contactData.get("Phone"))
                .street1(contactData.get("Street Address 1"))
                .street2(contactData.get("Street Address 2"))
                .city(contactData.get("City"))
                .stateProvince(contactData.get("State or Province"))
                .postalCode(contactData.get("Postal Code"))
                .country(contactData.get("Country"))
                .build();
    }

    @When("PATCH request is sent to partially update contact with id {string}")
    public void patchContact(String contactId, DataTable table) {
        var data = table.asMap();
        var patchDto = ContactDto.builder()
                .country(data.get("Country"))
                .city(data.get("City"))
                .build();
        patchContactResponse = getContactApiSteps().patchContact(contactId, token, patchDto);
    }

    @When("PUT request is sent to update contact with id {string}")
    public void putContactWithIdEfFEFEb(Method method, String contactId) {
    }
}
