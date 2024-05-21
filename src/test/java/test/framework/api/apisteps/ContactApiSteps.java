package test.framework.api.apisteps;

import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import test.framework.api.models.ContactDto;

@Log4j2 @NoArgsConstructor
public class ContactApiSteps extends BaseApiSteps {
    private static ContactApiSteps instance;

    public static ContactApiSteps getContactApiSteps() {
        if (instance == null) {
            instance = new ContactApiSteps();
        }
        return instance;
    }

    public Response getContact(String contactId, String token) {
        log.info("Sending GET request to retrieve contact data with id: " + contactId);
        return getContactApiClient(token).getContactById(contactId);
    }

    public Response patchContact(String contactId, String token, ContactDto contactDto) {
        log.info("Sending PATCH request to partially update contact with id: " + contactId);
        return getContactApiClient(token).patchContactById(contactDto, contactId);
    }

    public Response putContact(String contactId, String token, ContactDto contactDto) {
        log.info("Sending PUT request to update contact with id: " + contactId);
        return getContactApiClient(token).putContactById(contactDto, contactId);
    }
}
