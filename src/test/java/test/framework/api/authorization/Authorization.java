package test.framework.api.authorization;

public record Authorization(String bearerToken) {

    public Authorization(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}
