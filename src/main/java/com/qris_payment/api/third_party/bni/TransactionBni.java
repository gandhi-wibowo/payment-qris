package com.qris_payment.api.third_party.bni;

import com.qris_payment.api.common.ZoneDateTimeHelper;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisRequest;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TransactionBni {
    ResponseEntity<BniQrisResponse> generateQr(String clientId, String clientSecret,String rsaSecret, String channelId, ZoneDateTimeHelper zoneDateTimeHelper, UUID storeId, BniQrisRequest body);
}
