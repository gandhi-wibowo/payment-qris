package com.qris_payment.api.third_party.bni.repository;


import com.qris_payment.api.third_party.bni.model.auth.BniAuthBody;
import com.qris_payment.api.third_party.bni.model.auth.BniAuthResponse;
import com.qris_payment.api.third_party.bni.model.inquiry.BniInquiryBody;
import com.qris_payment.api.third_party.bni.model.inquiry.BniInquiryResponse;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisRequest;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisResponse;
import com.qris_payment.api.third_party.common.BniClientConfiguration;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(
        value = "bniRepository",
        url = "https://mom-trxauth.spesandbox.com",
        configuration = BniClientConfiguration.class
)
@Headers({"Content-Type: application/json"})
public interface BniRepository {

    @RequestMapping(method = RequestMethod.POST, value = "/apisnap/access-token/b2b")
    ResponseEntity<BniAuthResponse> auth(@RequestHeader Map<String, String> headers, @RequestBody BniAuthBody bniAuthBody);

    @RequestMapping(method = RequestMethod.POST, value = "/apisnap/qr/qr-mpm-generate")
    ResponseEntity<BniQrisResponse> generateQr(@RequestHeader Map<String, String> headers, @RequestBody BniQrisRequest bniQrisBody);

    @RequestMapping(method = RequestMethod.POST, value = "/apisnap/qr/qr-mpm-query")
    ResponseEntity<BniInquiryResponse> inquiry(@RequestHeader Map<String, String> headers, @RequestBody BniInquiryBody bniInquiryBody);
}
