package com.qris_payment.api.transaction.domain.model;

import com.qris_payment.api.common.BaseEntity;
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
@Table(name = "transactions")
public class TransactionEntity  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "store_id", nullable = false)
    private UUID storeId;

    @Column(name = "payment_id", nullable = false)
    private UUID paymentId;

    @Column(name = "reference_no",length = 50)
    private String referenceNo;

    @Column(name = "partner_reference_no",length = 50)
    private String partnerReferenceNo;

    @Column(name = "terminal_code",length = 25)
    private String terminalCode;

    @Column(name = "cashier_code",length = 25)
    private String cashierCode;

    @Column(name = "receipt_no",length = 20)
    private String receiptNo;

    @Column(name = "status",length = 10,nullable = false)
    private String status;

    @Column(name = "status_reason")
    private String statusReason;
}