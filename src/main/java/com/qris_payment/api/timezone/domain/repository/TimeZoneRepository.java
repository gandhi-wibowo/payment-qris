package com.qris_payment.api.timezone.domain.repository;

import com.qris_payment.api.timezone.domain.model.TimeZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimeZoneRepository extends JpaRepository<TimeZoneEntity, UUID> {
}
