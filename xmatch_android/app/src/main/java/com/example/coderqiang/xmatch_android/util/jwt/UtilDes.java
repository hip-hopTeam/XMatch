package com.example.coderqiang.xmatch_android.util.jwt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class UtilDes {
    private static String encryptKey = "n"+"&"+"1P"+")J"+"^"+"A";

    public static String encryptDES(String encryptString) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(encryptKey.getBytes());
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(1, key, zeroIv);
        return UtilBase64.encode(cipher.doFinal(encryptString.getBytes()));
    }

    public static String decryptDES(String decryptString) throws Exception {
        UtilBase64 utilBase64 = new UtilBase64();
        byte[] byteMi = UtilBase64.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(encryptKey.getBytes());
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(2, key, zeroIv);
        return new String(cipher.doFinal(byteMi));
    }

    public static void main(String[] args) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(encryptKey.getBytes());
            System.out.println(zeroIv.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
