package com.qris_payment.api.transaction.domain.service;

import com.qris_payment.api.transaction.common.Transaction;

public interface TransactionService {
    void create(Transaction transaction);
}
