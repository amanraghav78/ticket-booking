package com.ticketbooking.system.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutheticationRequest {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonIgnore
    @JsonProperty("refresh_token")
    private String refreshToken;

    private Long id;
}
