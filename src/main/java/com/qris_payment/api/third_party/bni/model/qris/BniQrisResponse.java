package com.qris_payment.api.third_party.bni.model.qris;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qris_payment.api.third_party.bni.model.BniAdditionalInfoResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BniQrisResponse {
    private String responseCode;
    private String responseMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String referenceNo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String partnerReferenceNo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String qrContent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String merchantName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BniAdditionalInfoResponse additionalInfo;
}