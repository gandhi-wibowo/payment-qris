package com.qris_payment.api.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Amount {
    private String value;
    private String currency;
}