package com.qris_payment.api.transaction_log.common;

import com.qris_payment.api.common.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TransactionLog extends BaseModel {

    private UUID id;
    private String transactionId;
    private String action;
    private String category;
    private String note;
    private Object header;
    private Object body;
    private Object response;
}
