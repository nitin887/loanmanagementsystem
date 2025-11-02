package com.onlinebankingloanmanagement.loanmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * DTO for the response of a successful authentication, containing the JWT.
 */
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}