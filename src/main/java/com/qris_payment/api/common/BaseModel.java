package com.qris_payment.api.common;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
public class BaseModel {
    private Timestamp createdAt;
    private UUID createdBy;
    private Timestamp updatedAt;
    private UUID updatedBy;
}
