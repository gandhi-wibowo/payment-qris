package com.qris_payment.api.third_party.common;

import com.qris_payment.api.third_party.common.exception.BadRequestException;
import com.qris_payment.api.third_party.common.exception.InternalServerErrorException;
import com.qris_payment.api.third_party.common.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException("Bad Request");
            case 404 -> new NotFoundException("Not Found");
            case 500 -> new InternalServerErrorException("Internal Server Error");
            default -> new Exception("Generic Error");
        };
    }
}
