package com.qris_payment.api.transaction_log.domain.service;

import com.qris_payment.api.transaction_log.domain.model.LogAction;
import com.qris_payment.api.transaction_log.domain.model.LogCategory;

import java.sql.Timestamp;
import java.util.UUID;

public interface TransactionLogService {
    void createLog(String trxId, LogAction action, LogCategory category, String note, Object header, Object body, Object response, UUID userId, Timestamp timestamp);
}
