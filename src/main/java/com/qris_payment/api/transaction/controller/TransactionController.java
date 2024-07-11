package com.qris_payment.api.transaction.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qris_payment.api.common.Amount;
import com.qris_payment.api.common.ZoneDateTimeHelper;
import com.qris_payment.api.third_party.bni.TransactionBni;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisRequest;
import com.qris_payment.api.third_party.bni.model.qris.BniQrisResponse;
import com.qris_payment.api.third_party.bni.service.BniService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.UUID;

@RestController
public class TransactionController {
    private String base64Rsa = "LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tDQpNSUlFdmdJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLZ3dnZ1NrQWdFQUFvSUJBUUNuN0kxQlhpckM3VEdqDQpwQjMwZTFzbE1oV2VJNWExdVdzM2JaTkkrUzI4cDV5VHVoU3p2UURyYnlzdGt1TFRNSHdzNFpLL1BSSTR2VDZFDQpIOWFoSk1mWVNZVjAxaGdDck5kZVlNcHVrajc0NnNTd2xSazFTWi9sNHNQY3FFejhXMjcxdnI3U0tiQkhrczRZDQo3NWtPeDRpSVMwSzNMR3Q0amFnNEUxU253aU1VYnhhWEFvS05KT1VlSTF2eEx2c0hoNnNsUjdjVFRJSUIzUGpnDQpXd0tQVzBnYzg2dXA1U0pEb0Z1WWtVMlFCMWZGcnpxSkZiMW12eklGUTMvOHZKemFQOUtSVWNPblpKbG1XaTVIDQpJUzhmYXRXTUEweERTSjNwOFVvcndPYm9idTM3LzkrRUxoa0xqMC90OUlwT3pUcXVOdll5SmZVTTY0bkx0aXIyDQowbTRUQVlpM0FnTUJBQUVDZ2dFQVN4bXlPdS9nYy9rYzREQy83TjFXY0JZc01PTjl2WnhGWlM2dVZrNktzL3JLDQpQZzlVdnVYaWlXallpTE5PbW1EdXlDdkNNNkVlaXgybjNmTlhUQTVsQ2tnblZZK0NnWC9IZWtBVGlNd0RPZFBWDQpUNXYvQUNRRFRRN1R4ZFhIU0gyajdHZjRjNG1OZXhVZTgyd0VxZVYzSEcxOFV1dWN6UGVkcWlhTEQ5UDFXR2QwDQp6cmJySHVGaDJzVzUybXNzaHlNcUF1dmxuTDNYM3lIVnZVa1F5WEhBTjl4a2VCWS92QkxPYkJDQ2c3eTNkU2dLDQo3UFlaNTJGRXpNYlYyUWNFRmZnQWhpMlROMFdGcWtIKzBFZWJaUy94OTU1QVozRTJWTVI2cUxMSThLWUFjU1ZNDQo4OGZDdkxzemJoYmJ4YXpRekYxbDhoZE9vZWMwbkk4OUNaT0llMlRJOFFLQmdRRFZBc3RvaVdDTDd3cTRqY2cvDQpOanVVdkkyZmd0VFNodXh5WHllR2lvSm52QWh6T0gzTWQyb0x0QzZEM1NnMExSYjRUU2ROblZZSk1QYzNJSjRRDQpSOVVvdjhDam5VRjFLYzhDL3pOY0lKWGZsejBOSkNxS0dQRHhoblpVZjV4TWxZLzdGMWg1RWNFSTJQY2RyeGZGDQpYTloweTdUOVljQUdyTmUxQ25qZnp6NjVWUUtCZ1FESjBGZ1E1ZC9FZE5FREhUUkNVbVJGYzJzYzBSYUp3ZUdtDQo4NFpvRU1YKy9KZWRGNG9yTlBLOVpPa1pBTmNLYk5jMjJuZExXaVRmc0VPSEIvNFZRUDdZQldVWHJxcURoMTBxDQpVVnU0azRMMExaMWpQN3dDOWFTZjN5YlJNaXhiT0NwWmZUc3FHQzhXRzArZFVqc3MwRVFaWE5LY0ZxZWFmdE1PDQpxUUVHY0lFSjJ3S0JnQkRiNXNPQVUvN2ZUWWJyMmRSKzgxcERUaVk0MGFWVC9uVFM2U3J4M1g1ZVdJRGVFTDdWDQpHTFNTYnpnS2tmc0hNcEUyY2d6bmpmNnVQSExGaHgwY3dsYzJUZ1hsRldBemplbmJ6dkhVdG1mNjdWOFQ1TFlIDQpZVktNWk9NdE1tNnhTN01BMXdUR0RmUW9UKzZ1aEFBZjUrMVlqaHViMS9udlJpZFRDOXdTNkJEOUFvR0JBTGFuDQpaWDF0d0RmN2preG1PeTBuaXU4OHhoNGZzMmxjMjlHUnFQMDQ2U3FlRE02MlJteFNrTHdQdjlUTWFOUmJxL0ZPDQppeGZjNzNDeEJIMExJOUN5c2JSc05aSFltU1ZQOTJkY21Vb1Y0RGtGcStQdkJFZ2RjVERzOUNIMGFpYnBUNU1NDQovK1phV3d1RURtdVJ5Rk1IOEFxUWMyR29NMnRtQzduQ0JsYUFycGx2QW9HQkFJUDE2TEc0cUxLVlp3NnpDcGJJDQpuTkVSeHEyQ2lXbHJPaXNxV1Q3TjNZWmxkY2k5OU1KZDhiTlJ1MzBjdGF0bFY4OVpaU3ByYTlFUExDRkFFQkd5DQpPR0tZcGhLUVNYT1VIMC9BU2JFbTFENExvNWlIMDFocUVqTTFVWngvS1VrV3gyK3MxZkM1TXdOQk1yVmlDZjlyDQo5OTVoWWdTTmtISDlIWDFnZk80ZkZwZmYNCi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0NCg==";
    private String storeZoneId = "Asia/Jakarta";
    private String terminalId = "112";
    private String channelId = "112";
    private String merchantId = "600000089";
    private String clientSecret = "tfn6YsLn8O";
    private String clientId = "112";
    private UUID storeId = UUID.randomUUID();
    @Autowired
    private BniService bniService;
    
