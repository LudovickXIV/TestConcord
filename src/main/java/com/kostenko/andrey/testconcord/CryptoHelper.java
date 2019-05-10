package com.kostenko.andrey.testconcord;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * This class provide many settings for next development.
 * I didn't add many different security setting,
 * like weak, medium, strong, and add spring security,
 * because deadline is coming!
 */

public class CryptoHelper {

    /**
     *
     * @param key you key
     * @param message message to encode
     * @return encoded message in byte array
     * @throws Exception
     */
    public static byte[] encode(byte[] key, byte[] message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message);
    }

    /**
     *
     * @param key you key
     * @param message message to encode
     * @return encoded message in byte array
     * @throws Exception
     */
    public static byte[] encode(String key, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(decoder(key, false), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(decoder(message, false));
    }

    /**
     *
     * @param key you key
     * @param message message to encode
     * @param isKeyBase64 if key in Base64 format - true
     * @param isValueBase64 if message in Base64 format - true
     * @return encoded message in byte array
     * @throws Exception
     */
    public static byte[] encode(String key, String message, boolean isKeyBase64, boolean isValueBase64) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(decoder(key, isKeyBase64), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(decoder(message, isValueBase64));
    }

    /**
     *
     * @param key you key
     * @param message message to encode
     * @param isKeyBase64 if key in Base64 format - true
     * @param isValueBase64 if message in Base64 format - true
     * @param encodeMessageToBase64 if need encode message to Base64 - true
     * @return encoded message in byte array
     * @throws Exception
     */
    public static byte[] encode(String key, String message, boolean isKeyBase64, boolean isValueBase64, boolean encodeMessageToBase64) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(decoder(key, isKeyBase64), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        if (!encodeMessageToBase64) {
            return cipher.doFinal(decoder(message, isValueBase64));
        } else {
            return encoder(cipher.doFinal(decoder(message, isValueBase64)));
        }
    }

    /**
     *
     * @param key you key
     * @param message message to decode
     * @return decoded message in byte array
     * @throws Exception
     */
    public static byte[] decode(byte[] key, byte[] message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(message);
    }

    /**
     *
     * @param key you key
     * @param message message to decode
     * @return decoded message in byte array
     * @throws Exception
     */
    public static byte[] decode(String key, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(decoder(key, false), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(decoder(message, false));
    }

    /**
     *
     * @param key you key
     * @param message message to decode
     * @param isKeyBase64 if key in Base64 format - true
     * @param isValueBase64 if message in Base64 format - true
     * @return decoded message in byte array
     * @throws Exception
     */
    public static byte[] decode(String key, String message, boolean isKeyBase64, boolean isValueBase64) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(decoder(key, isKeyBase64), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] msg = decoder(message, isValueBase64);
        return cipher.doFinal(msg);
    }

    /**
     *
     * @param value - data to decode
     * @param isBase64 if data in Base64 format
     * @return you value in byte array
     */
    private static byte[] decoder(String value, boolean isBase64) {
        byte[] newValue;
        if (!isBase64) {
            newValue = value.getBytes(StandardCharsets.UTF_8);
            return newValue;
        }
        else {
            newValue = Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8));
            return newValue;
        }
    }

    private static byte[] encoder(byte[] value) {
        byte[] newValue = Base64.getEncoder().encode(value);
        return newValue;
    }
}
