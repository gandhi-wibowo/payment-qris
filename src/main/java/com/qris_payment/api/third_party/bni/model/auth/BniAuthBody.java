package com.qris_payment.api.third_party.bni.model.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BniAuthBody {
    private String grantType = "client_credentials";
}
