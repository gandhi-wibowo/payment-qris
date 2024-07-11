package com.qris_payment.api.transaction_log.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LogAction {
    private String actionName;

    public LogAction(LogActionType type, String name) {
        this.actionName = type.name() + " " + name;
    }
}
