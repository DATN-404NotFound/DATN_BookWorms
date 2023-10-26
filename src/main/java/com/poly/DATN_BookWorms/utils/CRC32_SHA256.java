package com.poly.DATN_BookWorms.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CRC32_SHA256 {
    public String getCodeCRC32C(String input) {
        String originalString = "hovandaaat2000";
        String sha256hex = Hashing.crc32c()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
}
