package com.qris_payment.api.third_party.bni.service;

import com.qris_payment.api.common.ZoneDateTimeHelper;
import com.qris_payment.api.third_party.bni.model.auth.BniAuthBody;
import com.qris_payment.api.third_party.bni.model.auth.BniAuthResponse;
import com.qris_payment.api.third_party.bni.model.inquiry.BniInquiryBody;
import com.qris_payment.api.third_party.bni.model.inquiry.BniInquiryResponse;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisRequest;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface BniService {
    ResponseEntity<BniAuthResponse> auth(String clientId, String rsaSecret, ZoneDateTimeHelper zoneDateTimeHelper, UUID storeId);

    ResponseEntity<BniQrisResponse> generateQr(String clientId, String clientSecret, String channelId, String token, ZoneDateTimeHelper zoneDateTimeHelper, UUID storeId, BniQrisRequest body);

    ResponseEntity<BniInquiryResponse> inquiry(Map<String, String> headers, BniInquiryBody bniInquiryBody);

}
