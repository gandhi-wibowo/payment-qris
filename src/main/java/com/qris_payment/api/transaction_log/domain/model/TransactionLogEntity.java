package com.qris_payment.api.transaction_log.domain.model;

import com.qris_payment.api.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction_logs")
public class TransactionLogEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "trx_id", length = 50)
    private String transactionId;

    @Column(name = "action", nullable = false, length = 50)
    private String action;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "note")
    private String note;

    @Column(name = "header_object")
    private String header;

    @Column(name = "body_object")
    private String body;

    @Column(name = "response_object")
    private String response;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private UUID createdBy;
//
//    @Column(name = "updated_at")
//    private Timestamp updatedAt;
//
//    @Column(name = "updated_by")
//    private UUID updatedBy;
}