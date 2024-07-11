package com.qris_payment.api.third_party.bni.common;

import java.util.HashMap;
import java.util.Map;

public class BniRequestHeader {

    public static Map<String, String> auth(String timeStamp, String signature, String clientKey) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-TIMESTAMP", timeStamp);
        headers.put("X-SIGNATURE", signature);
        headers.put("X-CLIENT-KEY", clientKey);
        return headers;
    }

    public static Map<String, String> transaction(
            String timeStamp,
            String signature,
            String partnerId,
            String token,
            String terminalId,
            String externalId,
            String channelId
    ) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-TIMESTAMP", timeStamp);
        headers.put("X-SIGNATURE", signature);
        headers.put("Authorization", "Bearer " + token);
        headers.put("X-PARTNER-ID", partnerId);
        headers.put("X-DEVICE-ID", terminalId);
        headers.put("X-EXTERNAL-ID", externalId);
        headers.put("CHANNEL-ID", channelId);
        return headers;
    }
}