package com.qris_payment.api.third_party.bni.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BniAdditionalInfoResponse {
    private String billNumber;
    private String amountMdr;
    private String approvalCode;
    private String customerPan;
    private String customerName;
    private String discount;
    private String merchantCategoryCode;
    private String merchantCity;
    private String merchantCountry;
    private String merchantCriteria;
    private String merchantName;
    private String merchantPan;
    private String processingCode;
    private String retrievalReferenceNumber;
    private String stan;
}