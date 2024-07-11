package com.qris_payment.api.common;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CommonSignature {

    public static byte[] hmacSha512(String key, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        String algorithm = "HmacSHA512";
        Mac mac = Mac.getInstance(algorithm);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm);
        mac.init(secretKey);
        return mac.doFinal(message.getBytes());
    }

    public static String hmacSha512Base64(String key, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] hmacSha512Bytes = hmacSha512(key, message);
        return Base64.getEncoder().encodeToString(hmacSha512Bytes);
    }
}
