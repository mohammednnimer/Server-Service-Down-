package com.utils.functions;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class ASDKey {

    public static String generate4096BitKey(String clientInfo) {
        try {
            MessageDigest sha512Digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = sha512Digest.digest((clientInfo+ UUID.randomUUID().toString()).getBytes(StandardCharsets.UTF_8));

            byte[] finalKey = new byte[512];
            for (int i = 0; i < 512 / hash.length; i++) {
                System.arraycopy(hash, 0, finalKey, i * hash.length, hash.length);
                hash = sha512Digest.digest(hash);
            }

            StringBuilder hexKey = new StringBuilder();
            for (byte b : finalKey) {
                hexKey.append(String.format("%02x", b));
            }

            return hexKey.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key: " + e.getMessage(), e);
        }
    }


}
