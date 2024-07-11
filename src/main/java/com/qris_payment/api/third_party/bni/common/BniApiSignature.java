package com.qris_payment.api.third_party.bni.common;


import com.qris_payment.api.common.RSAUtils;

import java.security.PrivateKey;
import java.util.Base64;

public class BniApiSignature {

    public static String generateAuthSignature(final String rsa, byte[] payload) throws Exception {
        PrivateKey privateKey  = RSAUtils.parsePrivateKey(rsa);
        byte[] signatureBytes = RSAUtils.signPKCS1v15(privateKey,payload);
        return Base64.getEncoder().encodeToString(signatureBytes);
    }
}
