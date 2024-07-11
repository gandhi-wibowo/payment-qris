package com.qris_payment.api.third_party.bni.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qris_payment.api.common.CommonSignature;
import com.qris_payment.api.common.Diggest;
import com.qris_payment.api.common.ZoneDateTimeHelper;
import com.qris_payment.api.third_party.bni.common.BniApiSignature;
import com.qris_payment.api.third_party.bni.common.BniRequestHeader;
import com.qris_payment.api.third_party.bni.model.auth.BniAuthBody;
import com.qris_payment.api.third_party.bni.model.auth.BniAuthResponse;
import com.qris_payment.api.third_party.bni.model.inquiry.BniInquiryBody;
import com.qris_payment.api.third_party.bni.model.inquiry.BniInquiryResponse;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisRequest;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisResponse;
import com.qris_payment.api.third_party.bni.repository.BniRepository;
import com.qris_payment.api.transaction_log.domain.model.LogAction;
import com.qris_payment.api.transaction_log.domain.model.LogActionType;
import com.qris_payment.api.transaction_log.domain.model.LogCategory;
import com.qris_payment.api.transaction_log.domain.service.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class BniServiceImpl implements BniService {

    @Autowired
    private BniRepository bniRepository;

    @Autowired
    private TransactionLogService transactionLogService;


    @Override
    public ResponseEntity<BniAuthResponse> auth(String clientId, String rsaSecret, ZoneDateTimeHelper zoneDateTimeHelper, UUID storeId) {

        String xTimeStamp = zoneDateTimeHelper.toString();
        Timestamp timestamp = zoneDateTimeHelper.toTimeStamp();
        // BODY
        BniAuthBody body = new BniAuthBody();

        // PAYLOAD TO BE SIGNATURE
        String payload = clientId + "|" + xTimeStamp;
        String signature;
        try {
            signature = BniApiSignature.generateAuthSignature(rsaSecret, payload.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // HEADERS
        Map<String, String> headers = BniRequestHeader.auth(xTimeStamp, signature, clientId);

        // CALL API
        ResponseEntity<BniAuthResponse> response;
        try {
            response = bniRepository.auth(headers, body);
            if (response.hasBody()) {
                if (Objects.equals(Objects.requireNonNull(response.getBody()).getResponseCode(), "20027300")) {
                    // SAVE to log success
                    LogAction logAction = new LogAction(LogActionType.AUTH, "BNI-SNAP");
                    transactionLogService.createLog(
                            null,
                            logAction,
                            LogCategory.SUCCESS,
                            null,
                            headers,
                            body,
                            response.getBody(),
                            storeId,
                            timestamp);
                } else {
                    // SAVE to log error
                    LogAction logAction = new LogAction(LogActionType.AUTH, "BNI-SNAP");
                    transactionLogService.createLog(
                            null,
                            logAction,
                            LogCategory.ERROR,
                            response.getBody().getResponseMessage(),
                            headers,
                            body,
                            response.getBody(),
                            storeId,
                            timestamp);
                }
            }
            return response;

        } catch (Exception e) {
            // SAVE to log
            LogAction logAction = new LogAction(LogActionType.AUTH, "BNI-SNAP");
            transactionLogService.createLog(
                    null,
                    logAction,
                    LogCategory.SUCCESS,
                    e.getMessage(),
                    headers,
                    body,
                    null,
                    storeId,
                    timestamp);

            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<BniQrisResponse> generateQr(String clientId, String clientSecret, String channelId, String token, ZoneDateTimeHelper zoneDateTimeHelper, UUID storeId, BniQrisRequest body) {
        String method = "POST";
        String url = "https://mom-trxauth.spesandbox.com/apisnap/qr/qr-mpm-generate";
        // timestamp
        String xTimeStamp = zoneDateTimeHelper.toString();
        Timestamp timestamp = zoneDateTimeHelper.toTimeStamp();
        String externalId = zoneDateTimeHelper.getZoneDateTimeStr();

        // convert body to json
        ObjectMapper qrisBodyObjectMapper = new ObjectMapper();
        String strBodyJson = "";
        try {
            strBodyJson = qrisBodyObjectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // hash body json to sha256
        String sha256Body;
        try {
            sha256Body = Diggest.sha256(strBodyJson.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // build payload
        String payload = method + ":" + url + ":" + token + ":" + sha256Body.toLowerCase() + ":" + xTimeStamp;
        // generate signature
        String signature;
        try {
            signature = CommonSignature.hmacSha512Base64(clientSecret, payload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<String, String> headers = BniRequestHeader.transaction(xTimeStamp, signature, clientId, token, body.getTerminalId(), externalId, channelId);
        // CALL API
        ResponseEntity<BniQrisResponse> response;
        try {
            response = bniRepository.generateQr(headers, body);
            if (response.hasBody()) {
                if (Objects.equals(Objects.requireNonNull(response.getBody()).getResponseCode(), "20024700")) {
                    // SAVE to log success
                    LogAction logAction = new LogAction(LogActionType.GENERATE_QR, "BNI-SNAP");
                    transactionLogService.createLog(
                            body.getPartnerReferenceNo(),
                            logAction,
                            LogCategory.SUCCESS,
                            null,
                            headers,
                            body,
                            response.getBody(),
                            storeId,
                            timestamp);
                } else {
                    // SAVE to log error
                    LogAction logAction = new LogAction(LogActionType.GENERATE_QR, "BNI-SNAP");
                    transactionLogService.createLog(
                            body.getPartnerReferenceNo(),
                            logAction,
                            LogCategory.ERROR,
                            response.getBody().getResponseMessage(),
                            headers,
                            body,
                            response.getBody(),
                            storeId,
                            timestamp);
                }
            }
            return response;

        } catch (Exception e) {
            // SAVE to log
            LogAction logAction = new LogAction(LogActionType.GENERATE_QR, "BNI-SNAP");
            transactionLogService.createLog(
                    body.getPartnerReferenceNo(),
                    logAction,
                    LogCategory.SUCCESS,
                    e.getMessage(),
                    headers,
                    body,
                    null,
                    storeId,
                    timestamp);

            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<BniInquiryResponse> inquiry(Map<String, String> headers, BniInquiryBody bniInquiryBody) {
        return bniRepository.inquiry(headers, bniInquiryBody);
    }
}
