package com.qris_payment.api.transaction.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qris_payment.api.transaction.domain.model.TransactionEntity;

public class TransactionMapper {

    public TransactionEntity map(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transaction.getId());
        transactionEntity.setStoreId(transaction.getStoreId());
        transactionEntity.setPaymentId(transaction.getPaymentId());
        transactionEntity.setReferenceNo(transaction.getReferenceNo());
        transactionEntity.setPartnerReferenceNo(transaction.getPartnerReferenceNo());
        transactionEntity.setTerminalCode(transaction.getTerminalCode());
        transactionEntity.setCashierCode(transaction.getCashierCode());
        transactionEntity.setReceiptNo(transaction.getReceiptNo());
        transactionEntity.setStatus(transaction.getStatus());
        transactionEntity.setStatusReason(transaction.getStatusReason());
        transactionEntity.setCreatedAt(transaction.getCreatedAt());
        transactionEntity.setCreatedBy(transaction.getCreatedBy());
        transactionEntity.setUpdatedAt(transaction.getUpdatedAt());
        transactionEntity.setUpdatedBy(transaction.getUpdatedBy());
        return transactionEntity;
    }

    public String map(TransactionEntity transactionEntity) {
        try {
            return new ObjectMapper().writeValueAsString(transactionEntity);
        } catch (JsonProcessingException ex) {
            return ex.getMessage();
        }
    }
}