package com.qris_payment.api.store.domain.repository;

import com.qris_payment.api.payment.domain.model.PaymentEntity;
import com.qris_payment.api.store.domain.model.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {
}
