package com.qris_payment.api.third_party.common;

import feign.Response;
import feign.codec.ErrorDecoder;

public class BniErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
//        if (response.status() == 404) {
//            return new ResourceNotFoundException("Resource not found");
//        }
        // Handle other status codes as needed
        return new Exception("Generic error occurred");
    }
}
