package test.framework.api.apiclient;

import io.restassured.response.Response;
import test.framework.api.authorization.Authorization;
import test.framework.api.models.ContactDto;

public class ContactApiClient extends BaseApiClient {
    private static final String CONTACT_ID_ENDPOINT = "contacts/{id}";
    private static final String CONTACTS_ENDPOINT = "contacts/";

    public ContactApiClient(Authorization authorization) {
        super(authorization);
    }

    public Response getContactById(String id) {
        return get(CONTACT_ID_ENDPOINT, id);
    }

    public Response patchContactById(ContactDto contactDto, String id) {
        return patch(contactDto, CONTACT_ID_ENDPOINT, id);
    }

    public Response putContactById(ContactDto contactDto, String id) {
        return put(contactDto, CONTACT_ID_ENDPOINT, id);
    }

    public Response postContact(ContactDto contactBody) {
        return post(CONTACTS_ENDPOINT, contactBody);
    }

    public Response deleteContactById(String id) {
        return delete(CONTACT_ID_ENDPOINT, id);
    }
}
