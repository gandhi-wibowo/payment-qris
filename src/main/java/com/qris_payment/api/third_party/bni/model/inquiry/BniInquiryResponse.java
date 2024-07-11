package com.qris_payment.api.third_party.bni.model.inquiry;

import com.qris_payment.api.common.Amount;
import com.qris_payment.api.third_party.bni.model.BniAdditionalInfoResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BniInquiryResponse {
    private String billNumber;
    private String responseCode;
    private String responseMessage;
    private String originalReferenceNo;
    private String originalPartnerReferenceNo;
    private String originalExternalId;
    private String serviceCode;
    private String latestTransactionStatus;
    private String transactionStatusDesc;
    private String paidTime;
    private Amount amount;
    private String terminalId;
    private BniAdditionalInfoResponse additionalInfo;
}