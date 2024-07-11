package com.qris_payment.api.third_party.bni;

import com.qris_payment.api.common.ZoneDateTimeHelper;
import com.qris_payment.api.third_party.bni.model.auth.BniAuthResponse;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisRequest;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisResponse;
import com.qris_payment.api.third_party.bni.service.BniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class TransactionBniIpl implements TransactionBni {
    @Autowired
    private BniService bniService;

    @Override
    public ResponseEntity<BniQrisResponse> generateQr(String clientId, String clientSecret, String rsaSecret, String channelId, ZoneDateTimeHelper zoneDateTimeHelper, UUID storeId, BniQrisRequest body) {
        ResponseEntity<BniAuthResponse> bniAuthResponseResponseEntity;
        try {
            bniAuthResponseResponseEntity = bniService.auth(clientId,rsaSecret,zoneDateTimeHelper,storeId);
        }catch (Exception e){
            BniQrisResponse bniQrisResponse = new BniQrisResponse();
            bniQrisResponse.setResponseCode("400");
            bniQrisResponse.setResponseMessage(e.getMessage());
            ResponseEntity<BniQrisResponse> errorAuthResponse ;
            errorAuthResponse = new ResponseEntity<>(bniQrisResponse,HttpStatus.BAD_REQUEST);
            return  errorAuthResponse;
        }
        if (!Objects.equals(bniAuthResponseResponseEntity.getBody().getResponseCode(), "20027300")){
            BniQrisResponse bniQrisResponse = new BniQrisResponse();
            bniQrisResponse.setResponseCode(bniAuthResponseResponseEntity.getBody().getResponseCode());
            bniQrisResponse.setResponseMessage(bniAuthResponseResponseEntity.getBody().getResponseMessage());
            ResponseEntity<BniQrisResponse> errorAuthResponse ;
            errorAuthResponse = new ResponseEntity<>(bniQrisResponse,HttpStatus.BAD_REQUEST);
            return  errorAuthResponse;
        }

        ResponseEntity<BniQrisResponse> bniQrisResponseResponseEntity;
        try {
            bniQrisResponseResponseEntity = bniService.generateQr(clientId,clientSecret,channelId,bniAuthResponseResponseEntity.getBody().getAccessToken(),zoneDateTimeHelper,storeId,body);
        } catch (Exception e){
            BniQrisResponse bniQrisResponse = new BniQrisResponse();
            bniQrisResponse.setResponseCode("400");
            bniQrisResponse.setResponseMessage(e.getMessage());
            ResponseEntity<BniQrisResponse> errorAuthResponse ;
            errorAuthResponse = new ResponseEntity<>(bniQrisResponse,HttpStatus.BAD_REQUEST);
            return  errorAuthResponse;
        }
        return bniQrisResponseResponseEntity;
    }
}
