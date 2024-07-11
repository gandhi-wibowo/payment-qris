package com.qris_payment.api.third_party.common.exception;

public class BadRequestException  extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
