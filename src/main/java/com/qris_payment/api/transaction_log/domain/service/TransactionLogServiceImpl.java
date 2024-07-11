package com.qris_payment.api.transaction_log.domain.service;

import com.qris_payment.api.transaction_log.domain.model.LogAction;
import com.qris_payment.api.transaction_log.common.TransactionLogMapper;
import com.qris_payment.api.transaction_log.domain.model.LogCategory;
import com.qris_payment.api.transaction_log.domain.model.TransactionLogEntity;
import com.qris_payment.api.transaction_log.domain.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {

    private final TransactionLogRepository transactionLogRepository;
    private final TransactionLogMapper mapper = new TransactionLogMapper();

    @Autowired
    public TransactionLogServiceImpl(TransactionLogRepository transactionLogRepository) {
        this.transactionLogRepository = transactionLogRepository;
    }

    public void createLog(String trxId, LogAction action, LogCategory category, String note, Object header, Object body, Object response, UUID userId, Timestamp timestamp) {
        TransactionLogEntity entity = mapper.mapToEntity(trxId, action, category, note, header, body, response, userId, timestamp);
        transactionLogRepository.save(entity);
    }
}
