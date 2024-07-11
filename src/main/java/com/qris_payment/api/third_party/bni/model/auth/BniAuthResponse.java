package com.qris_payment.api.third_party.bni.model.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class BniAuthResponse {
    private String responseCode;
    private String responseMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tokenType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expiresIn;
}