package com.qris_payment.api.transaction_log.domain.repository;

import com.qris_payment.api.transaction_log.domain.model.TransactionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionLogRepository extends JpaRepository<TransactionLogEntity, UUID> { }
