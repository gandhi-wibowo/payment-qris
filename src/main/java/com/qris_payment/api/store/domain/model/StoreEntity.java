package com.qris_payment.api.store.domain.model;

import com.qris_payment.api.common.BaseEntity;
import com.qris_payment.api.timezone.domain.model.TimeZoneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stores")
public class StoreEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "code", length = 25)
    private String code;

    @OneToOne(targetEntity = TimeZoneEntity.class)
    private TimeZoneEntity timeZone;
}
