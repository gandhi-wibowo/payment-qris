package com.qris_payment.api.transaction.domain.service;

import com.qris_payment.api.transaction.common.Transaction;
import com.qris_payment.api.transaction.common.TransactionMapper;
import com.qris_payment.api.transaction.domain.model.TransactionEntity;
import com.qris_payment.api.transaction.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*save transaction into database*/
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper = new TransactionMapper();

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void create(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapper.map(transaction);
        transactionRepository.save(transactionEntity);
    }
}
