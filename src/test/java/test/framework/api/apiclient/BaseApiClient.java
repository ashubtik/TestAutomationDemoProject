package test.framework.api.apiclient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import test.framework.api.authorization.Authorization;
import test.framework.config.Configuration;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApiClient {
    protected String baseUri;
    protected Authorization authorization;

    public BaseApiClient(Authorization authorization) {
        this.authorization = authorization;
        this.baseUri = Configuration.getConfig().getBaseUrl();
    }

    protected Response get(String endpoint, Object... pathParams) {
        return given()
                .spec(getRequestSpec())
                .when()
                .get(endpoint, pathParams)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    protected Response post(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    protected Response put(Object body, String endpoint, Object... pathParams) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .put(endpoint, pathParams)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    protected Response patch(Object body, String endpoint, Object... pathParams) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .patch(endpoint, pathParams)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    protected Response delete(String endpoint) {
        return given()
                .spec(getRequestSpec())
                .when()
                .delete(endpoint)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    protected RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setUrlEncodingEnabled(false)
                .addHeaders(getAuthorizationHeaders())
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .setRelaxedHTTPSValidation()
                .build();
    }

    protected ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    private Map<String, String> getAuthorizationHeaders() {
        return Map.of("Authorization", "Bearer ".concat(authorization.bearerToken()));
    }
}
