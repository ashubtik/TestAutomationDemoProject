package test.framework.api.apisteps;

import test.framework.api.apiclient.ContactApiClient;
import test.framework.api.authorization.Authorization;

public class BaseApiSteps {

    protected ContactApiClient getContactApiClient(String bearerToken) {
        return new ContactApiClient(new Authorization(bearerToken));
    }
}
