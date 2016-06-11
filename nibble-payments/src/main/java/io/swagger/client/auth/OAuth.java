package io.swagger.client.auth;

import java.util.Map;

public class OAuth implements Authentication {

    private String accessToken;

    public void setAccessToken(String token) {
        this.accessToken = token;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void applyToParams(Map<String, String> queryParams, Map<String, String> headerParams) {
        headerParams.put("Authorization", "Bearer " + this.accessToken);
    }
}
