package com.qris_payment.api.transaction.common;

import com.qris_payment.api.common.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class Transaction extends BaseModel {
    private Long id;
    private UUID storeId;
    private UUID paymentId;
    private String referenceNo;
    private String partnerReferenceNo;
    private String terminalCode;
    private String cashierCode;
    private String receiptNo;
    private String status;
    private String statusReason;
}