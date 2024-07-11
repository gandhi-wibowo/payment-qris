package com.qris_payment.api.transaction.domain.repository;

import com.qris_payment.api.transaction.domain.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
}
