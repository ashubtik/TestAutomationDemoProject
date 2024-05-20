package test.framework.api.apisteps;

import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2 @NoArgsConstructor
public class ContactApiSteps extends BaseApiSteps {
    private static ContactApiSteps instance;

    public static ContactApiSteps getContactApiSteps() {
        if (instance == null) {
            instance = new ContactApiSteps();
        }
        return instance;
    }

    public Response getContactResponse(String contactId, String token) {
        log.info("Sending GET request to retrieve contact data by id: " + contactId);
        return getContactApiClient(token).getContactById(contactId);
    }
}
