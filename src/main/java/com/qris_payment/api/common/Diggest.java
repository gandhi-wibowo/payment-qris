package com.qris_payment.api.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Diggest {

    public static String sha256(byte[] text) throws NoSuchAlgorithmException {
        // Create SHA-256 digest instance
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Compute the hash value
        byte[] hash = digest.digest(text);

        // Convert hash byte array to hexadecimal string
        return Hex.bytesToHex(hash);
    }
}
