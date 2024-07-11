package com.qris_payment.api.transaction_log.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qris_payment.api.transaction_log.domain.model.LogAction;
import com.qris_payment.api.transaction_log.domain.model.LogCategory;
import com.qris_payment.api.transaction_log.domain.model.TransactionLogEntity;

import java.sql.Timestamp;
import java.util.UUID;

public class TransactionLogMapper {

    public TransactionLogEntity mapToEntity(String trxId, LogAction action, LogCategory category, String note, Object header, Object body, Object response, UUID userId, Timestamp timestamp) {
        TransactionLogEntity transactionLogEntity = new TransactionLogEntity();
        transactionLogEntity.setId(UUID.randomUUID());
        transactionLogEntity.setTransactionId(trxId);
        transactionLogEntity.setAction(action.getActionName());
        transactionLogEntity.setCategory(category.name());
        transactionLogEntity.setNote(note);

        ObjectMapper headerObjectMapper = new ObjectMapper();
        if (header != null){
            try {
                transactionLogEntity.setHeader(headerObjectMapper.writeValueAsString(header));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        ObjectMapper bodyObjectMapper = new ObjectMapper();
        if (header != null){
            try {
                transactionLogEntity.setBody(bodyObjectMapper.writeValueAsString(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        ObjectMapper responseObjectMapper = new ObjectMapper();
        if (header != null){
            try {
                transactionLogEntity.setResponse(responseObjectMapper.writeValueAsString(response));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        transactionLogEntity.setCreatedAt(timestamp);
        transactionLogEntity.setCreatedBy(userId);
        return transactionLogEntity;
    }
}