    @Autowired
    private TransactionBni transactionBni;

    @GetMapping("/bni")
    private ResponseEntity<BniQrisResponse> generateQr() {
        // decrypt rsa base 64
        byte[] rawRsaKeyBase64 = Base64.getDecoder().decode(base64Rsa);
        String rsa = new String(rawRsaKeyBase64);
        
        ZoneDateTimeHelper zoneDateTimeHelper = new ZoneDateTimeHelper
                .Builder()
                .setZoneId(storeZoneId)
                .setPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
                .build();

        BniQrisRequest bniQrisRequest = getBniQrisRequest(zoneDateTimeHelper);

        ResponseEntity<BniQrisResponse> qrisResponse = transactionBni.generateQr(clientId,clientSecret,rsa,channelId,zoneDateTimeHelper,storeId,bniQrisRequest);

        return qrisResponse;
    }

    private @NotNull BniQrisRequest getBniQrisRequest(ZoneDateTimeHelper zoneDateTimeHelper) {
        String xTimeStamp = zoneDateTimeHelper.getZoneDateTimeStr();
        String timeInEpochMilli = zoneDateTimeHelper.getZoneDateTimeStr();

        BniQrisRequest bniQrisRequest = new BniQrisRequest();
        // trx id
        bniQrisRequest.setPartnerReferenceNo(timeInEpochMilli);
        // amount
        Amount bniTrxAmount = new Amount();
        bniTrxAmount.setCurrency("IDR");
        bniTrxAmount.setValue("100000.00");
        bniQrisRequest.setAmount(bniTrxAmount);
        // terminal id
        bniQrisRequest.setTerminalId(terminalId);
        // merchant id
        bniQrisRequest.setMerchantId(merchantId);
        // period
        bniQrisRequest.setValidityPeriod(xTimeStamp); // should be added for some minute
        // additional info
        ObjectMapper emptyObjectMapper = new ObjectMapper();
        bniQrisRequest.setAdditionalInfo(emptyObjectMapper.createObjectNode());
        return bniQrisRequest;
    }
}
