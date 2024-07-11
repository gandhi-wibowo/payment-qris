package com.qris_payment.api.third_party.bni.model.qris;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qris_payment.api.common.Amount;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BniQrisRequest {
    private String partnerReferenceNo;
    private Amount amount;
    private String merchantId;
    private String terminalId;
    private String validityPeriod;
    private ObjectNode additionalInfo;
}
