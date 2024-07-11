package com.qris_payment.api.payment.domain.repository;

import com.qris_payment.api.payment.domain.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
