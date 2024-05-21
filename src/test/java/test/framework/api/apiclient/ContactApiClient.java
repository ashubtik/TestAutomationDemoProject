package test.framework.api.apiclient;

import io.restassured.response.Response;
import test.framework.api.authorization.Authorization;
import test.framework.api.models.ContactDto;

public class ContactApiClient extends BaseApiClient {
    private static final String CONTACTS_ENDPOINT = "contacts/{id}";

    public ContactApiClient(Authorization authorization) {
        super(authorization);
    }

    public Response getContactById(String id) {
        return get(CONTACTS_ENDPOINT, id);
    }

    public Response patchContactById(ContactDto contact, String id) {
        return patch(contact, CONTACTS_ENDPOINT, id);
    }
}
